/*
 * The MIT License
 *
 * Copyright 2016 brunot.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package jswingshell.gui.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.event.FocusEvent;

import javax.swing.text.Caret;
import javax.swing.text.Keymap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author brunot
 */
public class OverwritableTextAreaTest {

  public OverwritableTextAreaTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of setKeymap method, of class OverwritableTextArea.
   */
  @Test
  public void testSetKeymap() {
    System.out.println("setKeymap");
    Keymap map = null;
    OverwritableTextArea instance = new OverwritableTextArea();
    instance.setKeymap(map);
  }

  /**
   * Test of replaceSelection method, of class OverwritableTextArea.
   */
  @Test
  public void testReplaceSelection() {
    System.out.println("replaceSelection");
    String content = "";
    OverwritableTextArea instance = new OverwritableTextArea();
    instance.replaceSelection(content);
  }

  /**
   * Test of setOverwriting method, of class OverwritableTextArea.
   */
  @Test
  public void testSetOverwriting() {
    System.out.println("setOverwriting");
    boolean overwriting = false;
    OverwritableTextArea.setOverwriting(overwriting);
  }

  /**
   * Test of isOverwriting method, of class OverwritableTextArea.
   */
  @Test
  public void testIsOverwriting() {
    System.out.println("isOverwriting");
    boolean expResult = false;
    boolean result = OverwritableTextArea.isOverwriting();
    assertEquals(expResult, result);
  }

  /**
   * Test of setCaret method, of class OverwritableTextArea.
   */
  @Test
  public void testSetCaret() {
    System.out.println("setCaret");
    Caret caret = null;
    OverwritableTextArea instance = new OverwritableTextArea();
    instance.setCaret(caret);
  }

  /**
   * Test of getInsertCaret method, of class OverwritableTextArea.
   */
  @Test
  public void testGetInsertCaret() {
    System.out.println("getInsertCaret");
    OverwritableTextArea instance = new OverwritableTextArea();
    Caret notExpResult = null;
    Caret result = instance.getInsertCaret();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of setInsertCaret method, of class OverwritableTextArea.
   */
  @Test
  public void testSetInsertCaret() {
    System.out.println("setInsertCaret");
    Caret caret = null;
    OverwritableTextArea instance = new OverwritableTextArea();
    instance.setInsertCaret(caret);
  }

  /**
   * Test of getOverwriteCaret method, of class OverwritableTextArea.
   */
  @Test
  public void testGetOverwriteCaret() {
    System.out.println("getOverwriteCaret");
    OverwritableTextArea instance = new OverwritableTextArea();
    Caret notExpResult = null;
    Caret result = instance.getOverwriteCaret();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of setOverwriteCaret method, of class OverwritableTextArea.
   */
  @Test
  public void testSetOverwriteCaret() {
    System.out.println("setOverwriteCaret");
    Caret caret = null;
    OverwritableTextArea instance = new OverwritableTextArea();
    instance.setOverwriteCaret(caret);
  }

  /**
   * Test of processFocusEvent method, of class OverwritableTextArea.
   */
  @Test
  public void testProcessFocusEvent() {
    System.out.println("processFocusEvent");
    OverwritableTextArea instance = new OverwritableTextArea();
    FocusEvent evt = new FocusEvent(instance, 0, true);
    instance.processFocusEvent(evt);
  }

  /**
   * Test of selectCaret method, of class OverwritableTextArea.
   */
  @Test
  public void testSelectCaret() {
    System.out.println("selectCaret");
    OverwritableTextArea instance = new OverwritableTextArea();
    instance.selectCaret();
  }

}
