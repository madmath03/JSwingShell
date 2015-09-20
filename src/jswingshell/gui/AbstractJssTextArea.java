package jswingshell.gui;

import javax.swing.text.Document;
import jswingshell.IJssController;
import jswingshell.IJssView;

/**
 *
 * @author Mathieu Brunot
 */
public abstract class AbstractJssTextArea extends jswingshell.gui.component.OverwritableTextArea implements IJssView {

    private IJssController controller;

    // #########################################################################
    // Constructors
    public AbstractJssTextArea() {
        super();
    }

    public AbstractJssTextArea(IJssController controller) {
        super();
        this.controller = controller;
    }

    public AbstractJssTextArea(IJssController controller, String text) {
        super(text);
        this.controller = controller;
    }

    public AbstractJssTextArea(IJssController controller, int rows, int columns) {
        super(rows, columns);
        this.controller = controller;
    }

    public AbstractJssTextArea(IJssController controller, String text, int rows, int columns) {
        super(text, rows, columns);
        this.controller = controller;
    }

    public AbstractJssTextArea(IJssController controller, Document doc) {
        super(doc);
        this.controller = controller;
    }

    public AbstractJssTextArea(IJssController controller, Document doc, String text, int rows, int columns) {
        super(doc, text, rows, columns);
        this.controller = controller;
    }

    // #########################################################################
    // MVC methods
    @Override
    public IJssController getController() {
        return controller;
    }

    public void setController(IJssController controller) {
        this.controller = controller;
    }

    // #########################################################################
    // Components related methods

    // #########################################################################
    // Shell related methods
    @Override
    public abstract String getShellText();

    @Override
    public abstract void setShellText(String newShellText);

    @Override
    public abstract boolean isShellTextAreaLocked();

    @Override
    public abstract void lockShellTextArea();

    @Override
    public abstract void unlockShellTextArea();

    // #########################################################################
    // Command line related methods
    @Override
    public abstract String getCommandLine();

    @Override
    public abstract void setCommandLine(String newCommandLine);

    @Override
    public abstract boolean isCommandLineLocked();

    @Override
    public abstract void lockCommandLine();

    @Override
    public abstract void unlockCommandLine();

}
