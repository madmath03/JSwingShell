package jswingshell.action;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import jswingshell.IJssController;

/**
 * A {@code AbstractJssAction} is an operation that can be called through an id
 * and some parameters if needed.
 *
 * <p>
 * Note: this class has a natural ordering that is inconsistent with equals.</p>
 *
 * @author Mathieu Brunot
 */
public abstract class AbstractJssAction extends AbstractAction implements IJssAction {

    /**
     * A map of actions with their full list of identifiers stored as a 
     * displayable {@code String}.
     */
    protected static final Map<IJssAction, String> COMMAND_IDENTIFIERS_AS_STRING = new HashMap<>();

    /**
     * Get the full list of identifiers stored as a displayable {@code String} 
     * of a given action.
     * @param action the action for which to retrieve the command identifiers
     * @return the full list of identifiers of the action
     */
    protected static final String getCommandIdentifiersAsString(IJssAction action) {
        if (action != null && !COMMAND_IDENTIFIERS_AS_STRING.containsKey(action)) {
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("{ ");
            String[] commandIdentifiers = action.getCommandIdentifiers();
            if (commandIdentifiers != null) {
                for (int i = 0, n = commandIdentifiers.length; i < n; i++) {
                    String identifier = commandIdentifiers[i];
                    stringBuilder.append(identifier);
                    if (i < n - 1) {
                        stringBuilder.append(" | ");
                    }
                }
            }
            stringBuilder.append(" }");

            COMMAND_IDENTIFIERS_AS_STRING.put(action, stringBuilder.toString());
        }

        return COMMAND_IDENTIFIERS_AS_STRING.get(action);
    }

    public static final String getArgumentsAsString(Collection<String> arguments) {
        Object[] args = (arguments != null ? arguments.toArray() : null);
        return getArgumentsAsString(args);
    }

    protected static final String getArgumentsAsString(Object... arguments) {
        StringBuilder argBuilder = new StringBuilder("[ ");

        if (arguments != null) {
            for (int i = 0, n = arguments.length; i < n; i++) {
                Object arg = arguments[i];
                argBuilder.append(arg);
                if (i < n - 1) {
                    argBuilder.append(" | ");
                }
            }
        }
        argBuilder.append(" ]");

        return argBuilder.toString();
    }

    // ######################################################################### 
    /**
     * Action's default shell controller.
     */
    private transient IJssController shellController;
    /**
     * Action's default arguments.
     */
    private String[] args;

    // #########################################################################
    /**
     * Creates an {@code AbstractShellAction}.
     *
     * <p>
     * No default shell or arguments are defined for this action (both set to
     * {@code null}).</p>
     *
     * @see AbstractJssAction#AbstractJssAction(jswingshell.IJssController,
     * java.lang.String[])
     */
    public AbstractJssAction() {
        this(null, (String[]) null);
    }

    /**
     * Creates an {@code AbstractShellAction} with the specified default shell
     * controller and default arguments.
     *
     * @param shellController the default shell controller
     * @param args the default arguments for executing this action through a
     * shell
     */
    public AbstractJssAction(IJssController shellController, String... args) {
        super();
        this.shellController = shellController;
        this.args = args;
    }

    /**
     * Creates an {@code AbstractShellAction} with the specified name, default
     * shell controller and default arguments.
     *
     * @param name the name ({@code Action.NAME}) for the action; a value of
     * {@code null} is ignored
     * @param shellController the default shell controller
     * @param args the default arguments for executing this action through a
     * shell
     */
    public AbstractJssAction(String name, IJssController shellController, String... args) {
        super(name);
        this.shellController = shellController;
        this.args = args;
    }

    /**
     * Creates an {@code AbstractShellAction} with the specified name, small
     * icon, default shell controller and default arguments.
     *
     * @param name the name ({@code Action.NAME}) for the action; a value of
     * {@code null} is ignored
     * @param icon the small icon ({@code Action.SMALL_ICON}) for the action; a
     * value of {@code null} is ignored
     * @param shellController the default shell controller
     * @param args the default arguments for executing this action through a
     * shell
     */
    public AbstractJssAction(String name, Icon icon, IJssController shellController, String... args) {
        super(name, icon);
        this.shellController = shellController;
        this.args = args;
    }

    // #########################################################################
    /**
     * Get the default shell controller for executing this action.
     *
     * @return the default shell controller.
     */
    public IJssController getDefaultShellController() {
        return shellController;
    }

    /**
     * Set the default shell controller for executing this action.
     *
     * @param shellController the default shell controller.
     *
     * @since 1.4
     */
    public void setDefaultShellController(IJssController shellController) {
        this.shellController = shellController;
    }

    /**
     * Get the default shell arguments for executing this action.
     *
     * @return the default arguments.
     */
    public String[] getDefaultArguments() {
        return args;
    }

    /**
     * Set the default shell arguments for executing this action.
     *
     * @param args the default shell arguments for executing this action.
     *
     * @since 1.4
     */
    public void setDefaultArguments(String... args) {
        this.args = args;
    }

    // #########################################################################
    /**
     * {@inheritDoc }
     */
    @Override
    public String getDefaultCommandIdentifier() {
        String[] commandIdentifiers = this.getCommandIdentifiers();
        if (commandIdentifiers != null && commandIdentifiers.length > 0) {
            return commandIdentifiers[0];
        }
        return null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public abstract String[] getCommandIdentifiers();

    /**
     * {@inheritDoc }
     */
    @Override
    public String getCommandIdentifiersAsString() {
        return getCommandIdentifiersAsString(this);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getHelp() {
        return getHelp(this.shellController);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int run() {
        return run(this.shellController, this.args);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int run(String... args) {
        return run(this.shellController, args);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public abstract int run(IJssController shellController, String... args);

    @Override
    public void actionPerformed(ActionEvent e) {
        IJssController defaultShellController = getDefaultShellController();

        // Extract some information from the event to construct arguments
        String[] eventArgs = extractArgumentsFromEvent(e);
        if (eventArgs == null) {
            eventArgs = getDefaultArguments();
        }

        // Run action
        if (defaultShellController != null) {
            // Construct the command line
            StringBuilder commandBuilder = new StringBuilder();
            if (eventArgs != null && eventArgs.length > 0) {
                for (String arg : eventArgs) {
                    commandBuilder.append("\"").append(arg).append("\"");
                    commandBuilder.append(defaultShellController.getCommandParameterSeparator());
                }
            } else {
                String[] commandIdentifiers = this.getCommandIdentifiers();
                if (commandIdentifiers != null && commandIdentifiers.length > 0) {
                    commandBuilder.append(commandIdentifiers[0]);
                }
            }

            defaultShellController.setCommandLine(commandBuilder.toString());
            defaultShellController.interpret();
        } else {
            this.run(defaultShellController, eventArgs);
        }
    }

    /**
     * Construct an array of arguments to pass to the command based on an
     * {@code ActionEvent}.
     *
     * @param e an {@code ActionEvent}.
     *
     * @return an array of arguments to pass to the command, or {@code null} if
     * no arguments were extracted.
     */
    protected String[] extractArgumentsFromEvent(ActionEvent e) {
        return null;
    }

    // #########################################################################
    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>
     * The comparison is done on the actions' command identifiers found, thus
     * allowing for lexicographically sorting of the commands.</p>
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object.
     *
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException if the specified object's type prevents it
     * from being compared to this object.
     */
    @Override
    public int compareTo(IJssAction o) {
        String[] thisActionIdentifiers = this.getCommandIdentifiers(),
                otherActionIdentifiers = o.getCommandIdentifiers();
        // Define size of identifiers
        int thisActionIdentifiersSize = thisActionIdentifiers != null ? thisActionIdentifiers.length : 0,
                otherActionIdentifiersSize = otherActionIdentifiers != null ? otherActionIdentifiers.length : 0;
        // Compare number of identifiers
        if (thisActionIdentifiersSize == 0 && otherActionIdentifiersSize == 0) {
            return 0;
        } else {
            // Compare each actions' identifier
            for (int i = 0; i < thisActionIdentifiersSize; i++) {
                String firstIdentifier = this.getCommandIdentifiers()[i],
                        otherIdentifier = o.getCommandIdentifiers()[i];
                if (firstIdentifier != null) {
                    return firstIdentifier.compareTo(otherIdentifier);
                } else if (otherIdentifier != null) {
                    return otherIdentifier.compareTo(firstIdentifier);
                }
            }
            return 0;
        }
    }

}
