package jswingshell.action;

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
 */
public abstract class AbstractThreadedJssAction extends AbstractJssAction {

    // #########################################################################
    public AbstractThreadedJssAction(String name, Icon icon, IJssController shellController, String[] args) {
        super(name, icon, shellController, args);
    }

    public AbstractThreadedJssAction(String name, IJssController shellController, String[] args) {
        super(name, shellController, args);
    }

    public AbstractThreadedJssAction(IJssController shellController, String[] args) {
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
    public int run(IJssController shellController, String[] args) {
        int commandReturnStatus;

        AbstractJssActionWorker worker = this.prepareWorker(shellController, args);
        if (worker != null) {
            commandReturnStatus = IJssAction.IN_PROGRESS;
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

    protected AbstractJssActionWorker prepareWorker(String[] args) {
        return prepareWorker(this.getDefaultShellController(), args);
    }

    protected abstract AbstractJssActionWorker prepareWorker(IJssController shellController, String[] args);

    // #########################################################################
    public static class JssActionWorkerChunk {

        final IJssController.PublicationLevel chunkLevel;
        final String message;

        public JssActionWorkerChunk(IJssController.PublicationLevel chunkLevel, String message) {
            this.chunkLevel = chunkLevel;
            this.message = message;
        }

    }

    public static abstract class AbstractJssActionWorker extends SwingWorker<Integer, JssActionWorkerChunk> {

        final IJssController shellController;

        public AbstractJssActionWorker(IJssController shellController) {
            this.shellController = shellController;
        }

        /**
         * {@inheritDoc }
         */
        @Override
        protected void process(List<JssActionWorkerChunk> chunks) {
            super.process(chunks);
            for (JssActionWorkerChunk chunk : chunks) {
                shellController.publish(chunk.chunkLevel, chunk.message);
            }
        }

        /**
         * {@inheritDoc }
         */
        @Override
        protected void done() {
            super.done();
            shellController.unlockCommandLine();
            shellController.addNewLineToShell();
            shellController.addNewCommandLine();
        }
    }

}
