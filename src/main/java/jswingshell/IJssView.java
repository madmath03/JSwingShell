package jswingshell;

/**
 * The Shell view interface.
 * 
 * @author Mathieu Brunot
 */
public interface IJssView {

  // #########################################################################
  // MVC methods
  /**
   * Get the shell controller attached to this shell view.
   *
   * @return the shell controller attached to this shell view.
   */
  public IJssController getController();

  // #########################################################################
  // Components related methods
  // #########################################################################
  // Shell related methods
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
  // Command line related methods
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

}
