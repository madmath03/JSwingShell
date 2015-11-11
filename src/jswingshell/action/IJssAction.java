package jswingshell.action;

import javax.swing.Action;
import jswingshell.IJssController;

/**
 * Interface for shell action.
 *
 * <p>
 * Note: these instances may have a natural ordering that is inconsistent with
 * equals. Natural ordering of shell actions is often done on their
 * identifier.</p>
 *
 * @author Mathieu Brunot
 */
public interface IJssAction extends Action, Comparable<IJssAction> {

    /**
     * Default return status code for a successful execution of an action.
     */
    public static final int SUCCESS = 0x0000;

    /**
     * Default return status code for a failed execution of an action.
     */
    public static final int ERROR = 0x0001;

    /**
     * Default return status code for an execution still in progress.
     *
     * <p>
     * This is usually used by action that operates in separate threads. Such
     * actions are responsible for alerting the shell of their end by themselves
     * and thus should return a specific status code to alert the shell
     * environment.</p>
     *
     * @see AbstractThreadedJssAction
     */
    public static final int IN_PROGRESS = 0x0002;

    // #########################################################################
    /**
     * Get the default identifier for this shell command.
     *
     * <p>
     * If this command has no identifiers, returns {@code null}.</p>
     *
     * <p>
     * The default identifier is usually the first of the command identifiers.</p>
     *
     * @return the default identifier for this shell command, {@code null} if
     * none
     *
     * @see #getCommandIdentifiers()
     *
     * @since 1.2
     */
    public String getDefaultCommandIdentifier();

    /**
     * Get array of allowed identifiers for this shell command.
     *
     * @return array of allowed identifiers for this shell command.
     */
    public String[] getCommandIdentifiers();

    /**
     * Get a displayable {@code String} of the allowed identifiers for this
     * shell command.
     *
     * @return a displayable {@code String} of the allowed identifiers
     */
    public String getCommandIdentifiersAsString();

    /**
     * Get an extensive text describing the use of this shell command.
     *
     * @return a string describing the use of this shell command.
     */
    public String getHelp();

    /**
     * Get an extensive text describing the use of this shell command in regards
     * to a given shell.
     *
     * @param shellController The shell controller for which we should retrieve
     * the action's help. This is useful for
     *
     * @return a string describing the use of this shell command.
     */
    public String getHelp(IJssController shellController);

    /**
     * Get a brief text describing the use of this shell command.
     *
     * @return a string describing the use of this shell command.
     */
    public String getBriefHelp();

    /**
     * Run the action for its default shell controller and its default
     * arguments.
     *
     * @return the return status code of the action.
     *
     * @see #run(jswingshell.IJssController, java.lang.String[])
     */
    public int run();

    /**
     * Run the action for its default shell controller and given arguments.
     *
     * @param args the arguments given to the action.
     *
     * @return the return status code of the action.
     *
     * @see #run(jswingshell.IJssController, java.lang.String[])
     */
    public int run(String[] args);

    /**
     * Run the action for a given shell controller and given arguments.
     *
     * @param shellController the shell controller for which to execute the
     * action
     *
     * @param args the arguments given to the action.
     *
     * @return the return status code of the action.
     */
    public int run(IJssController shellController, String[] args);

}
