package jswingshell.gui.component;

/*
 Core SWING Advanced Programming 
 By Kim Topley
 ISBN: 0 13 083292 8       
 Publisher: Prentice Hall  
 */
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;
import javax.swing.text.TextAction;

/**
 * A {@code OverwritableTextField} is a lightweight component that allows 
 * the editing of a single line of text, with overriding mode management.
 * 
 * <p>
 * It is an extended version of the {@code OverwritableTextField} by Kim
 * Topley from <em>Core SWING Advanced Programming</em>. This version comes
 * with all the constructors from the {@link javax.swing.JTextField} by reusing 
 * its basic caret as the insert caret.</p>
 * 
 * <p>
 * The insert caret is initialized with the {@code JTextField}'s basic caret.
 * The insert caret can be changed using the 
 * {@link #setInsertCaret(javax.swing.text.Caret)} method. </p>
 * 
 * <p>
 * The default overwrite caret is the {@link OverwriteCaret}. 
 * The overwrite caret can then be changed through the 
 * {@link #setOverwriteCaret(javax.swing.text.Caret)} method. 
 * Setting the overwrite or the insert caret will check the current overwrite 
 * mode and update the {@code JTextArea} caret used, but it will not shift the 
 * overwriting mode.
 * To do so, use the {@link #setOverwriting(boolean)} or the {@code INSERT} key.</p>
 * 
 * <p>
 * Switching from overwrite mode will change the current and use the new one,
 * with all its properties set as the original caret (i.e. dot, mark, blink 
 * rate, and listeners).</p>
 * 
 * <p>
 * The {@code JTextArea}'s {@link #setCaret(javax.swing.text.Caret)} method 
 * is now used to set the caret for the current mode:</p>
 * <ul>
 * <li>If {@code #isOverwriting()} is {@code true}, the {@code setCaret}
 * will call {@code #setOverwriteCaret}.</li>
 * <li>If {@code #isOverwriting()} is {@code false}, the {@code setCaret}
 * will call {@code #setInsertCaret}.</li>
 * </ul>
 * 
 * @beaninfo
 *   attribute: isContainer false
 * description: A component which allows for the editing of a single line of text, with overriding mode management.
 * 
 * @author Kim Topley
 * @author Mathieu Brunot
 * 
 * @see OverwriteCaret
 * @see javax.swing.JTextField
 */
public class OverwritableTextField extends JTextField {

    /**
     * Constructs a new <code>TextField</code>.  A default model is created,
     * the initial string is <code>null</code>,
     * and the number of columns is set to 0.
     */
    public OverwritableTextField() {
        this(null, null, 0);
    }

    /**
     * Constructs a new <code>TextField</code> initialized with the
     * specified text. A default model is created and the number of
     * columns is 0.
     *
     * @param text the text to be displayed, or <code>null</code>
     */
    public OverwritableTextField(String text) {
        this(null, text, 0);
    }

    /**
     * Constructs a new empty <code>TextField</code> with the specified
     * number of columns.
     * A default model is created and the initial string is set to
     * <code>null</code>.
     *
     * @param columns  the number of columns to use to calculate
     *   the preferred width; if columns is set to zero, the
     *   preferred width will be whatever naturally results from
     *   the component implementation
     */
    public OverwritableTextField(int columns) {
        this(null, null, columns);
    }

    /**
     * Constructs a new <code>TextField</code> initialized with the
     * specified text and columns.  A default model is created.
     *
     * @param text the text to be displayed, or <code>null</code>
     * @param columns  the number of columns to use to calculate
     *   the preferred width; if columns is set to zero, the
     *   preferred width will be whatever naturally results from
     *   the component implementation
     */
    public OverwritableTextField(String text, int columns) {
        this(null, text, columns);
    }

    /**
     * Constructs a new <code>OverwritableTextField</code> that uses the given 
     * text storage model and the given number of columns.
     * This is the constructor through which the other constructors feed.
     * If the document is <code>null</code>, a default model is created.
     *
     * @param doc  the text storage to use; if this is <code>null</code>,
     *          a default will be provided by calling the
     *          <code>createDefaultModel</code> method
     * @param text  the initial string to display, or <code>null</code>
     * @param columns  the number of columns to use to calculate
     *   the preferred width &gt;= 0; if <code>columns</code>
     *   is set to zero, the preferred width will be whatever
     *   naturally results from the component implementation
     * @exception IllegalArgumentException if <code>columns</code> &lt; 0
     */
    public OverwritableTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
        
        // Initialize insert caret to the caret of the parent class
        this.insertCaret = super.getCaret();
        // Initialize overwirte caret with same blink rate than insert caret
        if (this.insertCaret != null) {
            this.overwriteCaret = new OverwriteCaret(this.insertCaret.getBlinkRate());
        } else {
            this.overwriteCaret = new OverwriteCaret();
        }
        // Set current caret
        super.setCaret(overwriting ? overwriteCaret : insertCaret);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setKeymap(Keymap map) {
        if (map == null) {
            super.setKeymap(null);
            sharedKeymap = null;
            return;
        }

        if (getKeymap() == null) {
            if (sharedKeymap == null) {
                // Switch keymaps. Add extra bindings.
                removeKeymap(keymapName);
                sharedKeymap = addKeymap(keymapName, map);
                loadKeymap(sharedKeymap, bindings, defaultActions);
            }
            map = sharedKeymap;
        }
        super.setKeymap(map);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void replaceSelection(String content) {
        Document doc = getDocument();
        if (doc != null) {
            /*
             If we are not overwriting, just do the
             usual insert. Also, if there is a selection,
             just overwrite that (and that only).
             */
            if (overwriting == true && getSelectionStart() == getSelectionEnd()) {

                /*
                 Overwrite and no selection. Remove
                 the stretch that we will overwrite,
                 then use the usual code to insert the
                 new text.
                 */
                int insertPosition = getCaretPosition();
                int overwriteLength = doc.getLength() - insertPosition;
                int length = content.length();

                if (overwriteLength > length) {
                    overwriteLength = length;
                }

                // Remove the range being overwritten
                try {
                    doc.remove(insertPosition, overwriteLength);
                } catch (BadLocationException e) {
                    // Won't happen
                }
            }
        }

        super.replaceSelection(content);
    }

    // Change the global overwriting mode
    /**
     * Change the global overwriting mode.
     *
     * @param overwriting does the overwrite mode must be globally enabled?
     * @see #isOverwriting()
     */
    public static void setOverwriting(boolean overwriting) {
        OverwritableTextField.overwriting = overwriting;
    }

    /**
     * Is the overwrite mode globally enabled?
     *
     * @return {@code true} if the overwrite mode is globally enabled,
     * {@code false} otherwise
     */
    public static boolean isOverwriting() {
        return overwriting;
    }

    // Configuration of the current caret
    /**
     * Sets the caret to be used.
     *
     * <p>
     * The method will set the caret for the current overwriting mode:</p>
     * <ul>
     * <li>If {@code #isOverwriting()} is {@code true}, the {@code setCaret}
     * will call {@code #setOverwriteCaret}.</li>
     * <li>If {@code #isOverwriting()} is {@code false}, the {@code setCaret}
     * will call {@code #setInsertCaret}.</li>
     * </ul>
     * 
     * @param caret the caret
     * @see #getCaret
     * @see javax.swing.JTextField#setCaret(javax.swing.text.Caret) 
     */
    @Override
    public void setCaret(Caret caret) {
        if (overwriting) {
            setOverwriteCaret(caret);
        } else {
            setInsertCaret(caret);
        }
    }

    // Allow configuration of the insert caret.
    /**
     * Get the insert caret.
     * @return the insert caret.
     */
    public Caret getInsertCaret() {
        return insertCaret;
    }

    /**
     * Allow configuration of a new insert caret.
     * @param caret the caret
     * @see #getInsertCaret() 
     * @see #setCaret(javax.swing.text.Caret) 
     */
    public void setInsertCaret(Caret caret) {
        insertCaret = caret;
        selectCaret();
    }

    // Allow configuration of the overwrite caret.
    /**
     * Get the overwrite caret.
     * @return the overwrite caret.
     */
    public Caret getOverwriteCaret() {
        return overwriteCaret;
    }

    /**
     * Allow configuration of a new overwrite caret.
     * @param caret the caret
     * @see #getOverwriteCaret() 
     * @see #setCaret(javax.swing.text.Caret) 
     */
    public void setOverwriteCaret(Caret caret) {
        overwriteCaret = caret;
        selectCaret();
    }

    // Caret switching
    @Override
    public void processFocusEvent(FocusEvent evt) {
        if (evt.getID() == FocusEvent.FOCUS_GAINED) {
            selectCaret();
        }
        super.processFocusEvent(evt);
    }

    /**
     * Select the appropriate caret for the current overwrite mode and update 
     * used caret if needed.
     */
    protected void selectCaret() {
        // Select the appropriate caret for the current overwrite mode.
        Caret newCaret = overwriting ? overwriteCaret : insertCaret;

        if (newCaret != getCaret()) {
            Caret caret = getCaret();
            int mark = 0, dot = 0, rate = 0;
            boolean isVisible = false;
            if (caret != null) {
                mark = caret.getMark();
                dot = caret.getDot();
                rate = caret.getBlinkRate();
                isVisible = true;
                caret.setVisible(false);
                // Reset the old caret
                caret.setDot(0);
            }

            super.setCaret(newCaret);

            if (newCaret != null) {
                newCaret.setDot(mark);
                newCaret.moveDot(dot);
                newCaret.setBlinkRate(rate);
                newCaret.setVisible(isVisible);
            }
        }
    }

    protected Caret overwriteCaret;

    protected Caret insertCaret;

    protected static boolean overwriting = false;

    public static final String toggleOverwriteAction = "toggle-overwrite-" + OverwritableTextField.class.toString();

    protected static Keymap sharedKeymap;

    protected static final String keymapName = "OverwriteMap";

    protected static final Action[] defaultActions = {new ToggleOverwriteAction()};

    protected static JTextComponent.KeyBinding[] bindings = {new JTextComponent.KeyBinding(
        KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0),
        toggleOverwriteAction)};

    /**
     * Insert/overwrite toggling for {@code OverwritableTextField} action.
     */
    public static class ToggleOverwriteAction extends TextAction {

        ToggleOverwriteAction() {
            super(toggleOverwriteAction);
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            OverwritableTextField.setOverwriting(!OverwritableTextField
                    .isOverwriting());
            JTextComponent target = getFocusedComponent();
            if (target instanceof OverwritableTextField) {
                OverwritableTextField field = (OverwritableTextField) target;
                field.selectCaret();
            }
        }
    }

}
