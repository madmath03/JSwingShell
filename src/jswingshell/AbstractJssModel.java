package jswingshell;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
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
public abstract class AbstractJssModel implements IJssModel, Serializable {

    /**
     * The reference to the shell controller.
     */
    private transient IJssController controller;

    /**
     * All available actions in the model.
     */
    private final Set<IJssAction> availableActions;

    /**
     * Has the map of actions by their command identifier been initialized?
     */
    private transient boolean actionsByCommandIdentifierInitialized = false;

    /**
     * Map of actions by their command identifier.
     *
     * <p>
     * This internal {@code Map} is used to ease the look up of a command for a
     * given identifier. If a command has several identifiers, each identifier
     * will have its own entry in the {@code Map}.</p>
     */
    private transient Map<String, IJssAction> actionsByCommandIdentifier;

    /**
     * Should the actions and identifiers be sorted?
     *
     * <p>
     * This will define if {@link #availableActions} and
     * {@link #actionsByCommandIdentifier} should be sorted through their
     * natural comparison order.</p>
     *
     * @see #availableActions
     * @see #actionsByCommandIdentifier
     *
     * @since 1.4.2
     */
    private final boolean sorted;

    // #########################################################################
    // Constructors
    /**
     * Contruct a shell model with no controller.
     *
     * @see #setController(jswingshell.IJssController)
     *
     * @since 1.4
     */
    protected AbstractJssModel() {
        this((IJssController) null);
    }

    /**
     * Contruct a shell model, with no controller, and the available commands.
     *
     * @param actions the available commands.
     *
     * @see #setController(jswingshell.IJssController)
     *
     * @since 1.4
     */
    protected AbstractJssModel(Collection<IJssAction> actions) {
        this(null, actions);
    }

    /**
     * Contruct a shell model and initializes the controller.
     *
     * @param controller the shell controller to attach to this shell model.
     */
    public AbstractJssModel(IJssController controller) {
        this(controller, false);
    }

    /**
     * Contruct a shell model and initializes the controller.
     *
     * @param controller the shell controller to attach to this shell model.
     * @param sorted Should the actions and identifiers be sorted?
     *
     * @since 1.4.2
     */
    public AbstractJssModel(IJssController controller, boolean sorted) {
        this(controller, 0, sorted);
    }

    /**
     * Contruct a shell model and initializes the controller and the initial
     * capacity for available commands.
     *
     * @param controller the shell controller to attach to this shell model.
     * @param initialCapacity the initial capacity for available commands.
     */
    public AbstractJssModel(IJssController controller, int initialCapacity) {
        this(controller, initialCapacity, false);
    }

    /**
     * Contruct a shell model and initializes the controller and the initial
     * capacity for available commands.
     *
     * @param controller the shell controller to attach to this shell model.
     * @param initialCapacity the initial capacity for available commands.
     * @param sorted Should the actions and identifiers be sorted?
     *
     * @since 1.4.2
     */
    private AbstractJssModel(IJssController controller, int initialCapacity, boolean sorted) {
        super();
        this.controller = controller;
        if (sorted) {
            this.availableActions = new TreeSet<>();
            this.actionsByCommandIdentifier = new TreeMap<>();
        } else {
            this.availableActions = new HashSet<>(initialCapacity);
            this.actionsByCommandIdentifier = new HashMap<>(initialCapacity);
        }
        this.sorted = sorted;
    }

    /**
     * Contruct a shell model and initializes the controller and the available
     * commands.
     *
     * @param controller the shell controller to attach to this shell model.
     * @param actions the available commands.
     */
    public AbstractJssModel(IJssController controller, Collection<IJssAction> actions) {
        this(controller, actions, false);
    }

    /**
     * Contruct a shell model and initializes the controller and the available
     * commands.
     *
     * @param controller the shell controller to attach to this shell model.
     * @param actions the available commands.
     * @param sorted Should the actions be sorted (natural order)?
     *
     * @since 1.4.2
     */
    public AbstractJssModel(IJssController controller, Collection<IJssAction> actions, boolean sorted) {
        super();
        this.controller = controller;

        if (actions != null) {
            if (sorted) {
                this.availableActions = new TreeSet<>(actions);
                this.actionsByCommandIdentifier = new TreeMap<>();
            } else {
                this.availableActions = new HashSet<>(actions);
                this.actionsByCommandIdentifier = new HashMap<>(actions.size());
            }
        } else if (sorted) {
            this.availableActions = new TreeSet<>();
            this.actionsByCommandIdentifier = new TreeMap<>();
        } else {
            this.availableActions = new HashSet<>();
            this.actionsByCommandIdentifier = new HashMap<>();
        }
        this.sorted = sorted;
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
        this(anotherModel.controller, anotherModel);
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
        if (anotherModel.sorted) {
            this.availableActions = new TreeSet<>(anotherModel.availableActions);
            this.actionsByCommandIdentifier = new TreeMap<>(anotherModel.actionsByCommandIdentifier);
        } else {
            this.availableActions = new HashSet<>(anotherModel.availableActions);
            this.actionsByCommandIdentifier = new HashMap<>(anotherModel.actionsByCommandIdentifier);
        }
        this.sorted = anotherModel.sorted;
        this.actionsByCommandIdentifierInitialized = anotherModel.actionsByCommandIdentifierInitialized;
    }

    // #########################################################################
    // MVC methods
    @Override
    public IJssController getController() {
        return controller;
    }

    /**
     * Set the shell controller.
     *
     * @param anotherController the new shell controller.
     *
     * @since 1.4
     */
    protected void setController(IJssController anotherController) {
        this.controller = anotherController;
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
        if (actionsByCommandIdentifier == null) {
            if (availableActions != null) {
                actionsByCommandIdentifier = new HashMap<>(availableActions.size());
            } else {
                actionsByCommandIdentifier = new HashMap<>();
            }
        } else {
            actionsByCommandIdentifier.clear();
        }

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

    protected boolean isSorted() {
        return sorted;
    }

}
