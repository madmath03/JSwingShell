package jswingshell.gui;

import javax.swing.text.Document;
import jswingshell.IJssController;

/**
 * A simple Shell Shell {@code TextArea} view.
 *
 * @author Mathieu Brunot
 */
public class JssTextArea extends AbstractJssTextArea {

    // #########################################################################
    // Constructors
    public JssTextArea() {
        super();
    }

    public JssTextArea(JssTextAreaController controller) {
        super(controller);
    }

    public JssTextArea(IJssController controller, String text) {
        super(controller, text);
    }

    public JssTextArea(IJssController controller, int rows, int columns) {
        super(controller, rows, columns);
    }

    public JssTextArea(IJssController controller, String text, int rows, int columns) {
        super(controller, text, rows, columns);
    }

    public JssTextArea(IJssController controller, Document doc) {
        super(controller, doc);
    }

    public JssTextArea(IJssController controller, Document doc, String text, int rows, int columns) {
        super(controller, doc, text, rows, columns);
    }

    // #########################################################################
    // MVC methods
    @Override
    public JssTextAreaController getController() {
        return (JssTextAreaController) super.getController();
    }

    @Override
    public void setController(IJssController controller) {
        // Remove previous controller from key listeners
        if (getController() != null) {
            getJShellTextArea().removeKeyListener(getController());
        }

        super.setController(controller);

        // Initialize key listener for the new controller
        if (controller instanceof JssTextAreaController) {
            getJShellTextArea().addKeyListener((JssTextAreaController) controller);
        }
    }

    // #########################################################################
    // Specific implementation methods
    public jswingshell.gui.component.OverwritableTextArea getJShellTextArea() {
        return this;
    }

    @Override
    public void cut() {
        // Make sure the shell line itself is never compromised
        getController().fixSelection();
        super.cut();
    }

    @Override
    public void paste() {
        // Make sure the shell line itself is never compromised
        getController().fixSelection();
        super.paste();
    }

    // #########################################################################
    // Shell related methods
    @Override
    public String getShellText() {
        return getJShellTextArea().getText();
    }

    @Override
    public void setShellText(String newShellText) {
        getJShellTextArea().setText(newShellText);
    }

    @Override
    public boolean isShellTextAreaLocked() {
        return !getJShellTextArea().isEditable();
    }

    @Override
    public void lockShellTextArea() {
        getJShellTextArea().setEditable(false);
        getJShellTextArea().getCaret().setVisible(false);
    }

    @Override
    public void unlockShellTextArea() {
        getJShellTextArea().setEditable(true);
        getJShellTextArea().getCaret().setVisible(true);
    }

    // #########################################################################
    // Command line related methods
    @Override
    public String getCommandLine() {
        // For this kind of complicated view, delegate to controller
        return getController().getCommandLine();
    }

    @Override
    public void setCommandLine(String newCommandLine) {
        // For this kind of complicated view, delegate to controller
        getController().setCommandLine(newCommandLine);
    }

    @Override
    public boolean isCommandLineLocked() {
        return !getJShellTextArea().isEditable();
    }

    @Override
    public void lockCommandLine() {
        getJShellTextArea().setEditable(false);
        getJShellTextArea().getCaret().setVisible(false);
    }

    @Override
    public void unlockCommandLine() {
        getJShellTextArea().setEditable(true);
        getJShellTextArea().getCaret().setVisible(true);
    }

}
