package jswingshell.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Document;
import jswingshell.AbstractJssController;
import jswingshell.AbstractJssModel;
import jswingshell.JssSimpleModel;

/**
 *
 * @author Mathieu Brunot
 */
public class JssTextAreaController extends AbstractJssController {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(JssTextAreaController.class.getName());

    /**
     * The default end of line.
     * @since 1.4
     */
    private static final String EOL1 = "\n";
    /**
     * The system end of line.
     * @since 1.4
     */
    private static final String EOL2;
    
    static {
        String eol = EOL1;
        try {
            eol = System.getProperty("line.separator", EOL1);
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, "Could not retrieve the system line separator: {0}", e.getMessage());
            eol = EOL1;
        } finally {
            EOL2 = eol;
        }
    }

    /**
     * The new line separator.
     */
    private static final String NEW_LINE = EOL2;

    private final JssTextArea view;

    private final JssSimpleModel model;

    private int shellLineStart = -1;

    private int commandLineStart = -1;

    // #########################################################################
    // Constructors
    public JssTextAreaController() {
        super();
        this.view = new JssTextArea(this);
        this.model = new JssSimpleModel(this);
        initView();
    }

    public JssTextAreaController(JssTextArea view) {
        super();
        this.view = view;
        this.model = new JssSimpleModel(this);
        initView();
    }

    public JssTextAreaController(String text) {
        super();
        this.view = new JssTextArea(this, text);
        this.model = new JssSimpleModel(this);
        initView();
    }

    public JssTextAreaController(int rows, int columns) {
        super();
        this.view = new JssTextArea(this, rows, columns);
        this.model = new JssSimpleModel(this);
        initView();
    }

    public JssTextAreaController(String text, int rows, int columns) {
        super();
        this.view = new JssTextArea(this, text, rows, columns);
        this.model = new JssSimpleModel(this);
        initView();
    }

    public JssTextAreaController(Document doc) {
        super();
        this.view = new JssTextArea(this, doc);
        this.model = new JssSimpleModel(this);
        initView();
    }

    public JssTextAreaController(Document doc, String text, int rows, int columns) {
        super();
        this.view = new JssTextArea(this, doc, text, rows, columns);
        this.model = new JssSimpleModel(this);
        initView();
    }

    public JssTextAreaController(JssTextAreaController anotherController) {
        super();
        this.view = new JssTextArea(this);
        this.model = new JssSimpleModel(this, anotherController.model);
        initView();
    }

    public JssTextAreaController(JssTextAreaController anotherController, String text) {
        super();
        this.view = new JssTextArea(this, text);
        this.model = new JssSimpleModel(this, anotherController.model);
        initView();
    }

    public JssTextAreaController(JssTextAreaController anotherController, Document doc) {
        super();
        this.view = new JssTextArea(this, doc);
        this.model = new JssSimpleModel(this, anotherController.model);
        initView();
    }

    // #########################################################################
    // MVC methods
    @Override
    public JssTextArea getView() {
        return view;
    }

    private void initView() {
        if (getView() == null) {
            return;
        }

        // Init display properties
        getView().setBackground(Color.black);
        getView().setForeground(Color.white);
        getView().setSelectedTextColor(Color.black);
        getView().setSelectionColor(Color.white);
        getView().setTabSize(4);
        getView().setFont(new java.awt.Font("LucidaSans", Font.BOLD, 11));

        addNewCommandLine();
        unlockShellTextArea();
        // Set caret to the end of the document
        setCaretToEndOfDocument();

        // Ensure the view is linked to this shell controller
        getView().setController(this);
    }

    @Override
    public AbstractJssModel getModel() {
        return model;
    }

    // #########################################################################
    // Shell methods
    @Override
    public void setShellText(String newShellText) {
        boolean isEditable = getView().isEditable();
        super.setShellText(newShellText);
        getView().setEditable(isEditable);
        shellLineStart = -1;
        commandLineStart = -1;
    }

    @Override
    public void addNewLineToShell() {
        boolean isEditable = getView().isEditable();
        getView().getJShellTextArea().append(NEW_LINE);
        getView().setEditable(isEditable);
        shellLineStart = -1;
        commandLineStart = -1;
    }

    @Override
    public void addNewLineToShell(String text) {
        boolean isEditable = getView().isEditable();
        getView().getJShellTextArea().append(NEW_LINE + text);
        getView().setEditable(isEditable);
        shellLineStart = -1;
        commandLineStart = -1;
    }

    protected int getLastShellLinePosition() {
        if (shellLineStart != -1) {
            return shellLineStart;
        }

        String newCommandLineStart = getNewCommandLine();
        String shellText = getShellText();
        int indexOfLastLine = shellText.lastIndexOf(NEW_LINE),
                indexOfLastShellLine = shellText.lastIndexOf(newCommandLineStart);

        if (indexOfLastShellLine < indexOfLastLine) {
            // Some text with line feeds was inserted in shell
            shellLineStart = -1;
        } else if (indexOfLastShellLine > indexOfLastLine) {
            // This case should never happen!!
            LOGGER.severe("Something went wrong: we could not find the last shell line!!");
            shellLineStart = -1;
        } else {
            shellLineStart = indexOfLastShellLine;
        }

        return shellLineStart;
    }

    // #########################################################################
    // Command line methods
    private String getNewCommandLine() {
        return NEW_LINE + getCommandLinePrefix();
    }

    @Override
    public void addNewCommandLine() {
        String newCommandLineStart = getNewCommandLine();
        getView().getJShellTextArea().append(newCommandLineStart);
        if (commandLineStart < 0) {
            commandLineStart = getView().getShellText().lastIndexOf(newCommandLineStart);
        }
        commandLineStart += newCommandLineStart.length();
        setCaretPosition(commandLineStart);
    }

    @Override
    public void addNewCommandLine(String newCommandLine) {
        String newCommandLineStart = getNewCommandLine();
        getView().getJShellTextArea().append(newCommandLineStart + newCommandLine);
        if (commandLineStart < 0) {
            commandLineStart = getView().getShellText().lastIndexOf(newCommandLineStart);
        }
        commandLineStart += newCommandLineStart.length();
        setCaretPosition(commandLineStart);
    }

    @Override
    public String getCommandLine() {
        String commandLine;

        String shellText = getShellText();
        int commandLinePosition = getCommandLinePosition();
        if (commandLinePosition > -1 && shellText != null && commandLinePosition < shellText.length()) {
            commandLine = shellText.substring(commandLinePosition);
        } else {
            commandLine = null;
        }

        return commandLine;
    }

    @Override
    public void setCommandLine(String newCommandLine) {
        String shellText = getShellText();
        // Construct a substring of the whole shell without the last line
        String newCommandLineStart = getNewCommandLine();

        String newShellText;
        if (commandLineStart > -1 && shellText != null) {
            newShellText = shellText.substring(0, commandLineStart);
        } else if (shellText == null) {
            commandLineStart = newCommandLineStart.length();
            newShellText = newCommandLineStart;
        } else {
            int indexOfLastLine = getLastShellLinePosition();
            if (indexOfLastLine > -1) {
                commandLineStart = indexOfLastLine + newCommandLineStart.length();
            }
            if (commandLineStart > -1) {
                newShellText = shellText.substring(0, commandLineStart);
            } else {
                // If we could not find the shell line, do nothing
                newShellText = null;
            }
        }

        // Add the new shell line to the shell
        if (newShellText != null) {
            if (newCommandLine != null) {
                newShellText += newCommandLine;
            }
            setShellText(newShellText);
        }

    }

    protected int getCommandLinePosition() {
        if (commandLineStart == -1) {
            int indexOfLastLine = getLastShellLinePosition();
            String newCommandLineStart = getNewCommandLine();
            if (indexOfLastLine > -1) {
                commandLineStart = indexOfLastLine + newCommandLineStart.length();
            }
        }
        return commandLineStart;
    }

    protected int getCaretPosition() {
        return getView().getJShellTextArea().getCaretPosition();
    }

    protected void setCaretPosition(int caretPosition) {
        getView().getJShellTextArea().setCaretPosition(caretPosition);
    }

    protected void setCaretToEndOfDocument() {
        setCaretPosition(getView().getJShellTextArea().getDocument().getLength());
    }

    protected void moveCaretPosition(int caretPosition) {
        getView().getJShellTextArea().moveCaretPosition(caretPosition);
    }

    protected boolean isCaretBeforeStartOfCommandLine() {
        return getCaretPosition() < getCommandLinePosition();
    }

    protected boolean isCaretBeforeOrEqualToStartOfCommandLine() {
        return getCaretPosition() <= getCommandLinePosition();
    }

    protected boolean isCaretAtStartOfCommandLine() {
        return getCaretPosition() == getCommandLinePosition();
    }

    protected boolean isCaretAfterOrEqualToStartOfCommandLine() {
        return getCaretPosition() >= getCommandLinePosition();
    }

    protected boolean isCaretAfterStartOfCommandLine() {
        return getCaretPosition() > getCommandLinePosition();
    }

    // #########################################################################
    // Key handler methods
    @Override
    public void keyPressed(java.awt.event.KeyEvent evt) {
        jShellTextAreaKeyPressed(evt);
    }

    private void jShellTextAreaKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt == null) {
            return;
        }
        if (isCommandLineLocked()) {
            evt.consume();
            return;
        }

        // Actions depending on the key pressed
        final int keyCode = evt.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ENTER:
                evt.consume();
                interpret();
                break;

            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                if (((KeyEvent.SHIFT_MASK | KeyEvent.SHIFT_DOWN_MASK) & evt.getModifiers()) != 0) {
                    // Do not overwrite behavior if SHIFT key applied
                    break;
                } else {
                    // Retrieve the appropriate command from the history
                    String commandFromHistory;
                    if (keyCode == KeyEvent.VK_UP) {
                        commandFromHistory = getPreviousCommand();
                    } else {
                        commandFromHistory = getNextCommand();
                    }

                    if (commandFromHistory == null) {
                        commandFromHistory = "";
                    }

                    // Cancel event and set command line
                    evt.consume();
                    setCommandLine(commandFromHistory);
                    // Set caret to the end of the document
                    setCaretToEndOfDocument();
                }
                break;

            case KeyEvent.VK_HOME: {
                int startOfCommandfLine = getCommandLinePosition();
                // If we are inside the command line, go to command line start
                if (getCaretPosition() >= startOfCommandfLine) {
                    if (((KeyEvent.SHIFT_MASK | KeyEvent.SHIFT_DOWN_MASK) & evt.getModifiers()) == 0) {
                        setCaretPosition(startOfCommandfLine);
                    } else {
                        moveCaretPosition(startOfCommandfLine);
                    }
                    evt.consume();
                }
            }
            break;

            case KeyEvent.VK_LEFT:
                // Consume if we already are at the start of the line
                if (isCaretAtStartOfCommandLine()) {
                    evt.consume();
                }
                break;

            case KeyEvent.VK_BACK_SPACE: {
                // Make sure the shell line itself is never compromised
                int startOfCommandfLine = getCommandLinePosition();
                if (getView().getJShellTextArea().getSelectionStart() < startOfCommandfLine) {
                    getView().getJShellTextArea().setSelectionStart(startOfCommandfLine);
                }
                if (getView().getJShellTextArea().getSelectedText() == null && getCaretPosition() <= startOfCommandfLine) {
                    evt.consume();
                }
            }
            break;

            case KeyEvent.VK_CONTEXT_MENU:
            case KeyEvent.VK_CONTROL:
            case KeyEvent.VK_ALT:
            case KeyEvent.VK_SHIFT:
                // Keep default behavior and do nothing for mask keys
                break;

            case KeyEvent.VK_UNDEFINED:
            default:
                // If any mask different than Alt, AltGr or Shift in progress, let the event continue
                if (evt.getModifiers() != 0 && ((KeyEvent.ALT_GRAPH_MASK | KeyEvent.ALT_GRAPH_DOWN_MASK | KeyEvent.ALT_MASK | KeyEvent.ALT_DOWN_MASK | KeyEvent.SHIFT_MASK | KeyEvent.SHIFT_DOWN_MASK) & evt.getModifiers()) == 0) {
                    break;
                } else {
                    fixSelection();
                }
                break;
        }
    }

    /**
     * <em>Fix</em> the current selection to ensure that any input will not edit
     * the <em>past</em> shell data or compromise the command line itself.
     */
    protected void fixSelection() {
        // Make sure the shell line itself is never compromised
        int startOfCommandfLine = getCommandLinePosition();
        if (getView().getJShellTextArea().getSelectionStart() < startOfCommandfLine && getView().getJShellTextArea().getSelectionEnd() > startOfCommandfLine) {
            getView().getJShellTextArea().setSelectionStart(startOfCommandfLine);
        } else if (getCaretPosition() < startOfCommandfLine) {
            setCaretToEndOfDocument();
        }
    }

    // #########################################################################
    // Command management
    // #########################################################################
    // Model related methods
}
