package jswingshell.gui.component;

/*
 * Core SWING Advanced Programming By Kim Topley ISBN: 0 13 083292 8 Publisher: Prentice Hall
 */
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.plaf.TextUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;

/**
 * A basic implementation of Caret designed for overwrite mode.
 * 
 * The caret is rendered as a filled rectangle in the color specified by the CaretColor property of
 * the associated JTextComponent. It can blink at the rate specified by the BlinkRate property.
 * 
 * @author Kim Topley
 * @author Mathieu Brunot
 * 
 * @see javax.swing.text.Caret
 * @see javax.swing.text.DefaultCaret
 */
public class OverwriteCaret extends DefaultCaret {

  /**
   * Logger.
   */
  private static final Logger LOGGER = Logger.getLogger(OverwriteCaret.class.getName());

  /**
   * Constructor with blink rate.
   * 
   * @param rate the rate in milliseconds, 0 to stop blinking
   * @see javax.swing.text.Caret#getBlinkRate
   */
  public OverwriteCaret(int rate) {
    super();
    this.setBlinkRate(rate);
  }

  /**
   * Default constructor.
   */
  public OverwriteCaret() {
    this(0);
  }

  /**
   * {@inheritDoc }
   */
  @Override
  protected synchronized void damage(Rectangle r) {
    if (r != null) {
      try {
        JTextComponent comp = getComponent();
        TextUI mapper = comp.getUI();
        Rectangle r2 = mapper.modelToView(comp, getDot() + 1);
        int rectangleWidth = r2.x - r.x;
        if (rectangleWidth == 0) {
          rectangleWidth = MIN_WIDTH;
        }
        comp.repaint(r.x, r.y, rectangleWidth, r.height);

        // Swing 1.1 beta 2 compat
        this.x = r.x;
        this.y = r.y;
        this.width = rectangleWidth;
        this.height = r.height;
      } catch (BadLocationException e) {
      }
    }
  }

  /**
   * {@inheritDoc }
   */
  @Override
  public void paint(Graphics g) {
    if (isVisible()) {
      try {
        JTextComponent comp = getComponent();
        TextUI mapper = comp.getUI();
        Rectangle r1 = mapper.modelToView(comp, getDot());
        Rectangle r2 = mapper.modelToView(comp, getDot() + 1);
        g = g.create();
        g.setColor(comp.getForeground());
        g.setXORMode(comp.getBackground());
        int rectangleWidth = r2.x - r1.x;
        if (rectangleWidth == 0) {
          rectangleWidth = MIN_WIDTH;
        }
        g.fillRect(r1.x, r1.y, rectangleWidth, r1.height);
        g.dispose();
      } catch (BadLocationException e) {
        LOGGER.log(Level.SEVERE, null, e);
      }
    }
  }

  protected static final int MIN_WIDTH = 8;
}
