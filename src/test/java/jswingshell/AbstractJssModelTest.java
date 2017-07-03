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
package jswingshell;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jswingshell.action.IJssAction;

/**
 *
 * @author brunot
 */
public class AbstractJssModelTest {

  public AbstractJssModelTest() {}

  @BeforeClass
  public static void setUpClass() {}

  @AfterClass
  public static void tearDownClass() {}

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  /**
   * Test of getController method, of class AbstractJssModel.
   */
  @Test
  public void testGetController() {
    System.out.println("getController");
    AbstractJssModel instance = new AbstractJssModelImpl();
    IJssController expResult = null;
    IJssController result = instance.getController();
    assertEquals(expResult, result);
  }

  /**
   * Test of setController method, of class AbstractJssModel.
   */
  @Test
  public void testSetController() {
    System.out.println("setController");
    IJssController anotherController = null;
    AbstractJssModel instance = new AbstractJssModelImpl();
    instance.setController(anotherController);
  }

  /**
   * Test of getAvailableActions method, of class AbstractJssModel.
   */
  @Test
  public void testGetAvailableActions() {
    System.out.println("getAvailableActions");
    AbstractJssModel instance = new AbstractJssModelImpl();
    Set<IJssAction> notExpResult = null;
    Set<IJssAction> result = instance.getAvailableActions();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of clear method, of class AbstractJssModel.
   */
  @Test
  public void testClear() {
    System.out.println("clear");
    AbstractJssModel instance = new AbstractJssModelImpl();
    instance.clear();
  }

  /**
   * Test of add method, of class AbstractJssModel.
   */
  @Test
  public void testAdd() {
    System.out.println("add");
    IJssAction action = null;
    AbstractJssModel instance = new AbstractJssModelImpl();
    boolean expResult = true;
    boolean result = instance.add(action);
    assertEquals(expResult, result);
  }

  /**
   * Test of addAll method, of class AbstractJssModel.
   */
  @Test
  public void testAddAll() {
    System.out.println("addAll");
    Collection<? extends IJssAction> actions = Collections.emptyList();
    AbstractJssModel instance = new AbstractJssModelImpl();
    boolean expResult = false;
    boolean result = instance.addAll(actions);
    assertEquals(expResult, result);
  }

  /**
   * Test of remove method, of class AbstractJssModel.
   */
  @Test
  public void testRemove() {
    System.out.println("remove");
    IJssAction action = null;
    AbstractJssModel instance = new AbstractJssModelImpl();
    boolean expResult = false;
    boolean result = instance.remove(action);
    assertEquals(expResult, result);
  }

  /**
   * Test of removeAll method, of class AbstractJssModel.
   */
  @Test
  public void testRemoveAll() {
    System.out.println("removeAll");
    Collection<? extends IJssAction> actions = Collections.emptyList();
    AbstractJssModel instance = new AbstractJssModelImpl();
    boolean expResult = false;
    boolean result = instance.removeAll(actions);
    assertEquals(expResult, result);
  }

  /**
   * Test of contains method, of class AbstractJssModel.
   */
  @Test
  public void testContains() {
    System.out.println("contains");
    IJssAction action = null;
    AbstractJssModel instance = new AbstractJssModelImpl();
    boolean expResult = false;
    boolean result = instance.contains(action);
    assertEquals(expResult, result);
  }

  /**
   * Test of containsAll method, of class AbstractJssModel.
   */
  @Test
  public void testContainsAll() {
    System.out.println("containsAll");
    Collection<? extends IJssAction> actions = Collections.emptyList();
    AbstractJssModel instance = new AbstractJssModelImpl();
    boolean expResult = true;
    boolean result = instance.containsAll(actions);
    assertEquals(expResult, result);
  }

  /**
   * Test of retainAll method, of class AbstractJssModel.
   */
  @Test
  public void testRetainAll() {
    System.out.println("retainAll");
    Collection<? extends IJssAction> actions = Collections.emptyList();
    AbstractJssModel instance = new AbstractJssModelImpl();
    boolean expResult = false;
    boolean result = instance.retainAll(actions);
    assertEquals(expResult, result);
  }

  /**
   * Test of isEmpty method, of class AbstractJssModel.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    AbstractJssModel instance = new AbstractJssModelImpl();
    boolean expResult = true;
    boolean result = instance.isEmpty();
    assertEquals(expResult, result);
  }

  /**
   * Test of size method, of class AbstractJssModel.
   */
  @Test
  public void testSize() {
    System.out.println("size");
    AbstractJssModel instance = new AbstractJssModelImpl();
    int expResult = 0;
    int result = instance.size();
    assertEquals(expResult, result);
  }

  /**
   * Test of getActionsByCommandIdentifier method, of class AbstractJssModel.
   */
  @Test
  public void testGetActionsByCommandIdentifier() {
    System.out.println("getActionsByCommandIdentifier");
    AbstractJssModel instance = new AbstractJssModelImpl();
    Map<String, IJssAction> notExpResult = null;
    Map<String, IJssAction> result = instance.getActionsByCommandIdentifier();
    assertNotEquals(notExpResult, result);
  }

  /**
   * Test of getActionForCommandIdentifier method, of class AbstractJssModel.
   */
  @Test
  public void testGetActionForCommandIdentifier() {
    System.out.println("getActionForCommandIdentifier");
    String commandIdentifier = "";
    AbstractJssModel instance = new AbstractJssModelImpl();
    IJssAction expResult = null;
    IJssAction result = instance.getActionForCommandIdentifier(commandIdentifier);
    assertEquals(expResult, result);
  }

  public class AbstractJssModelImpl extends AbstractJssModel {
  }

}
