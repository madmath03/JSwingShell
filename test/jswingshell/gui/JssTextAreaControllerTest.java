/*
 * The MIT License
 *
 * Copyright 2016 brunot.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jswingshell.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import jswingshell.AbstractJssModel;
import jswingshell.IJssView;
import jswingshell.JssSimpleModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brunot
 */
public class JssTextAreaControllerTest {
    
    public JssTextAreaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getView method, of class JssTextAreaController.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        JssTextAreaController instance = new JssTextAreaController();
        JssTextArea notExpResult = null;
        JssTextArea result = instance.getView();
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of setView method, of class JssTextAreaController.
     */
    @Test
    public void testSetView() {
        System.out.println("setView");
        IJssView anotherView = new JssTextArea();
        JssTextAreaController instance = new JssTextAreaController();
        instance.setView(anotherView);
    }

    /**
     * Test of getModel method, of class JssTextAreaController.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        JssTextAreaController instance = new JssTextAreaController();
        AbstractJssModel notExpResult = null;
        AbstractJssModel result = instance.getModel();
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of setModel method, of class JssTextAreaController.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        JssTextAreaController instance = new JssTextAreaController();
        AbstractJssModel anotherModel = new JssSimpleModel(instance);
        instance.setModel(anotherModel);
    }

    /**
     * Test of setShellText method, of class JssTextAreaController.
     */
    @Test
    public void testSetShellText() {
        System.out.println("setShellText");
        String newShellText = "";
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.setShellText(newShellText);
    }

    /**
     * Test of addNewLineToShell method, of class JssTextAreaController.
     */
    @Test
    public void testAddNewLineToShell_0args() {
        System.out.println("addNewLineToShell");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.addNewLineToShell();
    }

    /**
     * Test of addNewLineToShell method, of class JssTextAreaController.
     */
    @Test
    public void testAddNewLineToShell_String() {
        System.out.println("addNewLineToShell");
        String text = "";
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.addNewLineToShell(text);
    }

    /**
     * Test of getLastShellLinePosition method, of class JssTextAreaController.
     */
    @Test
    public void testGetLastShellLinePosition() {
        System.out.println("getLastShellLinePosition");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        int expResult = 0;
        int result = instance.getLastShellLinePosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of addNewCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testAddNewCommandLine_0args() {
        System.out.println("addNewCommandLine");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.addNewCommandLine();
    }

    /**
     * Test of addNewCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testAddNewCommandLine_String() {
        System.out.println("addNewCommandLine");
        String newCommandLine = "";
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.addNewCommandLine(newCommandLine);
    }

    /**
     * Test of getCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testGetCommandLine() {
        System.out.println("getCommandLine");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        String expResult = "";
        String result = instance.getCommandLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testSetCommandLine() {
        System.out.println("setCommandLine");
        String newCommandLine = "";
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.setCommandLine(newCommandLine);
    }

    /**
     * Test of getCommandLinePosition method, of class JssTextAreaController.
     */
    @Test
    public void testGetCommandLinePosition() {
        System.out.println("getCommandLinePosition");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        int expResult = 4;
        int result = instance.getCommandLinePosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCaretPosition method, of class JssTextAreaController.
     */
    @Test
    public void testGetCaretPosition() {
        System.out.println("getCaretPosition");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        int expResult = 4;
        int result = instance.getCaretPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCaretPosition method, of class JssTextAreaController.
     */
    @Test
    public void testSetCaretPosition() {
        System.out.println("setCaretPosition");
        int caretPosition = 0;
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.setCaretPosition(caretPosition);
    }

    /**
     * Test of setCaretToEndOfDocument method, of class JssTextAreaController.
     */
    @Test
    public void testSetCaretToEndOfDocument() {
        System.out.println("setCaretToEndOfDocument");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.setCaretToEndOfDocument();
    }

    /**
     * Test of moveCaretPosition method, of class JssTextAreaController.
     */
    @Test
    public void testMoveCaretPosition() {
        System.out.println("moveCaretPosition");
        int caretPosition = 0;
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.moveCaretPosition(caretPosition);
    }

    /**
     * Test of isCaretBeforeStartOfCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testIsCaretBeforeStartOfCommandLine() {
        System.out.println("isCaretBeforeStartOfCommandLine");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        boolean expResult = false;
        boolean result = instance.isCaretBeforeStartOfCommandLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCaretBeforeOrEqualToStartOfCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testIsCaretBeforeOrEqualToStartOfCommandLine() {
        System.out.println("isCaretBeforeOrEqualToStartOfCommandLine");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        boolean expResult = true;
        boolean result = instance.isCaretBeforeOrEqualToStartOfCommandLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCaretAtStartOfCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testIsCaretAtStartOfCommandLine() {
        System.out.println("isCaretAtStartOfCommandLine");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        boolean expResult = true;
        boolean result = instance.isCaretAtStartOfCommandLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCaretAfterOrEqualToStartOfCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testIsCaretAfterOrEqualToStartOfCommandLine() {
        System.out.println("isCaretAfterOrEqualToStartOfCommandLine");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        boolean expResult = true;
        boolean result = instance.isCaretAfterOrEqualToStartOfCommandLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCaretAfterStartOfCommandLine method, of class JssTextAreaController.
     */
    @Test
    public void testIsCaretAfterStartOfCommandLine() {
        System.out.println("isCaretAfterStartOfCommandLine");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        boolean expResult = false;
        boolean result = instance.isCaretAfterStartOfCommandLine();
        assertEquals(expResult, result);
    }

    /**
     * Test of keyPressed method, of class JssTextAreaController.
     */
    @Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent evt = null;
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.keyPressed(evt);
    }

    /**
     * Test of fixSelection method, of class JssTextAreaController.
     */
    @Test
    public void testFixSelection() {
        System.out.println("fixSelection");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.fixSelection();
    }

    /**
     * Test of getBackground method, of class JssTextAreaController.
     */
    @Test
    public void testGetBackground() {
        System.out.println("getBackground");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        Color expResult = Color.BLACK;
        Color result = instance.getBackground();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBackground method, of class JssTextAreaController.
     */
    @Test
    public void testSetBackground() {
        System.out.println("setBackground");
        Color backgroundColor = Color.WHITE;
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.setBackground(backgroundColor);
    }

    /**
     * Test of getForeground method, of class JssTextAreaController.
     */
    @Test
    public void testGetForeground() {
        System.out.println("getForeground");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        Color expResult = Color.WHITE;
        Color result = instance.getForeground();
        assertEquals(expResult, result);
    }

    /**
     * Test of setForeground method, of class JssTextAreaController.
     */
    @Test
    public void testSetForeground() {
        System.out.println("setForeground");
        Color foregroundColor = Color.BLACK;
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.setForeground(foregroundColor);
    }

    /**
     * Test of getTabSize method, of class JssTextAreaController.
     */
    @Test
    public void testGetTabSize() {
        System.out.println("getTabSize");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        int expResult = 2;
        int result = instance.getTabSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTabSize method, of class JssTextAreaController.
     */
    @Test
    public void testSetTabSize() {
        System.out.println("setTabSize");
        int size = 0;
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.setTabSize(size);
    }

    /**
     * Test of getFont method, of class JssTextAreaController.
     */
    @Test
    public void testGetFont() {
        System.out.println("getFont");
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        Font notExpResult = null;
        Font result = instance.getFont();
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of setFont method, of class JssTextAreaController.
     */
    @Test
    public void testSetFont() {
        System.out.println("setFont");
        Font f = null;
        JssTextAreaController instance = new JssTextAreaController(new JssTextArea());
        instance.setFont(f);
    }
    
}
