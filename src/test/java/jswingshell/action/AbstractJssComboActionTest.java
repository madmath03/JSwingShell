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
import static org.junit.Assert.assertNotEquals;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

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
public class AbstractJssComboActionTest {

  public AbstractJssComboActionTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of setModel method, of class AbstractJssComboAction.
   */
  @Test
  public void testSetModel() {
    System.out.println("setModel");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    instance.setModel(null);
  }

  /**
   * Test of getModel method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetModel() {
    System.out.println("getModel");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    ComboBoxModel notExpResult = null;
    ComboBoxModel result = instance.getModel();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of setSelectedItem method, of class AbstractJssComboAction.
   */
  @Test
  public void testSetSelectedItem() {
    System.out.println("setSelectedItem");
    Object anItem = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    instance.setSelectedItem(anItem);
  }

  /**
   * Test of getSelectedItem method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetSelectedItem() {
    System.out.println("getSelectedItem");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    Object expResult = null;
    Object result = instance.getSelectedItem();
    assertEquals(expResult, result);
  }

  /**
   * Test of getSize method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetSize() {
    System.out.println("getSize");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    int expResult = 0;
    int result = instance.getSize();
    assertEquals(expResult, result);
  }

  /**
   * Test of getElementAt method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetElementAt() {
    System.out.println("getElementAt");
    int index = 0;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    Object expResult = null;
    Object result = instance.getElementAt(index);
    assertEquals(expResult, result);
  }

  /**
   * Test of addListDataListener method, of class AbstractJssComboAction.
   */
  @Test
  public void testAddListDataListener() {
    System.out.println("addListDataListener");
    ListDataListener l = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    instance.addListDataListener(l);
  }

  /**
   * Test of removeListDataListener method, of class AbstractJssComboAction.
   */
  @Test
  public void testRemoveListDataListener() {
    System.out.println("removeListDataListener");
    ListDataListener l = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    instance.removeListDataListener(l);
  }

  /**
   * Test of putValue method, of class AbstractJssComboAction.
   */
  @Test
  public void testPutValue() {
    System.out.println("putValue");
    String key = "";
    Object newValue = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    instance.putValue(key, newValue);
  }

  /**
   * Test of setEnabled method, of class AbstractJssComboAction.
   */
  @Test
  public void testSetEnabled() {
    System.out.println("setEnabled");
    boolean newValue = false;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    instance.setEnabled(newValue);
  }

  /**
   * Test of getHelp method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetHelp() {
    System.out.println("getHelp");
    IJssController shellController = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    String notExpResult = "";
    String result = instance.getHelp(shellController);
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of extractArgumentsFromEvent method, of class AbstractJssComboAction.
   */
  @Test
  public void testExtractArgumentsFromEvent() {
    System.out.println("extractArgumentsFromEvent");
    ActionEvent e = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    String[] expResult = null;
    String[] result = instance.extractArgumentsFromEvent(e);
    assertArrayEquals(expResult, result);
  }

  /**
   * Test of run method, of class AbstractJssComboAction.
   */
  @Test
  public void testRun() {
    System.out.println("run");
    IJssController shellController = null;
    String[] args = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    int expResult = IJssAction.ERROR;
    int result = instance.run(shellController, args);
    assertEquals(expResult, result);
  }

  /**
   * Test of getSwitchValuesByArgument method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetSwitchValuesByArgument() {
    System.out.println("getSwitchValuesByArgument");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    Map notExpResult = null;
    Map result = instance.getSwitchValuesByArgument();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of getSwitchArgumentsByValue method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetSwitchArgumentsByValue() {
    System.out.println("getSwitchArgumentsByValue");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    Map notExpResult = null;
    Map result = instance.getSwitchArgumentsByValue();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of doSwitch method, of class AbstractJssComboAction.
   */
  @Test
  public void testDoSwitch_GenericType() {
    System.out.println("doSwitch");
    Object switchValue = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    boolean expResult = false;
    boolean result = instance.doSwitch(switchValue);
    assertEquals(expResult, result);
  }

  /**
   * Test of doSwitch method, of class AbstractJssComboAction.
   */
  @Test
  public void testDoSwitch_IJssController_GenericType() {
    System.out.println("doSwitch");
    IJssController shellController = null;
    Object switchValue = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    boolean expResult = false;
    boolean result = instance.doSwitch(shellController, switchValue);
    assertEquals(expResult, result);
  }

  /**
   * Test of constructArgumentsByValue method, of class AbstractJssComboAction.
   */
  @Test
  public void testConstructArgumentsByValue() {
    System.out.println("constructArgumentsByValue");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    Map notExpResult = null;
    Map result = instance.constructArgumentsByValue();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of getInnerGroup method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetInnerGroup() {
    System.out.println("getInnerGroup");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    ActionGroup expResult = null;
    ActionGroup result = instance.getInnerGroup();
    assertEquals(expResult, result);
  }

  /**
   * Test of setInnerGroup method, of class AbstractJssComboAction.
   */
  @Test
  public void testSetInnerGroup() {
    System.out.println("setInnerGroup");
    ActionGroup innerGroup = null;
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    instance.setInnerGroup(innerGroup);
  }

  /**
   * Test of getInnerElementActions method, of class AbstractJssComboAction.
   */
  @Test
  public void testGetInnerElementActions() {
    System.out.println("getInnerElementActions");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    Collection<AbstractJssComboAction<String>.ComboElementAction<String>> notExpResult =
        null;
    Collection<AbstractJssComboAction<String>.ComboElementAction<String>> result =
        instance.getInnerElementActions();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of hasInnerElementActions method, of class AbstractJssComboAction.
   */
  @Test
  public void testHasInnerElementActions() {
    System.out.println("hasInnerElementActions");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    boolean expResult = false;
    boolean result = instance.hasInnerElementActions();
    assertEquals(expResult, result);
  }

  /**
   * Test of initInnerElements method, of class AbstractJssComboAction.
   */
  @Test
  public void testInitInnerElements() {
    System.out.println("initInnerElements");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    Collection<AbstractJssComboAction<String>.ComboElementAction<String>> notExpResult =
        null;
    Collection<AbstractJssComboAction<String>.ComboElementAction<String>> result =
        instance.initInnerElements();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of resetInnerElements method, of class AbstractJssComboAction.
   */
  @Test
  public void testResetInnerElements() {
    System.out.println("resetInnerElements");
    AbstractJssComboAction instance = new AbstractJssComboActionImpl();
    instance.resetInnerElements();
  }

  public class AbstractJssComboActionImpl
      extends AbstractJssComboAction<String> {

    public boolean doSwitch(IJssController shellController,
        String switchValue) {
      return false;
    }

    @Override
    public String[] getCommandIdentifiers() {
      return new String[] {};
    }

    @Override
    public String getBriefHelp() {
      return "";
    }
  }

}
