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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jswingshell.IJssController;

/**
 *
 * @author brunot
 */
public class AbstractJssTextAreaTest {

  public AbstractJssTextAreaTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of getController method, of class AbstractJssTextArea.
   */
  @Test
  public void testGetController() {
    System.out.println("getController");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    IJssController expResult = null;
    IJssController result = instance.getController();
    assertEquals(expResult, result);
  }

  /**
   * Test of setController method, of class AbstractJssTextArea.
   */
  @Test
  public void testSetController() {
    System.out.println("setController");
    IJssController controller = null;
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    instance.setController(controller);
  }

  /**
   * Test of getShellText method, of class AbstractJssTextArea.
   */
  @Test
  public void testGetShellText() {
    System.out.println("getShellText");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    String expResult = "";
    String result = instance.getShellText();
    assertEquals(expResult, result);
  }

  /**
   * Test of setShellText method, of class AbstractJssTextArea.
   */
  @Test
  public void testSetShellText() {
    System.out.println("setShellText");
    String newShellText = "";
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    instance.setShellText(newShellText);
  }

  /**
   * Test of isShellTextAreaLocked method, of class AbstractJssTextArea.
   */
  @Test
  public void testIsShellTextAreaLocked() {
    System.out.println("isShellTextAreaLocked");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    boolean expResult = false;
    boolean result = instance.isShellTextAreaLocked();
    assertEquals(expResult, result);
  }

  /**
   * Test of lockShellTextArea method, of class AbstractJssTextArea.
   */
  @Test
  public void testLockShellTextArea() {
    System.out.println("lockShellTextArea");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    instance.lockShellTextArea();
  }

  /**
   * Test of unlockShellTextArea method, of class AbstractJssTextArea.
   */
  @Test
  public void testUnlockShellTextArea() {
    System.out.println("unlockShellTextArea");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    instance.unlockShellTextArea();
  }

  /**
   * Test of getCommandLine method, of class AbstractJssTextArea.
   */
  @Test
  public void testGetCommandLine() {
    System.out.println("getCommandLine");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    String expResult = "";
    String result = instance.getCommandLine();
    assertEquals(expResult, result);
  }

  /**
   * Test of setCommandLine method, of class AbstractJssTextArea.
   */
  @Test
  public void testSetCommandLine() {
    System.out.println("setCommandLine");
    String newCommandLine = "";
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    instance.setCommandLine(newCommandLine);
  }

  /**
   * Test of isCommandLineLocked method, of class AbstractJssTextArea.
   */
  @Test
  public void testIsCommandLineLocked() {
    System.out.println("isCommandLineLocked");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    boolean expResult = false;
    boolean result = instance.isCommandLineLocked();
    assertEquals(expResult, result);
  }

  /**
   * Test of lockCommandLine method, of class AbstractJssTextArea.
   */
  @Test
  public void testLockCommandLine() {
    System.out.println("lockCommandLine");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    instance.lockCommandLine();
  }

  /**
   * Test of unlockCommandLine method, of class AbstractJssTextArea.
   */
  @Test
  public void testUnlockCommandLine() {
    System.out.println("unlockCommandLine");
    AbstractJssTextArea instance = new AbstractJssTextAreaImpl();
    instance.unlockCommandLine();
  }

  public class AbstractJssTextAreaImpl extends AbstractJssTextArea {

    public String getShellText() {
      return "";
    }

    public void setShellText(String newShellText) {}

    public boolean isShellTextAreaLocked() {
      return false;
    }

    public void lockShellTextArea() {}

    public void unlockShellTextArea() {}

    public String getCommandLine() {
      return "";
    }

    public void setCommandLine(String newCommandLine) {}

    public boolean isCommandLineLocked() {
      return false;
    }

    public void lockCommandLine() {}

    public void unlockCommandLine() {}
  }

}
