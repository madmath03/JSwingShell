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
package jswingshell;

import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import jswingshell.action.AbstractThreadedJssAction;
import jswingshell.action.IJssAction;
import jswingshell.gui.AbstractJssTextArea;
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
public class AbstractJssControllerTest {
    
    public AbstractJssControllerTest() {
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
     * Test of getView method, of class AbstractJssController.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        AbstractJssController instance = new AbstractJssControllerImpl();
        IJssView expResult = null;
        IJssView result = instance.getView();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setView method, of class AbstractJssController.
     */
    @Test
    public void testSetView() {
        System.out.println("setView");
        IJssView anotherView = null;
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.setView(anotherView);
    }

    /**
     * Test of getModel method, of class AbstractJssController.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        AbstractJssController instance = new AbstractJssControllerImpl();
        AbstractJssModel notExpResult = null;
        AbstractJssModel result = instance.getModel();
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of setModel method, of class AbstractJssController.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        AbstractJssModel anotherModel = null;
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.setModel(anotherModel);
    }

    /**
     * Test of getCommandHistory method, of class AbstractJssController.
     */
    @Test
    public void testGetCommandHistory() {
        System.out.println("getCommandHistory");
        AbstractJssController instance = new AbstractJssControllerImpl();
        AbstractJssController.CommandHistory notExpResult = null;
        AbstractJssController.CommandHistory result = instance.getCommandHistory();
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of resetCommandHistory method, of class AbstractJssController.
     */
    @Test
    public void testResetCommandHistory() {
        System.out.println("resetCommandHistory");
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.resetCommandHistory();
    }

    /**
     * Test of addToCommandHistory method, of class AbstractJssController.
     */
    @Test
    public void testAddToCommandHistory() {
        System.out.println("addToCommandHistory");
        String command = "";
        AbstractJssController instance = new AbstractJssControllerImpl();
        boolean expResult = true;
        boolean result = instance.addToCommandHistory(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of clearCommandHistory method, of class AbstractJssController.
     */
    @Test
    public void testClearCommandHistory() {
        System.out.println("clearCommandHistory");
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.clearCommandHistory();
    }

    /**
     * Test of commandHistorySize method, of class AbstractJssController.
     */
    @Test
    public void testCommandHistorySize() {
        System.out.println("commandHistorySize");
        AbstractJssController instance = new AbstractJssControllerImpl();
        int expResult = 0;
        int result = instance.commandHistorySize();
        assertEquals(expResult, result);
    }

    /**
     * Test of isCommandHistoryEmpty method, of class AbstractJssController.
     */
    @Test
    public void testIsCommandHistoryEmpty() {
        System.out.println("isCommandHistoryEmpty");
        AbstractJssController instance = new AbstractJssControllerImpl();
        boolean expResult = true;
        boolean result = instance.isCommandHistoryEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentHistoryPosition method, of class AbstractJssController.
     */
    @Test
    public void testGetCurrentHistoryPosition() {
        System.out.println("getCurrentHistoryPosition");
        AbstractJssController instance = new AbstractJssControllerImpl();
        int expResult = 0;
        int result = instance.getCurrentHistoryPosition();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentHistoryCommand method, of class AbstractJssController.
     */
    @Test
    public void testGetCurrentHistoryCommand() {
        System.out.println("getCurrentHistoryCommand");
        AbstractJssController instance = new AbstractJssControllerImpl();
        String expResult = null;
        String result = instance.getCurrentHistoryCommand();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPreviousCommand method, of class AbstractJssController.
     */
    @Test
    public void testGetPreviousCommand() {
        System.out.println("getPreviousCommand");
        AbstractJssController instance = new AbstractJssControllerImpl();
        String expResult = null;
        String result = instance.getPreviousCommand();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNextCommand method, of class AbstractJssController.
     */
    @Test
    public void testGetNextCommand() {
        System.out.println("getNextCommand");
        AbstractJssController instance = new AbstractJssControllerImpl();
        String expResult = null;
        String result = instance.getNextCommand();
        assertEquals(expResult, result);
    }

    /**
     * Test of getShellText method, of class AbstractJssController.
     */
    @Test
    public void testGetShellText() {
        System.out.println("getShellText");
        AbstractJssController instance = new AbstractJssControllerImpl();
        String expResult = "";
        String result = instance.getShellText();
        assertEquals(expResult, result);
    }

    /**
     * Test of setShellText method, of class AbstractJssController.
     */
    @Test
    public void testSetShellText() {
        System.out.println("setShellText");
        String newShellText = "";
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.setShellText(newShellText);
    }

    /**
     * Test of clearShell method, of class AbstractJssController.
     */
    @Test
    public void testClearShell() {
        System.out.println("clearShell");
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.clearShell();
    }

    /**
     * Test of isShellTextAreaLocked method, of class AbstractJssController.
     */
    @Test
    public void testIsShellTextAreaLocked() {
        System.out.println("isShellTextAreaLocked");
        AbstractJssController instance = new AbstractJssControllerImpl();
        boolean expResult = true;
        boolean result = instance.isShellTextAreaLocked();
        assertEquals(expResult, result);
    }

    /**
     * Test of lockShellTextArea method, of class AbstractJssController.
     */
    @Test
    public void testLockShellTextArea() {
        System.out.println("lockShellTextArea");
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.lockShellTextArea();
    }

    /**
     * Test of unlockShellTextArea method, of class AbstractJssController.
     */
    @Test
    public void testUnlockShellTextArea() {
        System.out.println("unlockShellTextArea");
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.unlockShellTextArea();
    }

    /**
     * Test of getCommandLinePrefix method, of class AbstractJssController.
     */
    @Test
    public void testGetCommandLinePrefix() {
        System.out.println("getCommandLinePrefix");
        AbstractJssController instance = new AbstractJssControllerImpl();
        String notExpResult = "";
        String result = instance.getCommandLinePrefix();
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of clearCommandLine method, of class AbstractJssController.
     */
    @Test
    public void testClearCommandLine() {
        System.out.println("clearCommandLine");
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.clearCommandLine();
    }

    /**
     * Test of isCommandLineLocked method, of class AbstractJssController.
     */
    @Test
    public void testIsCommandLineLocked() {
        System.out.println("isCommandLineLocked");
        AbstractJssController instance = new AbstractJssControllerImpl();
        boolean expResult = true;
        boolean result = instance.isCommandLineLocked();
        assertEquals(expResult, result);
    }

    /**
     * Test of lockCommandLine method, of class AbstractJssController.
     */
    @Test
    public void testLockCommandLine() {
        System.out.println("lockCommandLine");
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.lockCommandLine();
    }

    /**
     * Test of unlockCommandLine method, of class AbstractJssController.
     */
    @Test
    public void testUnlockCommandLine() {
        System.out.println("unlockCommandLine");
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.unlockCommandLine();
    }

    /**
     * Test of getCommandLineParser method, of class AbstractJssController.
     */
    @Test
    public void testGetCommandLineParser() {
        System.out.println("getCommandLineParser");
        AbstractJssController instance = new AbstractJssControllerImpl();
        AbstractJssController.CommandLineParser notExpResult = null;
        AbstractJssController.CommandLineParser result = instance.getCommandLineParser();
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of getCommandParameterSeparator method, of class AbstractJssController.
     */
    @Test
    public void testGetCommandParameterSeparator() {
        System.out.println("getCommandParameterSeparator");
        AbstractJssController instance = new AbstractJssControllerImpl();
        String expResult = AbstractJssController.COMMAND_PARAMETER_SEPARATOR;
        String result = instance.getCommandParameterSeparator();
        assertEquals(expResult, result);
    }

    /**
     * Test of extractCommand method, of class AbstractJssController.
     */
    @Test
    public void testExtractCommand() {
        System.out.println("extractCommand");
        AbstractJssController instance = new AbstractJssControllerImpl();
        String expResult = "";
        String result = instance.extractCommand();
        assertEquals(expResult, result);
    }

    /**
     * Test of extractCommandParameters method, of class AbstractJssController.
     */
    @Test
    public void testExtractCommandParameters() {
        System.out.println("extractCommandParameters");
        String commandLine = "";
        AbstractJssController instance = new AbstractJssControllerImpl();
        String[] expResult = null;
        String[] result = instance.extractCommandParameters(commandLine);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of interpretCommand method, of class AbstractJssController.
     */
    @Test
    public void testInterpretCommand_0args() {
        System.out.println("interpretCommand");
        AbstractJssController instance = new AbstractJssControllerImpl();
        int expResult = AbstractJssController.COMMAND_EMPTY_STATUS;
        int result = instance.interpretCommand();
        assertEquals(expResult, result);
    }

    /**
     * Test of interpretCommand method, of class AbstractJssController.
     */
    @Test
    public void testInterpretCommand_String() {
        System.out.println("interpretCommand");
        String command = "";
        AbstractJssController instance = new AbstractJssControllerImpl();
        int expResult = AbstractJssController.COMMAND_EMPTY_STATUS;
        int result = instance.interpretCommand(command);
        assertEquals(expResult, result);
    }

    /**
     * Test of interpretCommand method, of class AbstractJssController.
     */
    @Test
    public void testInterpretCommand_String_boolean() {
        System.out.println("interpretCommand");
        String command = "";
        boolean addCommandToHistory = false;
        AbstractJssController instance = new AbstractJssControllerImpl();
        int expResult = AbstractJssController.COMMAND_EMPTY_STATUS;
        int result = instance.interpretCommand(command, addCommandToHistory);
        assertEquals(expResult, result);
    }

    /**
     * Test of interpret method, of class AbstractJssController.
     */
    @Test
    public void testInterpret() {
        System.out.println("interpret");
        AbstractJssController instance = new AbstractJssControllerImpl();
        int expResult = 0;
        int result = instance.interpret();
        assertEquals(expResult, result);
    }

    /**
     * Test of publish method, of class AbstractJssController.
     */
    @Test
    public void testPublish() {
        System.out.println("publish");
        IJssController.PublicationLevel level = null;
        String message = "";
        AbstractJssController instance = new AbstractJssControllerImpl();
        boolean expResult = false;
        boolean result = instance.publish(level, message);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPublicationLevel method, of class AbstractJssController.
     */
    @Test
    public void testGetPublicationLevel() {
        System.out.println("getPublicationLevel");
        AbstractJssController instance = new AbstractJssControllerImpl();
        IJssController.PublicationLevel notExpResult = null;
        IJssController.PublicationLevel result = instance.getPublicationLevel();
        assertNotEquals(notExpResult, result);
    }

    /**
     * Test of setPublicationLevel method, of class AbstractJssController.
     */
    @Test
    public void testSetPublicationLevel() {
        System.out.println("setPublicationLevel");
        IJssController.PublicationLevel level = null;
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.setPublicationLevel(level);
    }

    /**
     * Test of getCurrentAction method, of class AbstractJssController.
     */
    @Test
    public void testGetCurrentAction() {
        System.out.println("getCurrentAction");
        AbstractJssController instance = new AbstractJssControllerImpl();
        IJssAction expResult = null;
        IJssAction result = instance.getCurrentAction();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActionsInProgress method, of class AbstractJssController.
     */
    @Test
    public void testGetActionsInProgress() {
        System.out.println("getActionsInProgress");
        AbstractJssController instance = new AbstractJssControllerImpl();
        List<AbstractThreadedJssAction> expResult = Collections.emptyList();
        List<AbstractThreadedJssAction> result = instance.getActionsInProgress();
        assertEquals(expResult, result);
    }

    /**
     * Test of removeEndedAction method, of class AbstractJssController.
     */
    @Test
    public void testRemoveEndedAction() {
        System.out.println("removeEndedAction");
        IJssAction endedAction = null;
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.removeEndedAction(endedAction);
    }

    /**
     * Test of addPropertyChangeListener method, of class AbstractJssController.
     */
    @Test
    public void testAddPropertyChangeListener() {
        System.out.println("addPropertyChangeListener");
        PropertyChangeListener listener = null;
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.addPropertyChangeListener(listener);
    }

    /**
     * Test of removePropertyChangeListener method, of class AbstractJssController.
     */
    @Test
    public void testRemovePropertyChangeListener() {
        System.out.println("removePropertyChangeListener");
        PropertyChangeListener listener = null;
        AbstractJssController instance = new AbstractJssControllerImpl();
        instance.removePropertyChangeListener(listener);
    }

    /**
     * Test of getPropertyChangeListeners method, of class AbstractJssController.
     */
    @Test
    public void testGetPropertyChangeListeners() {
        System.out.println("getPropertyChangeListeners");
        AbstractJssController instance = new AbstractJssControllerImpl();
        List<PropertyChangeListener> expResult = Collections.emptyList();
        List<PropertyChangeListener> result = instance.getPropertyChangeListeners();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailableActions method, of class AbstractJssController.
     */
    @Test
    public void testGetAvailableActions() {
        System.out.println("getAvailableActions");
        AbstractJssController instance = new AbstractJssControllerImpl();
        Set<IJssAction> expResult = Collections.emptySet();
        Set<IJssAction> result = instance.getAvailableActions();
        assertEquals(expResult, result);
    }

    /**
     * Test of getActionForCommandIdentifier method, of class AbstractJssController.
     */
    @Test
    public void testGetActionForCommandIdentifier() {
        System.out.println("getActionForCommandIdentifier");
        String commandIdentifier = "";
        AbstractJssController instance = new AbstractJssControllerImpl();
        IJssAction expResult = null;
        IJssAction result = instance.getActionForCommandIdentifier(commandIdentifier);
        assertEquals(expResult, result);
    }

    public class AbstractJssTextAreaImpl extends AbstractJssTextArea {

        @Override
        public String getShellText() {
            return "";
        }

        @Override
        public void setShellText(String newShellText) {
        }

        @Override
        public boolean isShellTextAreaLocked() {
            return true;
        }

        @Override
        public void lockShellTextArea() {
        }

        @Override
        public void unlockShellTextArea() {
        }

        @Override
        public String getCommandLine() {
            return "";
        }

        @Override
        public void setCommandLine(String newCommandLine) {
        }

        @Override
        public boolean isCommandLineLocked() {
            return true;
        }

        @Override
        public void lockCommandLine() {
        }

        @Override
        public void unlockCommandLine() {
        }
    }

    public class AbstractJssModelImpl extends AbstractJssModel {
    }

    public class AbstractJssControllerImpl extends AbstractJssController {

        private IJssView view;

        private AbstractJssModel model;

        public AbstractJssControllerImpl() {
            this.view = new AbstractJssTextAreaImpl();
            this.model = new AbstractJssModelImpl();
        }

        public IJssView getView() {
            return view;
        }

        public void setView(IJssView anotherView) {
            this.view = anotherView;
        }

        public AbstractJssModel getModel() {
            return model;
        }

        public void setModel(AbstractJssModel anotherModel) {
            this.model = anotherModel;
        }

        @Override
        public void addNewLineToShell() {
        }

        @Override
        public void addNewLineToShell(String text) {
        }

        @Override
        public void addNewCommandLine() {
        }

        @Override
        public void addNewCommandLine(String newCommandLine) {
        }

        @Override
        public String getCommandLine() {
            return "";
        }

        @Override
        public void setCommandLine(String newCommandLine) {
        }
    }
    
}
