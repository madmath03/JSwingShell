package jswingshell;

import java.util.List;
import java.util.Set;

import jswingshell.action.IJssAction;

/**
 * The Shell controller interface.
 *
 * @author Mathieu Brunot
 */
public interface IJssController {

  // #########################################################################
  // MVC methods
  /**
   * Get the shell view.
   *
   * @return the shell view linked to this controller.
   */
  public IJssView getView();

  /**
   * Get the shell model.
   *
   * @return the shell model linked to this controller.
   */
  public IJssModel getModel();

  // #########################################################################
  // Shell methods
  /**
   * Add a new empty line to the shell area.
   */
  public void addNewLineToShell();

  /**
   * Add a new line of text to the shell area.
   *
   * @param text the line of text to add to the shell area.
   */
  public void addNewLineToShell(String text);

  /**
   * Get all the text in the shell area.
   *
   * @return the text in the shell area.
   */
  public String getShellText();

  /**
   * Set the text in the shell area.
   *
   * @param newShellText the new text for the shell area.
   * @see #getShellText()
   */
  public void setShellText(String newShellText);

  /**
   * Clear the shell area.
   */
  public void clearShell();

  /**
   * Is the shell text area locked?
   *
   * @return {@code true} if the shell text area is locked.
   */
  public boolean isShellTextAreaLocked();

  /**
   * Lock the shell area.
   *
   * @see #unlockShellTextArea()
   */
  public void lockShellTextArea();

  /**
   * Unlock the shell area.
   *
   * @see #lockShellTextArea()
   */
  public void unlockShellTextArea();

  // #########################################################################
  // Command line methods
  /**
   * Add a new empty command line.
   */
  public void addNewCommandLine();

  /**
   * Add a new line of text to the command line.
   *
   * @param newCommandLine the new line of text for the command line.
   */
  public void addNewCommandLine(String newCommandLine);

  /**
   * Get all the text in the command line.
   *
   * @return the text in the command line.
   */
  public String getCommandLine();

  /**
   * Set the text in the command line.
   *
   * @param newCommandLine the new text for the command line.
   * @see #getCommandLine()
   */
  public void setCommandLine(String newCommandLine);

  /**
   * Clear the command line.
   */
  public void clearCommandLine();

  /**
   * Is the command line locked?
   *
   * @return {@code true} if the command line is locked.
   */
  public boolean isCommandLineLocked();

  /**
   * Lock the command line.
   *
   * @see #unlockCommandLine()
   */
  public void lockCommandLine();

  /**
   * Unlock the command line.
   *
   * @see #lockCommandLine()
   */
  public void unlockCommandLine();

  // #########################################################################
  // Command management
  /**
   * Get the separator expected between a command and its parameters.
   *
   * @return the separator expected between a command and its parameters.
   */
  public String getCommandParameterSeparator();

  /**
   * Extract the command line to interpret it.
   *
   * @return the command to interpret.
   *
   * @see #interpretCommand(java.lang.String)
   */
  public String extractCommand();

  /**
   * Interpret a command and return its execution status.
   *
   * @return a command's execution status.
   */
  public int interpretCommand();

  /**
   * Interpret a command and return its execution status.
   *
   * @param command the command to interpret.
   * @return the command's execution status.
   */
  public int interpretCommand(String command);

  /**
   * Extract the command line, interpret the command and return its execution status.
   *
   * @return a command's execution status.
   *
   * @since 1.2
   */
  public int interpret();

  /**
   * Execution results levels.
   *
   * <p>
   * Those constants are used to identify the results criticity when adding a message through
   * {@link #publish(jswingshell.IJssController.PublicationLevel, java.lang.String) }.
   * </p>
   */
  public static enum PublicationLevel {

    /**
     * Success.
     *
     * <p>
     * This is used to return the results of a successful execution.
     * </p>
     */
    SUCCESS,
    /**
     * Fatal error.
     *
     * <p>
     * This is typically used for an unexpected error which prevented the proper execution of the
     * program.
     * </p>
     */
    FATAL_ERROR,
    /**
     * Error.
     *
     * <p>
     * This is typically used for an error which was expected but prevented the proper execution of
     * the program.
     * </p>
     */
    ERROR,
    /**
     * Warning.
     *
     * <p>
     * This is typically used for an error which was expected but did not prevented the proper
     * execution of the program.
     * </p>
     */
    WARNING,
    /**
     * Information.
     *
     * <p>
     * This is typically used for displaying useful informations to the user about the execution of
     * the program.
     * </p>
     */
    INFO,
    /**
     * Debug.
     */
    DEBUG,
    /**
     * Trace.
     */
    TRACE;
  }

  /**
   * Publish a message to the shell.
   *
   * <p>
   * If the publication level is lesser or equal to the shell's level, the message shall not be
   * published.
   * </p>
   *
   * @param level the level of the publish.
   * @param message the message to publish.
   * @return {@code true} if the message was published, {@code false} otherwise.
   */
  public boolean publish(PublicationLevel level, String message);

  /**
   * Get the shell's level for publications.
   *
   * @return the shell's level for publications.
   */
  public PublicationLevel getPublicationLevel();

  /**
   * Set the shell's level for publications.
   *
   * @param level the new shell's level for publications.
   */
  public void setPublicationLevel(PublicationLevel level);

  /**
   * The last executed action which is still currently in progress.
   *
   * @return the last executed action which is still currently in progress.
   *
   * @since 1.4
   */
  public IJssAction getCurrentAction();

  /**
   * The list of actions currently in progress.
   *
   * @return an unmodifiable list of the actions currently in progress.
   *
   * @since 1.4
   */
  public List<? extends IJssAction> getActionsInProgress();

  // TODO Add autocomplete methods
  // #########################################################################
  // Model related methods
  /**
   * Get actions available for this shell.
   *
   * @return the actions available for this shell.
   */
  public Set<IJssAction> getAvailableActions();

  /**
   * Get an action by its identifier.
   *
   * @param commandIdentifier the action id
   * @return an action, or {@code null} if not found.
   */
  public IJssAction getActionForCommandIdentifier(String commandIdentifier);

}
