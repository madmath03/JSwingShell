package jswingshell;

import java.util.Collection;
import jswingshell.action.IJssAction;

/**
 * The Shell model interface.
 *
 * <p>
 * A shell model stores all actions available for the shell environment, as well
 * as methods to manage those actions.</p>
 *
 * @author Mathieu Brunot
 *
 * @see IJssAction
 */
public interface IJssModel {

    // #########################################################################
    // MVC methods
    /**
     * Get the shell controller attached to this shell model.
     *
     * @return the shell controller attached to this shell model.
     */
    public IJssController getController();

    // #########################################################################
    // Actions methods
    /**
     * Clear all available commands.
     */
    public void clear();

    /**
     * Add an action to the available actions.
     *
     * @param action the action to add.
     * @return {@code true} if the action was added
     */
    public boolean add(IJssAction action);

    /**
     * Add several actions to the available actions.
     *
     * @param actions the action to add.
     * @return {@code true} if the available actions were changed.
     */
    public boolean addAll(Collection<? extends IJssAction> actions);

    /**
     * Remove ac action from the available actions.
     *
     * @param action the action to remove.
     * @return {@code true} if the action was removed
     */
    public boolean remove(IJssAction action);

    /**
     * Remove several actions from the available actions.
     *
     * @param actions the action to remove.
     * @return {@code true} if the available actions were changed.
     */
    public boolean removeAll(Collection<? extends IJssAction> actions);

    /**
     * Does a given action belongs to the shell available actions?
     *
     * @param action action whose presence in the shell available actions is to
     * be tested
     * @return {@code true} if the given action belongs to the shell available
     * actions.
     */
    public boolean contains(IJssAction action);

    /**
     * Do a given collection of actions belongs to the shell available actions?
     *
     * @param actions collection of actions to be checked for containment in the
     * shell available actions
     * @return {@code true} if the given actions belongs to the shell available
     * actions.
     */
    public boolean containsAll(Collection<? extends IJssAction> actions);

    /**
     * Retains only the actions in this shell available actions that are
     * contained in the specified collection of actions.
     *
     * @param actions collection containing actions to be retained in the shell
     * available actions
     * @return {@code true} if the given actions belongs to the shell available
     * actions.
     */
    public boolean retainAll(Collection<? extends IJssAction> actions);

    /**
     * Is the list of available actions empty?
     *
     * @return {@code true} if there are no available actions.
     */
    public boolean isEmpty();

    /**
     * Returns the number of available actions in this shell model (its
     * cardinality).
     *
     * <p>
     * If this set contains more than <tt>Integer.MAX_VALUE</tt> elements,
     * returns <tt>Integer.MAX_VALUE</tt>.</p>
     *
     * @return the number of available actions in this model (its cardinality)
     */
    public int size();

    /**
     * Get an action by its command identifier.
     *
     * @param commandIdentifier the actions's command identifier.
     * @return the action corresponding to the command identifier.
     */
    public IJssAction getActionForCommandIdentifier(String commandIdentifier);
}
