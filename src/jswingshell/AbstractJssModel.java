package jswingshell;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import jswingshell.action.IJssAction;

/**
 * The base for Shell models.
 *
 * <p>
 * This shell model stores all actions available for the shell environment, as
 * well as methods to manage those actions.</p>
 *
 * @author Mathieu Brunot
 */
public abstract class AbstractJssModel implements IJssModel {

    private final IJssController controller;

    private final Set<IJssAction> availableActions;

    private boolean actionsByCommandIdentifierInitialized = false;

    private final Map<String, IJssAction> actionsByCommandIdentifier;

    // #########################################################################
    // Constructors
    /**
     * Contruct a shell model and initializes the controller.
     *
     * @param controller the shell controller to attach to this shell model.
     */
    public AbstractJssModel(IJssController controller) {
        super();
        this.controller = controller;
        this.availableActions = new HashSet<>();
        this.actionsByCommandIdentifier = new HashMap<>();
    }

    /**
     * Contruct a shell model and initializes the controller and the initial
     * capacity for available commands.
     *
     * @param controller the shell controller to attach to this shell model.
     * @param initialCapacity the initial capacity for available commands.
     */
    public AbstractJssModel(IJssController controller, int initialCapacity) {
        super();
        this.controller = controller;
        this.availableActions = new HashSet<>(initialCapacity);
        this.actionsByCommandIdentifier = new HashMap<>(initialCapacity);
    }

    /**
     * Contruct a shell model and initializes the controller and the available
     * commands.
     *
     * @param controller the shell controller to attach to this shell model.
     * @param actions the available commands.
     */
    public AbstractJssModel(IJssController controller, Collection<IJssAction> actions) {
        super();
        this.controller = controller;

        if (actions != null) {
            this.availableActions = new HashSet<>(actions);
            this.actionsByCommandIdentifier = new HashMap<>(actions.size());
        } else {
            this.availableActions = new HashSet<>();
            this.actionsByCommandIdentifier = new HashMap<>();
        }
    }

    /**
     * Contruct a copy of a shell model.
     *
     * <p>
     * This will create a copy of the model's available actions and attach the
     * new model to the same controller. If you need to attach the new model to
     * another controller, please look at 
     * {@link AbstractJssModel#AbstractJssModel(jswingshell.IJssController, jswingshell.AbstractJssModel) }.</p>
     *
     * @param anotherModel the shell model to copy.
     *
     * @see #clone()
     */
    public AbstractJssModel(AbstractJssModel anotherModel) {
        super();
        this.controller = anotherModel.controller;
        this.availableActions = new HashSet<>(anotherModel.availableActions);
        this.actionsByCommandIdentifier = new HashMap<>(anotherModel.actionsByCommandIdentifier);
        this.actionsByCommandIdentifierInitialized = anotherModel.actionsByCommandIdentifierInitialized;
    }

    /**
     * Contruct a copy of a shell model and attach it to a different controller.
     *
     * <p>
     * This will create a copy of the model's available actions and attach the
     * new model to the given controller.</p>
     *
     * @param anotherModel the shell model to copy.
     * @param anotherController the shell controller to attach to this shell
     * model.
     */
    public AbstractJssModel(IJssController anotherController, AbstractJssModel anotherModel) {
        super();
        this.controller = anotherController;
        this.availableActions = new HashSet<>(anotherModel.availableActions);
        this.actionsByCommandIdentifier = new HashMap<>(anotherModel.actionsByCommandIdentifier);
        this.actionsByCommandIdentifierInitialized = anotherModel.actionsByCommandIdentifierInitialized;
    }

    // #########################################################################
    // MVC methods
    @Override
    public IJssController getController() {
        return controller;
    }

    // #########################################################################
    // Actions methods
    /**
     * Get actions available for this shell environment.
     *
     * @return actions available.
     */
    protected Set<IJssAction> getAvailableActions() {
        return availableActions;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void clear() {
        getAvailableActions().clear();
        getActionsByCommandIdentifier().clear();
        actionsByCommandIdentifierInitialized = true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean add(IJssAction action) {
        boolean added = getAvailableActions().add(action);
        actionsByCommandIdentifierInitialized = !added;
        return added;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean addAll(Collection<? extends IJssAction> actions) {
        boolean added = getAvailableActions().addAll(actions);
        actionsByCommandIdentifierInitialized = !added;
        return added;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean remove(IJssAction action) {
        boolean removed = getAvailableActions().remove(action);
        actionsByCommandIdentifierInitialized = !removed;
        return removed;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean removeAll(Collection<? extends IJssAction> actions) {
        boolean removed = getAvailableActions().removeAll(actions);
        actionsByCommandIdentifierInitialized = !removed;
        return removed;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean contains(IJssAction action) {
        return getAvailableActions().contains(action);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean containsAll(Collection<? extends IJssAction> actions) {
        return getAvailableActions().containsAll(actions);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean retainAll(Collection<? extends IJssAction> actions) {
        return getAvailableActions().retainAll(actions);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isEmpty() {
        return getAvailableActions().isEmpty();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int size() {
        return getAvailableActions().size();
    }

    protected Map<String, IJssAction> getActionsByCommandIdentifier() {
        if (!actionsByCommandIdentifierInitialized) {
            initActionsByCommandIdentifier();
        }
        return actionsByCommandIdentifier;
    }

    private void initActionsByCommandIdentifier() {
        actionsByCommandIdentifier.clear();

        Set<IJssAction> actions = getAvailableActions();
        if (actions != null) {
            for (IJssAction shellAction : actions) {
                if (shellAction == null || shellAction.getCommandIdentifiers() == null) {
                    continue;
                }
                for (String commandIdentifier : shellAction.getCommandIdentifiers()) {
                    if (commandIdentifier != null) {
                        actionsByCommandIdentifier.put(commandIdentifier.toUpperCase(), shellAction);
                    }
                }
            }
        }

        actionsByCommandIdentifierInitialized = true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public IJssAction getActionForCommandIdentifier(String commandIdentifier) {
        IJssAction action = null;

        Map<String, IJssAction> actions = getActionsByCommandIdentifier();
        if (commandIdentifier != null && actions != null) {
            action = actions.get(commandIdentifier.toUpperCase());
        }

        return action;
    }

}
