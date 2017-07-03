package jswingshell.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Icon;
import javax.swing.SwingWorker;
import jswingshell.IJssController;

/**
 * A {@code AbstractThreadedJssAction} is an operation that can be called
 * through anid and some parameters if needed and executes in a separate thread.
 *
 * @see SwingWorker
 *
 * @author Mathieu Brunot
 *
 * @since 1.2
 */
public abstract class AbstractThreadedJssAction extends AbstractJssAction {

    /**
     * List of all currently active threads of this action.
     *
     * @since 1.4
     */
    private final transient List<AbstractJssActionWorker> activeWorkers = new ArrayList<>();

    // #########################################################################
    public AbstractThreadedJssAction(String name, Icon icon, IJssController shellController, String... args) {
        super(name, icon, shellController, args);
    }

    public AbstractThreadedJssAction(String name, IJssController shellController, String... args) {
        super(name, shellController, args);
    }

    public AbstractThreadedJssAction(IJssController shellController, String... args) {
        super(shellController, args);
    }

    public AbstractThreadedJssAction(IJssController shellController) {
        super(shellController);
    }

    public AbstractThreadedJssAction() {
        super();
    }

    // #########################################################################
    /**
     * Run the threaded action for a given shell controller and given arguments.
     *
     * <p>
     * The method will prepare a {@link AbstractJssActionWorker} for the given
     * shell and arguments and if it was properly created, execute the working
     * and return {@link IJssAction#IN_PROGRESS}.</p>
     *
     * @param shellController the shell controller for which to execute the
     * action
     *
     * @param args the arguments given to the action.
     *
     * @return the return status code of the action.
     */
    @Override
    public int run(IJssController shellController, String... args) {
        int commandReturnStatus;

        AbstractJssActionWorker worker = this.prepareWorker(shellController, args);
        if (worker != null) {
            // Mark the action in progress
            commandReturnStatus = IJssAction.IN_PROGRESS;
            // And keep track of the worker to be executed
            this.activeWorkers.add(worker);
            if (shellController != null) {
                shellController.lockCommandLine();
            }
            worker.execute();
        } else {
            commandReturnStatus = IJssAction.ERROR;
        }

        return commandReturnStatus;
    }

    protected AbstractJssActionWorker prepareWorker() {
        return prepareWorker(this.getDefaultShellController(), this.getDefaultArguments());
    }

    protected AbstractJssActionWorker prepareWorker(String... args) {
        return prepareWorker(this.getDefaultShellController(), args);
    }

    protected abstract AbstractJssActionWorker prepareWorker(IJssController shellController, String... args);

    /**
     * Get the list of active workers.
     *
     * @return an unmodiable list of all currently active workers for this
     * action
     *
     * @since 1.4
     */
    public List<AbstractJssActionWorker> getActiveWorkers() {
        return Collections.unmodifiableList(this.activeWorkers);
    }

    /**
     * Attempts to cancel execution of this action's tasks. This attempt will
     * fail if the task has already completed, has already been cancelled, or
     * could not be cancelled for some other reason. If successful, and this
     * task has not started when <tt>cancel</tt> is called, this task should
     * never run. If the task has already started, then the
     * <tt>mayInterruptIfRunning</tt> parameter determines whether the thread
     * executing this task should be interrupted in an attempt to stop the task.
     *
     * @param shellController the shell controller for which to cancel the tasks
     * @param mayInterruptIfRunning <tt>true</tt> if the thread executing this
     * task should be interrupted; otherwise, in-progress tasks are allowed to
     * complete
     *
     * @return <tt>false</tt> if the task could not be cancelled, typically
     * because it has already completed normally;
     * <tt>true</tt> otherwise
     */
    public final boolean cancel(IJssController shellController, boolean mayInterruptIfRunning) {
        boolean cancelled = true;
        List<AbstractJssActionWorker> workers = new ArrayList<>(activeWorkers);
        for (AbstractThreadedJssAction.AbstractJssActionWorker worker : workers) {
            if (worker.getShellController() == shellController) {
                cancelled &= worker.cancel(mayInterruptIfRunning);
                activeWorkers.remove(worker);
            }
        }
        return cancelled;
    }

    // #########################################################################
    /**
     * The type used for carrying out intermediate results by a
     * {@code AbstractJssActionWorker's} {@code publish} and {@code process}
     * methods.
     *
     * @since 1.2
     */
    public class JssActionWorkerChunk {

        final IJssController.PublicationLevel chunkLevel;
        final String message;

        /**
         * Construct a chunk of results with level and a message.
         *
         * @param chunkLevel the chunk's publication level
         * @param message the chunk's message
         */
        public JssActionWorkerChunk(IJssController.PublicationLevel chunkLevel, String message) {
            this.chunkLevel = chunkLevel;
            this.message = message;
        }

        public IJssController.PublicationLevel getChunkLevel() {
            return chunkLevel;
        }

        public String getMessage() {
            return message;
        }

    }

    /**
     * An abstract class to perform lengthy GUI-interaction action's tasks in a
     * background thread.
     *
     * <p>
     * Several background threads can be used to execute such tasks. However,
     * the exact strategy of choosing a thread for any particular
     * {@code AbstractJssActionWorker} is unspecified and should not be relied
     * on.</p>
     *
     * <p>
     * When writing a multi-threaded application using Swing, there are two
     * constraints to keep in mind: (refer to
     * <a href="http://java.sun.com/docs/books/tutorial/uiswing/misc/threads.html">
     * How to Use Threads
     * </a> for more details):</p>
     * <ul>
     * <li> Time-consuming tasks should not be run on the <i>Event Dispatch
     * Thread</i>. Otherwise the application becomes unresponsive.</li>
     * <li> Swing components should be accessed on the <i>Event Dispatch
     * Thread</i> only.</li>
     * </ul>
     *
     * <p>
     * These constraints mean that a GUI application with time intensive
     * computing needs at least two threads: 1) a thread to perform the lengthy
     * task and 2) the <i>Event Dispatch Thread</i> (EDT) for all GUI-related
     * activities. This involves inter-thread communication which can be tricky
     * to implement.</p>
     *
     * <p>
     * {@code AbstractJssActionWorker} is designed for situations where you need
     * to have a long running action run in a background thread and provide
     * updates to the UI either when done, or while processing. Subclasses of
     * {@code AbstractJssActionWorker} must implement the
     * {@link #doInBackground} method to perform the background computation.</p>
     *
     *
     * <p>
     * <strong>Workflow</strong></p>
     *
     * <p>
     * There are three threads involved in the life cycle of a
     * {@code SwingWorker} :</p>
     * <ul>
     * <li>
     * <i>Current</i> thread: The {@link #execute} method is called on this
     * thread. It schedules {@code SwingWorker} for the execution on a
     * <i>worker</i>
     * thread and returns immediately. One can wait for the {@code SwingWorker}
     * to complete using the {@link #get get} methods.</li>
     * <li>
     * <i>Worker</i> thread: The {@link #doInBackground} method is called on
     * this thread. This is where all background activities should happen. To
     * notify {@code PropertyChangeListeners} about bound properties changes use
     * the {@link #firePropertyChange firePropertyChange} and
     * {@link #getPropertyChangeSupport} methods. By default there are two bound
     * properties available: {@code state} and {@code progress}.</li>
     * <li>
     * <i>Event Dispatch Thread</i>: All Swing related activities occur on this
     * thread. {@code SwingWorker} invokes the {@link #process process} and
     * {@link #done} methods and notifies any {@code PropertyChangeListeners} on
     * this thread.</li>
     * </ul>
     *
     * <p>
     * Often, the <i>Current</i> thread is the <i>Event Dispatch Thread</i>.</p>
     *
     *
     * <p>
     * Before the {@code doInBackground} method is invoked on a <i>worker</i>
     * thread, {@code SwingWorker} notifies any {@code PropertyChangeListeners}
     * about the {@code state} property change to {@code StateValue.STARTED}.
     * After the {@code doInBackground} method is finished the {@code done}
     * method is executed. Then {@code SwingWorker} notifies any
     * {@code PropertyChangeListeners} about the {@code state} property change
     * to {@code StateValue.DONE}.</p>
     *
     * <p>
     * {@code SwingWorker} is only designed to be executed once. Executing a
     * {@code SwingWorker} more than once will not result in invoking the
     * {@code doInBackground} method twice.</p>
     *
     * <p>
     * Because {@code SwingWorker} implements {@code Runnable}, a
     * {@code SwingWorker} can be submitted to an
     * {@link java.util.concurrent.Executor} for execution.</p>
     *
     * @author Mathieu Brunot
     *
     * @since 1.2
     */
    public abstract class AbstractJssActionWorker extends SwingWorker<Integer, JssActionWorkerChunk> {

        /**
         * Reference on the shell controller.
         * 
         * @since 1.2
         */
        final IJssController shellController;

        /**
         * Construct a worker for a shell environment.
         *
         * @param shellController a shell controller
         */
        public AbstractJssActionWorker(IJssController shellController) {
            this.shellController = shellController;
        }

        /**
         * {@inheritDoc }
         */
        @Override
        protected void process(List<JssActionWorkerChunk> chunks) {
            super.process(chunks);
            /* 
             * Due to the asynchronous mode, make sure no message is published
             * when the worker has already ended.
             */
            if (getState() != StateValue.DONE) {
                for (JssActionWorkerChunk chunk : chunks) {
                    this.shellController.publish(chunk.chunkLevel, chunk.message);
                }
            }
        }

        /**
         * {@inheritDoc }
         */
        @Override
        protected void done() {
            super.done();
            // Remove the current worker from the action's active workers list
            AbstractThreadedJssAction.this.activeWorkers.remove(this);
        }

        /**
         * Reference on the main action.
         * 
         * @return the main action.
         * 
         * @since 1.4
         */
        public AbstractThreadedJssAction getParentAction() {
            return AbstractThreadedJssAction.this;
        }

        /**
         * Reference on the shell controller.
         * 
         * @return the shell controller.
         * 
         * @since 1.4
         */
        public IJssController getShellController() {
            return shellController;
        }

    }

}
