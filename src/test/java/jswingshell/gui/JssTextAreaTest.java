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
package jswingshell.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jswingshell.IJssController;
import jswingshell.gui.component.OverwritableTextArea;

/**
 *
 * @author brunot
 */
public class JssTextAreaTest {

  public JssTextAreaTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of getController method, of class JssTextArea.
   */
  @Test
  public void testGetController() {
    System.out.println("getController");
    JssTextArea instance = new JssTextArea();
    JssTextAreaController expResult = null;
    JssTextAreaController result = instance.getController();
    assertEquals(expResult, result);
  }

  /**
   * Test of setController method, of class JssTextArea.
   */
  @Test
  public void testSetController() {
    System.out.println("setController");
    IJssController controller = null;
    JssTextArea instance = new JssTextArea();
    instance.setController(controller);
  }

  /**
   * Test of getJShellTextArea method, of class JssTextArea.
   */
  @Test
  public void testGetJShellTextArea() {
    System.out.println("getJShellTextArea");
    JssTextArea instance = new JssTextArea();
    OverwritableTextArea notExpResult = null;
    OverwritableTextArea result = instance.getJShellTextArea();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of cut method, of class JssTextArea.
   */
  @Test
  public void testCut() {
    System.out.println("cut");
    JssTextArea instance = new JssTextArea(new JssTextAreaController());
    instance.cut();
  }

  /**
   * Test of paste method, of class JssTextArea.
   */
  @Test
  public void testPaste() {
    System.out.println("paste");
    JssTextArea instance = new JssTextArea(new JssTextAreaController());
    instance.paste();
  }

  /**
   * Test of getShellText method, of class JssTextArea.
   */
  @Test
  public void testGetShellText() {
    System.out.println("getShellText");
    JssTextArea instance = new JssTextArea();
    String expResult = "";
    String result = instance.getShellText();
    assertEquals(expResult, result);
  }

  /**
   * Test of setShellText method, of class JssTextArea.
   */
  @Test
  public void testSetShellText() {
    System.out.println("setShellText");
    String newShellText = "";
    JssTextArea instance = new JssTextArea();
    instance.setShellText(newShellText);
  }

  /**
   * Test of isShellTextAreaLocked method, of class JssTextArea.
   */
  @Test
  public void testIsShellTextAreaLocked() {
    System.out.println("isShellTextAreaLocked");
    JssTextArea instance = new JssTextArea();
    boolean expResult = false;
    boolean result = instance.isShellTextAreaLocked();
    assertEquals(expResult, result);
  }

  /**
   * Test of lockShellTextArea method, of class JssTextArea.
   */
  @Test
  public void testLockShellTextArea() {
    System.out.println("lockShellTextArea");
    JssTextArea instance = new JssTextArea();
    instance.lockShellTextArea();
  }

  /**
   * Test of unlockShellTextArea method, of class JssTextArea.
   */
  @Test
  public void testUnlockShellTextArea() {
    System.out.println("unlockShellTextArea");
    JssTextArea instance = new JssTextArea();
    instance.unlockShellTextArea();
  }

  /**
   * Test of getCommandLine method, of class JssTextArea.
   */
  @Test
  public void testGetCommandLine() {
    System.out.println("getCommandLine");
    JssTextArea instance = new JssTextArea(new JssTextAreaController());
    String expResult = "";
    String result = instance.getCommandLine();
    assertEquals(expResult, result);
  }

  /**
   * Test of setCommandLine method, of class JssTextArea.
   */
  @Test
  public void testSetCommandLine() {
    System.out.println("setCommandLine");
    String newCommandLine = "";
    JssTextArea instance = new JssTextArea(new JssTextAreaController());
    instance.setCommandLine(newCommandLine);
  }

  /**
   * Test of isCommandLineLocked method, of class JssTextArea.
   */
  @Test
  public void testIsCommandLineLocked() {
    System.out.println("isCommandLineLocked");
    JssTextArea instance = new JssTextArea();
    boolean expResult = false;
    boolean result = instance.isCommandLineLocked();
    assertEquals(expResult, result);
  }

  /**
   * Test of lockCommandLine method, of class JssTextArea.
   */
  @Test
  public void testLockCommandLine() {
    System.out.println("lockCommandLine");
    JssTextArea instance = new JssTextArea();
    instance.lockCommandLine();
  }

  /**
   * Test of unlockCommandLine method, of class JssTextArea.
   */
  @Test
  public void testUnlockCommandLine() {
    System.out.println("unlockCommandLine");
    JssTextArea instance = new JssTextArea();
    instance.unlockCommandLine();
  }

}
