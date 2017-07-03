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
package jswingshell.action;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;
import java.util.Collection;

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
public class AbstractJssActionTest {

  public AbstractJssActionTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of getCommandIdentifiersAsString method, of class AbstractJssAction.
   */
  @Test
  public void testGetCommandIdentifiersAsString_IJssAction() {
    System.out.println("getCommandIdentifiersAsString");
    IJssAction action = null;
    String expResult = null;
    String result = AbstractJssAction.getCommandIdentifiersAsString(action);
    assertEquals(expResult, result);
  }

  /**
   * Test of getArgumentsAsString method, of class AbstractJssAction.
   */
  @Test
  public void testGetArgumentsAsString_Collection() {
    System.out.println("getArgumentsAsString");
    Collection<String> arguments = null;
    String expResult = "[  ]";
    String result = AbstractJssAction.getArgumentsAsString(arguments);
    assertEquals(expResult, result);
  }

  /**
   * Test of getArgumentsAsString method, of class AbstractJssAction.
   */
  @Test
  public void testGetArgumentsAsString_ObjectArr() {
    System.out.println("getArgumentsAsString");
    Object[] arguments = null;
    String expResult = "[  ]";
    String result = AbstractJssAction.getArgumentsAsString(arguments);
    assertEquals(expResult, result);
  }

  /**
   * Test of getDefaultShellController method, of class AbstractJssAction.
   */
  @Test
  public void testGetDefaultShellController() {
    System.out.println("getDefaultShellController");
    AbstractJssAction instance = new AbstractJssActionImpl();
    IJssController expResult = null;
    IJssController result = instance.getDefaultShellController();
    assertEquals(expResult, result);
  }

  /**
   * Test of setDefaultShellController method, of class AbstractJssAction.
   */
  @Test
  public void testSetDefaultShellController() {
    System.out.println("setDefaultShellController");
    IJssController shellController = null;
    AbstractJssAction instance = new AbstractJssActionImpl();
    instance.setDefaultShellController(shellController);
  }

  /**
   * Test of getDefaultArguments method, of class AbstractJssAction.
   */
  @Test
  public void testGetDefaultArguments() {
    System.out.println("getDefaultArguments");
    AbstractJssAction instance = new AbstractJssActionImpl();
    String[] expResult = null;
    String[] result = instance.getDefaultArguments();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of setDefaultArguments method, of class AbstractJssAction.
   */
  @Test
  public void testSetDefaultArguments() {
    System.out.println("setDefaultArguments");
    String[] args = null;
    AbstractJssAction instance = new AbstractJssActionImpl();
    instance.setDefaultArguments(args);
  }

  /**
   * Test of getDefaultCommandIdentifier method, of class AbstractJssAction.
   */
  @Test
  public void testGetDefaultCommandIdentifier() {
    System.out.println("getDefaultCommandIdentifier");
    AbstractJssAction instance = new AbstractJssActionImpl();
    String expResult = null;
    String result = instance.getDefaultCommandIdentifier();
    assertEquals(expResult, result);
  }

  /**
   * Test of getCommandIdentifiers method, of class AbstractJssAction.
   */
  @Test
  public void testGetCommandIdentifiers() {
    System.out.println("getCommandIdentifiers");
    AbstractJssAction instance = new AbstractJssActionImpl();
    String[] expResult = null;
    String[] result = instance.getCommandIdentifiers();
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of getCommandIdentifiersAsString method, of class AbstractJssAction.
   */
  @Test
  public void testGetCommandIdentifiersAsString_0args() {
    System.out.println("getCommandIdentifiersAsString");
    AbstractJssAction instance = new AbstractJssActionImpl();
    String expResult = "{  }";
    String result = instance.getCommandIdentifiersAsString();
    assertEquals(expResult, result);
  }

  /**
   * Test of getHelp method, of class AbstractJssAction.
   */
  @Test
  public void testGetHelp() {
    System.out.println("getHelp");
    AbstractJssAction instance = new AbstractJssActionImpl();
    String expResult = "";
    String result = instance.getHelp();
    assertEquals(expResult, result);
  }

  /**
   * Test of run method, of class AbstractJssAction.
   */
  @Test
  public void testRun_0args() {
    System.out.println("run");
    AbstractJssAction instance = new AbstractJssActionImpl();
    int expResult = 0;
    int result = instance.run();
    assertEquals(expResult, result);
  }

  /**
   * Test of run method, of class AbstractJssAction.
   */
  @Test
  public void testRun_StringArr() {
    System.out.println("run");
    String[] args = null;
    AbstractJssAction instance = new AbstractJssActionImpl();
    int expResult = 0;
    int result = instance.run(args);
    assertEquals(expResult, result);
  }

  /**
   * Test of run method, of class AbstractJssAction.
   */
  @Test
  public void testRun_IJssController_StringArr() {
    System.out.println("run");
    IJssController shellController = null;
    String[] args = null;
    AbstractJssAction instance = new AbstractJssActionImpl();
    int expResult = 0;
    int result = instance.run(shellController, args);
    assertEquals(expResult, result);
  }

  /**
   * Test of actionPerformed method, of class AbstractJssAction.
   */
  @Test
  public void testActionPerformed() {
    System.out.println("actionPerformed");
    ActionEvent e = null;
    AbstractJssAction instance = new AbstractJssActionImpl();
    instance.actionPerformed(e);
  }

  /**
   * Test of extractArgumentsFromEvent method, of class AbstractJssAction.
   */
  @Test
  public void testExtractArgumentsFromEvent() {
    System.out.println("extractArgumentsFromEvent");
    ActionEvent e = null;
    AbstractJssAction instance = new AbstractJssActionImpl();
    String[] expResult = null;
    String[] result = instance.extractArgumentsFromEvent(e);
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of compareTo method, of class AbstractJssAction.
   */
  @Test
  public void testCompareTo() {
    System.out.println("compareTo");
    IJssAction o = new AbstractJssActionImpl();
    AbstractJssAction instance = new AbstractJssActionImpl();
    int expResult = 0;
    int result = instance.compareTo(o);
    assertEquals(expResult, result);
  }

  public class AbstractJssActionImpl extends AbstractJssAction {

    public String[] getCommandIdentifiers() {
      return null;
    }

    public int run(IJssController shellController, String... args) {
      return 0;
    }

    @Override
    public String getHelp(IJssController shellController) {
      return "";
    }

    @Override
    public String getBriefHelp() {
      return "";
    }
  }

}
