package jswingshell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import jswingshell.action.AbstractJssAction;
import jswingshell.action.AbstractThreadedJssAction;
import jswingshell.action.IJssAction;

/**
 * The base for Shell controllers.
 *
 * <p>
 * This abstract shell controller includes a basic command history and a command
 * line parser to extract arguments to pass to {@link IJssAction}.</p>
 *
 * @author Mathieu Brunot
 */
public abstract class AbstractJssController extends java.awt.event.KeyAdapter implements IJssController {

    /**
     * The arguments default separator.
     */
    public static final String COMMAND_PARAMETER_SEPARATOR = " ";

    /**
     * Prefix used to indicate the start of command line.
     */
    private static final String COMMAND_LINE_PREFIX = "> ";

    /**
     * Default return status code for a successful execution of an action.
     * @since 1.4
     */
    public static final int COMMAND_SUCCESS = IJssAction.SUCCESS;

    /**
     * Default return status code for a failed execution of an action.
     * @since 1.4
     */
    public static final int COMMAND_ERROR = IJssAction.ERROR;

    /**
     * Default return status code for an execution still in progress.
     *
     * <p>
     * This is usually used by action that operates in separate threads. Such
     * actions are responsible for alerting the shell of their end by themselves
     * and thus should return a specific status code to alert the shell
     * environment.</p>
     *
     * @see AbstractThreadedJssAction
     * @since 1.4
     */
    public static final int COMMAND_IN_PROGRESS = IJssAction.IN_PROGRESS;

    /**
     * Default return status code when a given action cannot be found.
     */
    public static final int COMMAND_NOT_FOUND_STATUS = 0x0004;

    /**
     * Default return status code for an empty execution (no action provided).
     */
    public static final int COMMAND_EMPTY_STATUS = 0x0008;

    public static final PublicationLevel DEFAULT_LEVEL = PublicationLevel.WARNING;

    private CommandHistory commandHistory;

    private CommandLineParser commandLineParser;

    private PublicationLevel level;

    // #########################################################################
    // Constructors
    /**
     * Default constructor.
     */
    public AbstractJssController() {
        this(CommandHistory.DEFAULT_MAXIMUM_SIZE_ALLOWED, CommandHistory.DEFAULT_DUPLICATION_ALLOWED, CommandHistory.DEFAULT_SIZE_UNLIMITED, DEFAULT_LEVEL);
    }

    public AbstractJssController(PublicationLevel level) {
        this(CommandHistory.DEFAULT_MAXIMUM_SIZE_ALLOWED, CommandHistory.DEFAULT_DUPLICATION_ALLOWED, CommandHistory.DEFAULT_SIZE_UNLIMITED, level);
    }

    public AbstractJssController(String levelName) {
        this(CommandHistory.DEFAULT_MAXIMUM_SIZE_ALLOWED, CommandHistory.DEFAULT_DUPLICATION_ALLOWED, CommandHistory.DEFAULT_SIZE_UNLIMITED, PublicationLevel.valueOf(levelName));
    }

    /**
     * Create a shell controller with a command history initial capacity.
     *
     * @param commandHistoryCapacity Initial command history size.
     */
    public AbstractJssController(int commandHistoryCapacity) {
        this(commandHistoryCapacity, CommandHistory.DEFAULT_DUPLICATION_ALLOWED, CommandHistory.DEFAULT_SIZE_UNLIMITED, DEFAULT_LEVEL);
    }

    public AbstractJssController(int commandHistoryCapacity, boolean duplicationAllowed, boolean sizeUnlimited) {
        this(commandHistoryCapacity, duplicationAllowed, duplicationAllowed, DEFAULT_LEVEL);
    }

    public AbstractJssController(int commandHistoryCapacity, boolean duplicationAllowed, boolean sizeUnlimited, PublicationLevel level) {
        this.commandHistory = new CommandHistory(commandHistoryCapacity, duplicationAllowed, sizeUnlimited);
        this.commandLineParser = new CommandLineParser();
        this.level = level;
    }

    // #########################################################################
    // MVC methods
    /**
     * {@inheritDoc }
     */
    @Override
    public abstract IJssView getView();

    /**
     * {@inheritDoc }
     */
    @Override
    public abstract AbstractJssModel getModel();

    // #########################################################################
    // Command history methods
    /**
     * Get a copy of the whole command history.
     *
     * @return the command history.
     */
    public CommandHistory getCommandHistory() {
        return new CommandHistory(commandHistory);
    }

    /**
     * Reset command history and current position.
     */
    public void resetCommandHistory() {
        commandHistory.reset();
    }

    /**
     * Add a command to the history.
     *
     * <p>
     * If the command was added, the current position in the history is resetted
     * to its default.</p>
     *
     * @param command the command to add to the history
     * @return {@code true} if this action changed the command history.
     *
     * @see #getCurrentHistoryPosition()
     */
    protected boolean addToCommandHistory(String command) {
        return commandHistory.add(command);
    }

    /**
     * Clear command history and current position.
     */
    public void clearCommandHistory() {
        commandHistory.clear();
    }

    /**
     * Get the size of the command history.
     *
     * @return the size of the command history.
     */
    public int commandHistorySize() {
        return commandHistory.size();
    }

    /**
     * Is the command history empty?
     *
     * @return {@code true} if the command history is empty.
     */
    public boolean isCommandHistoryEmpty() {
        return commandHistory.isEmpty();
    }

    /**
     * Get the current history position.
     *
     * <p>
     * If the position in the history is at its default value (i.e. first call
     * or after insert of new commands), then the cursor will be set right after
     * the end of the history.</p>
     *
     * @return the current history position, {@code -1} if the command history
     * is not available
     *
     * @see #getCurrentHistoryCommand()
     */
    protected int getCurrentHistoryPosition() {
        return commandHistory.getCurrentHistoryPosition();
    }

    /**
     * Get the the command at the current position from history.
     *
     * @return the command pointed by the command history position. If the
     * current history position is outside of the history, then returns
     * {@code null}
     *
     * @see #getCurrentHistoryPosition()
     */
    protected String getCurrentHistoryCommand() {
        return commandHistory.getCurrent();
    }

    /**
     * Get previous command from history.
     *
     * <p>
     * If we are not already at the start of the command history, the history
     * position is moved to the previous element. Then the current element is
     * returned.</p>
     *
     * @return the previous command from history.
     *
     * @see #getCurrentHistoryCommand()
     */
    protected String getPreviousCommand() {
        return commandHistory.previous();
    }

    /**
     * Get next command from history.
     *
     * <p>
     * If we are not already at the end of the command history, the history
     * position is moved to the next element. Then the current element is
     * returned.</p>
     *
     * @return the next command from history.
     *
     * @see #getCurrentHistoryCommand()
     */
    protected String getNextCommand() {
        return commandHistory.next();
    }

    // #########################################################################
    // Shell methods
    /**
     * {@inheritDoc }
     */
    @Override
    public String getShellText() {
        return getView().getShellText();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setShellText(String newShellText) {
        getView().setShellText(newShellText);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void clearShell() {
        setShellText(null);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isShellTextAreaLocked() {
        return getView().isShellTextAreaLocked();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void lockShellTextArea() {
        getView().lockShellTextArea();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void unlockShellTextArea() {
        getView().unlockShellTextArea();
    }

    // #########################################################################
    // Command line methods
    /**
     * Get the string used to prefix a command line.
     *
     * @return the string used to prefix a command line.
     */
    protected String getCommandLinePrefix() {
        return COMMAND_LINE_PREFIX;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void clearCommandLine() {
        setCommandLine(null);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isCommandLineLocked() {
        return getView().isCommandLineLocked();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void lockCommandLine() {
        getView().lockCommandLine();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void unlockCommandLine() {
        getView().unlockCommandLine();
    }

    // #########################################################################
    // Command management
    /**
     * Get the whole command line parser.
     *
     * @return the command line parser.
     */
    public CommandLineParser getCommandLineParser() {
        return commandLineParser;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public final String getCommandParameterSeparator() {
        return COMMAND_PARAMETER_SEPARATOR;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String extractCommand() {
        return getCommandLine();
    }

    /**
     * Parse command line and extract arguments for an {@link IJssAction}.
     *
     * @param commandLine the command line
     * @return an array of arguments as {@code String}
     */
    protected String[] extractCommandParameters(String commandLine) {
        return getCommandLineParser().extractCommandArguments(commandLine);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int interpretCommand() {
        return interpretCommand(extractCommand());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int interpretCommand(String command) {
        return interpretCommand(command, true);
    }

    /**
     * Interpret a command.
     *
     * @param command the command to interpret. Action and arguments will be
     * extracter from it.
     * @param addCommandToHistory Should we add the command to the history?
     * @return the action's return code.
     */
    public int interpretCommand(String command, boolean addCommandToHistory) {
        if (command == null || command.isEmpty()) {
            publish(PublicationLevel.ERROR, "Empty command");
            return COMMAND_EMPTY_STATUS;
        }
        int commandReturnStatus;

        String[] args = extractCommandParameters(command);

        if (args == null || args.length == 0) {
            publish(PublicationLevel.ERROR, "Empty command");
            commandReturnStatus = COMMAND_EMPTY_STATUS;
        } else {
            // Store the command in history and reset position
            if (addCommandToHistory) {
                addToCommandHistory(command);
            }

            // Search for the action
            IJssAction action = getActionForCommandIdentifier(args[0]);
            if (action != null) {
                commandReturnStatus = action.run(this, args);
            } else {
                publish(PublicationLevel.ERROR, "Command not found: " + args[0]);
                commandReturnStatus = COMMAND_NOT_FOUND_STATUS;
            }
        }

        return commandReturnStatus;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int interpret() {
        lockCommandLine();
        
        String cmd = extractCommand();
        
        int commandReturnStatus = AbstractJssAction.SUCCESS;
        if (cmd != null && !cmd.isEmpty()) {
            commandReturnStatus = interpretCommand(cmd);
            addNewLineToShell();
            publish(PublicationLevel.DEBUG, "Return status: " + commandReturnStatus);
        }
        
        switch (commandReturnStatus) {
            case AbstractThreadedJssAction.IN_PROGRESS:
                // Do not add command line for actions still in progress
                break;
            default:
                addNewCommandLine();
                unlockCommandLine();
                break;
        }
        
        return commandReturnStatus;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public synchronized boolean publish(PublicationLevel level, String message) {
        boolean added = false;
        if (level != null && level.compareTo(getPublicationLevel()) <= 0) {
            addNewLineToShell(message);
            added = true;
        }
        return added;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public PublicationLevel getPublicationLevel() {
        return this.level;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setPublicationLevel(PublicationLevel level) {
        this.level = level;
    }

    // #########################################################################
    // Model related methods
    /**
     * Get actions available for this shell.
     *
     * <p>
     * The method returns an unmodifiable set initiliazed with the actions
     * available in the model.</p>
     *
     * @return a <em>read-only</em> set of the actions available for this shell.
     *
     * @see Collections#unmodifiableSet(java.util.Set)
     */
    @Override
    public Set<IJssAction> getAvailableActions() {
        return Collections.unmodifiableSet(getModel().getAvailableActions());
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public IJssAction getActionForCommandIdentifier(String commandIdentifier) {
        return getModel().getActionForCommandIdentifier(commandIdentifier);
    }

    // #########################################################################
    // Command history
    /**
     * Command history.
     *
     * <p>
     * This command history allows to store commands executed by a shell.
     * Commands are stored as {@code String} in a collection. The collection
     * might allow duplicates depending on {@link #isDuplicationAllowed() }.</p>
     *
     * <p>
     * When adding commands, the command history is automatically positionned
     * <strong>after</strong> the last element. You can retrieve the current
     * command from the history with {@link #getCurrent() } and navigate through
     * the history with {@link #previous() } and {@link #previous() }. If the
     * current position is outside of the history, {@code null} is returned.</p>
     *
     * <p>
     * If {@link #isSizeUnlimited() } is {@code false}, the command history will
     * be forced to {@link #getMaximumSizeAllowed() }: when adding commands to
     * an already full history, the older commands will be removed before adding
     * the new ones. If {@code getMaximumSizeAllowed() } is equal to {@code 0},
     * the history is disabled.</p>
     *
     */
    public static class CommandHistory {

        /**
         * Default value for the duplication mode.
         */
        protected static final boolean DEFAULT_DUPLICATION_ALLOWED = true;
        /**
         * Default value for the unlimited command history size mode.
         *
         * @see #DEFAULT_MAXIMUM_SIZE_ALLOWED
         */
        protected static final boolean DEFAULT_SIZE_UNLIMITED = true;
        /**
         * Default value for the maximum history size.
         *
         * @see #DEFAULT_SIZE_UNLIMITED
         */
        protected static final int DEFAULT_MAXIMUM_SIZE_ALLOWED = 50;

        private int currentHistoryPosition = -1;

        private Collection<String> commandBuffer;

        private boolean duplicationAllowed;

        private boolean sizeUnlimited;

        private int maximumSizeAllowed;

        /**
         * Default constructor for a command history.
         */
        public CommandHistory() {
            this(DEFAULT_MAXIMUM_SIZE_ALLOWED, DEFAULT_DUPLICATION_ALLOWED, DEFAULT_SIZE_UNLIMITED);
        }

        /**
         * Create a command history with an initial capacity.
         *
         * @param commandHistoryCapacity Command history capacity.
         */
        public CommandHistory(int commandHistoryCapacity) {
            this(commandHistoryCapacity, DEFAULT_DUPLICATION_ALLOWED, DEFAULT_SIZE_UNLIMITED);
        }

        /**
         * Create a command history with an initial capacity, allowed
         * duplication status and size limitation status.
         *
         * @param commandHistoryCapacity Command history capacity.
         * @param duplicationAllowed Is duplication of commands allowed in
         * history?
         * @param sizeUnlimited Is command history's size <em>unlimited</em>?
         * {@code commandHistoryCapacity} as {@code 0} plus {@code sizeUnlimited
         * } as {@code false} disables the command history.
         *
         * @throws IllegalArgumentException if {@code maximumSizeAllowed} is
         * negative.
         */
        public CommandHistory(int commandHistoryCapacity, boolean duplicationAllowed, boolean sizeUnlimited) {
            if (commandHistoryCapacity < 0) {
                throw new IllegalArgumentException("The maximum size allowed for the command history cannot be negative.");
            }
            this.duplicationAllowed = duplicationAllowed;
            if (duplicationAllowed) {
                this.commandBuffer = new LinkedList<>();
            } else {
                this.commandBuffer = new ConcurrentSkipListSet<>();
            }
            this.sizeUnlimited = sizeUnlimited;
            this.maximumSizeAllowed = commandHistoryCapacity;
        }

        /**
         * Create a shell controller with a command history initial capacity.
         *
         * @param that Another command history.
         *
         * @throws IllegalArgumentException if {@code maximumSizeAllowed} is
         * negative.
         * 
         * @since 1.2
         */
        public CommandHistory(CommandHistory that) {
            this.duplicationAllowed = that.duplicationAllowed;
            if (duplicationAllowed) {
                this.commandBuffer = new LinkedList<>(that.commandBuffer);
            } else {
                this.commandBuffer = new ConcurrentSkipListSet<>(that.commandBuffer);
            }
            this.sizeUnlimited = that.sizeUnlimited;
            this.maximumSizeAllowed = that.maximumSizeAllowed;
        }

        /**
         * Reset all command history properties.
         */
        public void reset() {
            this.duplicationAllowed = DEFAULT_DUPLICATION_ALLOWED;
            this.sizeUnlimited = DEFAULT_SIZE_UNLIMITED;
            this.maximumSizeAllowed = DEFAULT_MAXIMUM_SIZE_ALLOWED;

            resetCommandBuffer();
        }

        /**
         * Get the whole command buffer.
         *
         * @return the command buffer (a collection of commands).
         */
        protected Collection<String> getCommandBuffer() {
            return commandBuffer;
        }

        /**
         * Get the the command buffer size.
         *
         * @return the command buffer size.
         */
        public int size() {
            return commandBuffer != null ? commandBuffer.size() : 0;
        }

        /**
         * Is the command buffer empty?
         *
         * @return {@code true} if the command buffer is empty.
         */
        public boolean isEmpty() {
            return commandBuffer != null ? commandBuffer.isEmpty() : true;
        }

        /**
         * Reset command buffer with current command history properties.
         *
         * <p>
         * This method is used internally to copy the command buffer to a new
         * collection more appropriate the new properties.</p>
         */
        protected void resetCommandBuffer() {
            Collection<String> oldCommandBuffer = this.commandBuffer;

            if (getMaximumSizeAllowed() != 0) {
                if (isDuplicationAllowed()) {
                    this.commandBuffer = new LinkedList<>();
                } else {
                    this.commandBuffer = new ConcurrentSkipListSet<>();
                }
            } else {
                this.commandBuffer = null;
            }

            if (oldCommandBuffer != null) {
                if (getMaximumSizeAllowed() > 0 && !isSizeUnlimited() && this.commandBuffer.size() >= getMaximumSizeAllowed()) {
                    // Remove "oldest" element to make some place
                    if (oldCommandBuffer instanceof List) {
                        List localCommandBuffer = ((List) oldCommandBuffer);
                        while (localCommandBuffer.size() >= getMaximumSizeAllowed()) {
                            localCommandBuffer.remove(0);
                        }
                    } else if (oldCommandBuffer instanceof NavigableSet) {
                        NavigableSet localCommandBuffer = ((NavigableSet) oldCommandBuffer);
                        while (localCommandBuffer.size() >= getMaximumSizeAllowed()) {
                            localCommandBuffer.pollFirst();
                        }
                    }
                    this.commandBuffer.addAll(oldCommandBuffer);
                }

                oldCommandBuffer.clear();
            }
        }

        /**
         * Is duplication of commands allowed in history?
         *
         * <p>
         * Note that if duplication is not allowed and the same command is
         * entered twice, only the first occurence will be kept.</p>
         *
         * @return {@code true} if duplication is allowed.
         *
         * @see #setDuplicationAllowed(boolean)
         */
        public boolean isDuplicationAllowed() {
            return duplicationAllowed;
        }

        /**
         * Set the <em>command duplication</em> mode.
         *
         * @param duplicationAllowed {@code true} if duplication should be
         * allowed.
         */
        public void setDuplicationAllowed(boolean duplicationAllowed) {
            if (this.duplicationAllowed != duplicationAllowed) {
                this.duplicationAllowed = duplicationAllowed;

                resetCommandBuffer();
            }
        }

        /**
         * Is command history's size <em>unlimited</em>?
         *
         * <p>
         * Note that even if command history's size is <em>virtually
         * unlimited</em>, you will always be limited by the available memory.
         * Also keep in mind that having an history size's greater or equal to
         * {@code Integer.MAX_VALUE} is highly discouraged.</p>
         *
         * @return {@code true} if command history's size is <em>unlimited</em>.
         *
         * @see #getMaximumSizeAllowed()
         * @see #setSizeUnlimited(boolean)
         */
        public boolean isSizeUnlimited() {
            return sizeUnlimited;
        }

        /**
         * Set the <em>unlimited history size</em> status.
         *
         * @param sizeUnlimited {@code true} if command history's size should be
         * <em>unlimited</em>.
         */
        public void setSizeUnlimited(boolean sizeUnlimited) {
            if (this.sizeUnlimited != sizeUnlimited) {
                this.sizeUnlimited = sizeUnlimited;

                resetCommandBuffer();
            }
        }

        /**
         * Get the maximum size allowed for the command history.
         *
         * <p>
         * Note that this parameter has not effect if 
         * {@link #isSizeUnlimited() } is {@code true}.</p>
         *
         * @return the maximum size allowed for the command history.
         *
         * @see #setMaximumSizeAllowed(int)
         */
        public int getMaximumSizeAllowed() {
            return maximumSizeAllowed;
        }

        /**
         * Sets the maximum size allowed for the command history.
         *
         * @param maximumSizeAllowed the maximum size allowed for the command
         * history. {@code 0} plus {@link #isSizeUnlimited() } as {@code true}
         * disables the command history.
         *
         * @throws IllegalArgumentException if {@code maximumSizeAllowed} is
         * negative.
         */
        public void setMaximumSizeAllowed(int maximumSizeAllowed) {
            if (maximumSizeAllowed < 0) {
                throw new IllegalArgumentException("The maximum size allowed for the command history cannot be negative.");
            }
            if (this.maximumSizeAllowed != maximumSizeAllowed) {
                this.maximumSizeAllowed = maximumSizeAllowed;

                resetCommandBuffer();
            }
        }

        /**
         * Add a command to the history.
         *
         * <p>
         * If the command was added, the current position in the history is
         * resetted to its default.</p>
         *
         * @param command the command to add to the history
         * @return {@code true} if this action changed the command history.
         *
         * @see #getCurrentHistoryPosition()
         */
        public boolean add(String command) {
            boolean added = false;
            if (getMaximumSizeAllowed() == 0 || getCommandBuffer() == null) {
                return added;
            }

            // Should we remove commands?
            if (!isSizeUnlimited() && getCommandBuffer().size() >= getMaximumSizeAllowed()) {
                // Remove "oldest" element to make some place
                if (getCommandBuffer() instanceof List) {
                    List localCommandBuffer = ((List) getCommandBuffer());
                    while (localCommandBuffer.size() >= getMaximumSizeAllowed()) {
                        localCommandBuffer.remove(0);
                    }
                } else if (getCommandBuffer() instanceof NavigableSet) {
                    NavigableSet localCommandBuffer = ((NavigableSet) getCommandBuffer());
                    while (localCommandBuffer.size() >= getMaximumSizeAllowed()) {
                        localCommandBuffer.pollFirst();
                    }
                }
            }

            added = getCommandBuffer().add(command);
            if (added) {
                currentHistoryPosition = -1;
            }

            return added;
        }

        /**
         * Clear command history anc current position.
         */
        public void clear() {
            if (getCommandBuffer() != null) {
                getCommandBuffer().clear();
            }
            currentHistoryPosition = -1;
        }

        /**
         * Get the current history position.
         *
         * <p>
         * If the position in the history is at its default value (i.e. first
         * call or after insert of new commands), then the cursor will be set
         * right after the end of the history.</p>
         *
         * @return the current history position, {@code -1} if the command
         * history is not available
         *
         * @see #getCurrent()
         */
        public int getCurrentHistoryPosition() {
            if (currentHistoryPosition == -1 && getCommandBuffer() != null) {
                // Initialize to the last command
                currentHistoryPosition = getCommandBuffer().size();
            }
            return currentHistoryPosition;
        }

        /**
         * Get the the command at the current position from history.
         *
         * @return the command pointed by the command history position. If the
         * current history position is outside of the history, then returns
         * {@code null}
         *
         * @see #getCurrentHistoryPosition()
         */
        public String getCurrent() {
            if (getCommandBuffer() != null && getCurrentHistoryPosition() >= 0 && getCurrentHistoryPosition() < getCommandBuffer().size()) {
                if (getCommandBuffer() instanceof List) {
                    return ((List<String>) getCommandBuffer()).get(getCurrentHistoryPosition());
                } else if (getCommandBuffer() instanceof NavigableSet) {
                    return (String) ((NavigableSet<String>) getCommandBuffer()).toArray()[getCurrentHistoryPosition()];
                }
            }
            return null;
        }

        /**
         * Get previous command from history.
         *
         * <p>
         * If we are not already at the start of the command history, the
         * history position is moved to the previous element. Then the current
         * element is returned.</p>
         *
         * @return the previous command from history.
         *
         * @see #getCurrent()
         */
        public String previous() {
            int previousHistoryPosition = getCurrentHistoryPosition() - 1;
            if (previousHistoryPosition >= 0) {
                currentHistoryPosition = previousHistoryPosition;
            }
            return getCurrent();
        }

        /**
         * Get next command from history.
         *
         * <p>
         * If we are not already at the end of the command history, the history
         * position is moved to the next element. Then the current element is
         * returned.</p>
         *
         * @return the next command from history.
         *
         * @see #getCurrent()
         */
        public String next() {
            int nextHistoryPosition = getCurrentHistoryPosition() + 1;
            if (getCommandBuffer() != null && getCurrentHistoryPosition() < getCommandBuffer().size()) {
                currentHistoryPosition = nextHistoryPosition;
            }
            return getCurrent();
        }

    }

    // #########################################################################
    // Command line parser
    /**
     * Command line parser.
     *
     * <p>
     * This command line parser behaves in a way very similar to C/C++:</p>
     * <ul>
     * <li>
     * <p>
     * The first argument is the the command id.</p>
     * </li>
     * <li>
     * <p>
     * Arguments are delimited by white space, which is either a space or a
     * tab.</p>
     * </li>
     * <li>
     * <p>
     * A string surrounded by double quotation marks is interpreted as a single
     * argument, regardless of white space contained within. A quoted string can
     * be embedded in an argument. Note that the caret (<strong>^</strong>) is
     * not recognized as an escape character or delimiter. </p>
     * </li>
     * <li>
     * <p>
     * A double quotation mark preceded by a backslash, <strong>\"</strong>, is
     * interpreted as a literal double quotation mark (<strong>"</strong>).</p>
     * </li>
     * <li>
     * <p>
     * Backslashes are interpreted literally, unless they immediately precede a
     * double quotation mark.</p>
     * </li>
     * <li>
     * <p>
     * If an even number of backslashes is followed by a double quotation mark,
     * then one backslash (<strong>\</strong>) is placed in the
     * <span class="parameter">argv</span> array for every pair of backslashes
     * (<strong>\\</strong>), and the double quotation mark (<strong>"</strong>)
     * is interpreted as a string delimiter.</p>
     * </li>
     * <li>
     * <p>
     * If an odd number of backslashes is followed by a double quotation mark,
     * then one backslash (<strong>\</strong>) is placed in the
     * <span class="parameter">argv</span> array for every pair of backslashes
     * (<strong>\\</strong>) and the double quotation mark is interpreted as an
     * escape sequence by the remaining backslash, causing a literal double
     * quotation mark (<strong>"</strong>) to be placed in
     * <span class="parameter">argv</span>.</p>
     * </li>
     *
     * </ul>
     *
     * @see "https://msdn.microsoft.com/en-us/library/a1y7w461.aspx"
     * @see
     * "http://www.daviddeley.com/autohotkey/parameters/parameters.htm#WINARGV"
     */
    public static class CommandLineParser {

        private StringBuilder parameterStringBuilder = null;

        private List<String> parameters = null;

        private int argc = 0;

        private int countBackslashes = 0;

        private boolean insideDoubleQuotedPart = false;

        private void initParserProperties(String commandLine) {
            countBackslashes = 0;
            insideDoubleQuotedPart = false;
            argc = 0;
            parameterStringBuilder = new StringBuilder();
            parameters = new ArrayList<>(commandLine.length() / 2);
        }

        private void resetParserProperties() {
            countBackslashes = 0;
            insideDoubleQuotedPart = false;
            argc = 0;
            parameterStringBuilder = null;
            parameters = null;
        }

        /**
         * Append all successive backslashes found to the current argument being
         * parsed.
         */
        private void appendBackslashesToCurrentParameter() {
            for (int j = 0, m = countBackslashes; j < m; j++) {
                parameterStringBuilder.append('\\');
            }
            countBackslashes = 0;
        }

        /**
         * Append a character to the current argument being parsed.
         *
         * @param c a character
         */
        private void appendToCurrentParameter(char c) {
            appendBackslashesToCurrentParameter();
            parameterStringBuilder.append(c);
        }

        /**
         * Append a string to the current argument being parsed.
         *
         * @param s a string
         */
        private void appendToCurrentParameter(String s) {
            appendBackslashesToCurrentParameter();
            parameterStringBuilder.append(s);
        }

        /**
         * Fill current argument with chars in buffer and move to next one.
         */
        private void moveToNextParameter() {
            if (parameterStringBuilder.length() > 0) {
                // Move to the next parameter
                parameters.add(argc++, parameterStringBuilder.toString());
                parameterStringBuilder = new StringBuilder();
            }
        }

        /**
         * Convert the arguments list to a String array.
         *
         * @return the arguments as a String array.
         */
        private String[] parametersToArray() {
            String[] argv = new String[parameters.size()];

            for (int i = 0, n = argc; i < n; i++) {
                argv[i] = parameters.get(i);
            }

            return argv;
        }

        /**
         * Parse command line and extract arguments.
         *
         * <p>
         * This command line parser behaves in a way very similar to C/C++:</p>
         * <ul>
         * <li>
         * <p>
         * The first argument is the the command id.</p>
         * </li>
         * <li>
         * <p>
         * Arguments are delimited by white space, which is either a space or a
         * tab.</p>
         * </li>
         * <li>
         * <p>
         * A string surrounded by double quotation marks is interpreted as a
         * single argument, regardless of white space contained within. A quoted
         * string can be embedded in an argument. Note that the caret
         * (<strong>^</strong>) is not recognized as an escape character or
         * delimiter. </p>
         * </li>
         * <li>
         * <p>
         * A double quotation mark preceded by a backslash, <strong>\"</strong>,
         * is interpreted as a literal double quotation mark
         * (<strong>"</strong>).</p>
         * </li>
         * <li>
         * <p>
         * Backslashes are interpreted literally, unless they immediately
         * precede a double quotation mark.</p>
         * </li>
         * <li>
         * <p>
         * If an even number of backslashes is followed by a double quotation
         * mark, then one backslash (<strong>\</strong>) is placed in the
         * <span class="parameter">argv</span> array for every pair of
         * backslashes (<strong>\\</strong>), and the double quotation mark
         * (<strong>"</strong>) is interpreted as a string delimiter.</p>
         * </li>
         * <li>
         * <p>
         * If an odd number of backslashes is followed by a double quotation
         * mark, then one backslash (<strong>\</strong>) is placed in the
         * <span class="parameter">argv</span> array for every pair of
         * backslashes (<strong>\\</strong>) and the double quotation mark is
         * interpreted as an escape sequence by the remaining backslash, causing
         * a literal double quotation mark (<strong>"</strong>) to be placed in
         * <span class="parameter">argv</span>.</p>
         * </li>
         *
         * </ul>
         *
         * @see "https://msdn.microsoft.com/en-us/library/a1y7w461.aspx"
         * @see
         * "http://www.daviddeley.com/autohotkey/parameters/parameters.htm#WINARGV"
         *
         * @param commandLine The command line to parse
         *
         * @return the array of arguments
         */
        public String[] extractCommandArguments(String commandLine) {
            String[] argv;

            if (commandLine == null || commandLine.isEmpty()) {
                argv = null;
            } else {
                initParserProperties(commandLine);

                // Parse off parameter 0 (the action id)
                String commandSeparator = AbstractJssController.COMMAND_PARAMETER_SEPARATOR;
                String commandTrimmed = commandLine.trim();

                int indexOfCommandEnd = commandTrimmed.indexOf(commandSeparator);
                if (indexOfCommandEnd == -1) {
                    resetParserProperties();
                    if (commandTrimmed.startsWith("\"") && commandTrimmed.endsWith("\"")) {
                        commandTrimmed = commandTrimmed.substring(1, commandTrimmed.length() - 1);
                    }
                    return new String[]{commandTrimmed};
                }

                String commandId;
                if (commandTrimmed.startsWith("\"") && commandTrimmed.indexOf("\"", 1) > -1) {
                    indexOfCommandEnd = commandTrimmed.indexOf("\"", 1);
                    commandId = commandTrimmed.substring(1, commandTrimmed.indexOf("\"", 1));
                } else {
                    commandId = commandTrimmed.substring(0, indexOfCommandEnd);
                }
                appendToCurrentParameter(commandId);
                moveToNextParameter();

                // Parse off next parameters
                String restOfTheCommandLine = commandTrimmed.substring(indexOfCommandEnd + commandSeparator.length());

                for (int i = 0, n = restOfTheCommandLine.length(); i < n; i++) {
                    char c = restOfTheCommandLine.charAt(i);
                    switch (c) {

                        // Count backslashes
                        case '\\':
                            countBackslashes++;
                            break;

                        // Detect double quoted parts
                        case '"':
                            // Even number of backslashes?
                            if ((0x01 & countBackslashes) == 0) {
                                countBackslashes /= 2;
                                appendBackslashesToCurrentParameter();
                                // If currently in a double quoted part
                                if (insideDoubleQuotedPart) {
                                    // If next character is also "
                                    if (i + 1 < n && c == restOfTheCommandLine.charAt(i + 1)) {
                                        i++;
                                        appendToCurrentParameter(c);
                                    } else {
                                        insideDoubleQuotedPart = false;
                                    }
                                } else {
                                    insideDoubleQuotedPart = true;
                                }
                            } else {
                                countBackslashes--;
                                countBackslashes /= 2;
                                appendToCurrentParameter(c);
                            }
                            break;

                        // Any whitespace character
                        case '\t':
                        case ' ':
                            appendBackslashesToCurrentParameter();
                            if (insideDoubleQuotedPart) {
                                appendToCurrentParameter(c);
                            } else {
                                moveToNextParameter();
                            }
                            break;

                        // Any other character shall be added to the parameter
                        default:
                            appendToCurrentParameter(c);
                            break;
                    }
                }
                appendBackslashesToCurrentParameter();
                moveToNextParameter();

                argv = parametersToArray();
            }

            resetParserProperties();
            return argv;
        }
    }
}
