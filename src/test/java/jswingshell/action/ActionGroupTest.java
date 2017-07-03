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

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jswingshell.IJssController;

/**
 * Test Action Group.
 *
 * @author Mathieu Brunot
 */
public class ActionGroupTest {

  public ActionGroupTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of add method, of class ActionGroup.
   */
  @Test
  public void testAdd() {
    System.out.println("add");
    AbstractJssSwitchAction a = null;
    ActionGroup instance = new ActionGroup();
    instance.add(a);
  }

  /**
   * Test of remove method, of class ActionGroup.
   */
  @Test
  public void testRemove() {
    System.out.println("remove");
    AbstractJssSwitchAction a = null;
    ActionGroup instance = new ActionGroup();
    instance.remove(a);
  }

  /**
   * Test of clearSelection method, of class ActionGroup.
   */
  @Test
  public void testClearSelection() {
    System.out.println("clearSelection");
    ActionGroup instance = new ActionGroup();
    instance.clearSelection();
  }

  /**
   * Test of getElements method, of class ActionGroup.
   */
  @Test
  public void testGetElements() {
    System.out.println("getElements");
    ActionGroup instance = new ActionGroup();
    boolean expResult = true;
    boolean result = instance.getElements().isEmpty();
    assertEquals(expResult, result);
  }

  /**
   * Test of getSelection method, of class ActionGroup.
   */
  @Test
  public void testGetSelection() {
    System.out.println("getSelection");
    ActionGroup instance = new ActionGroup();
    AbstractJssSwitchAction expResult = null;
    AbstractJssSwitchAction result = instance.getSelection();
    assertEquals(expResult, result);
  }

  /**
   * Test of setSelected method, of class ActionGroup.
   */
  @Test
  public void testSetSelected() {
    System.out.println("setSelected");
    AbstractJssSwitchAction a = new AbstractJssSwitchAction() {
      @Override
      protected boolean doSwitch(IJssController shellController, Boolean switchValue) {
        System.out.println("DummyAction.doSwitch");
        return true;
      }

      @Override
      public String[] getCommandIdentifiers() {
        return new String[] {};
      }

      @Override
      public String getBriefHelp() {
        return "DummyAction";
      }
    };
    boolean b = false;
    ActionGroup instance = new ActionGroup();
    instance.setSelected(a, b);
  }

  /**
   * Test of isSelected method, of class ActionGroup.
   */
  @Test
  public void testIsSelected() {
    System.out.println("isSelected");
    AbstractJssSwitchAction a = null;
    ActionGroup instance = new ActionGroup();
    boolean expResult = true;
    boolean result = instance.isSelected(a);
    assertEquals(expResult, result);
  }

  /**
   * Test of getSelection method, setSelected method, isSelected method, of class ActionGroup.
   */
  @Test
  public void testSelection() {
    System.out.println("selection");
    AbstractJssSwitchAction a = new AbstractJssSwitchAction() {
      @Override
      protected boolean doSwitch(IJssController shellController, Boolean switchValue) {
        System.out.println("DummyAction.doSwitch");
        return true;
      }

      @Override
      public String[] getCommandIdentifiers() {
        return new String[] {};
      }

      @Override
      public String getBriefHelp() {
        return "DummyAction";
      }
    };
    boolean b = true;
    ActionGroup instance = new ActionGroup();
    instance.setSelected(a, b);

    AbstractJssSwitchAction getSelectionExpResult = a;
    AbstractJssSwitchAction getSelectionResult = instance.getSelection();
    assertEquals(getSelectionExpResult, getSelectionResult);

    boolean isSelectedExpResult = true;
    boolean isSelectedResult = instance.isSelected(a);
    assertEquals(isSelectedExpResult, isSelectedResult);
  }

  /**
   * Test of getActionCount method, of class ActionGroup.
   */
  @Test
  public void testGetActionCount() {
    System.out.println("getActionCount");
    ActionGroup instance = new ActionGroup();
    int expResult = 0;
    int result = instance.getActionCount();
    assertEquals(expResult, result);
  }

  /**
   * Test of setEnabled method, of class ActionGroup.
   */
  @Test
  public void testSetEnabled() {
    System.out.println("setEnabled");
    boolean newValue = false;
    ActionGroup instance = new ActionGroup();
    instance.setEnabled(newValue);
  }

}
