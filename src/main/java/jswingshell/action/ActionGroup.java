package jswingshell.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * This class is used to create a multiple-exclusion scope for a set of actions.
 *
 * <p>
 * Creating a set of actions with the same {@code ActionGroup} object means that
 * turning "on" one of those actions turns off all other actions in the
 * group.</p>
 * <p>
 * A {@code ActionGroup} can be used with any set of objects that inherit from
 * {@code AbstractJssSwitchAction}.</p>
 * <p>
 * Initially, all actions in the group are unselected.</p>
 *
 * @author Mathieu Brunot
 *
 * @since 1.3
 * @see AbstractJssSwitchAction
 */
public class ActionGroup implements Serializable {

    /**
     * The list of actions participating in this group.
     */
    protected Collection<AbstractJssSwitchAction> actions = new ArrayList<>();

    /**
     * The current selection.
     */
    AbstractJssSwitchAction selection = null;

    /**
     * Creates a new {@code ActionGroup}.
     */
    public ActionGroup() {
    }

    /**
     * Adds the action to the group.
     *
     * @param a the action to be added
     */
    public void add(AbstractJssSwitchAction a) {
        if (a == null) {
            return;
        }
        actions.add(a);

        // If action has a "selected" state at true and the group has no selection
        Boolean isActionSelected = a.isSelected();
        if (selection == null && isActionSelected != null && isActionSelected) {
            selection = a;
        } else {
            a.setSelected(false);
        }

        a.setGroup(this);
    }

    /**
     * Removes the action from the group.
     *
     * @param a the action to be removed
     */
    public void remove(AbstractJssSwitchAction a) {
        if (a == null) {
            return;
        }
        actions.remove(a);
        if (a == selection) {
            selection = null;
        }
        a.setGroup(null);
    }

    /**
     * Clears the selection such that none of the actions in the
     * {@code ActionGroup} are selected.
     */
    public void clearSelection() {
        if (selection != null) {
            AbstractJssSwitchAction oldSelection = selection;
            selection = null;
            oldSelection.setSelected(false);
        }
    }

    /**
     * Returns all the actions that are participating in this group.
     *
     * @return a unmodifiable {@code Collection} of the actions in this group
     */
    public Collection<AbstractJssSwitchAction> getElements() {
        return Collections.unmodifiableCollection(actions);
    }

    /**
     * Returns the selected action.
     *
     * @return the selected action
     */
    public AbstractJssSwitchAction getSelection() {
        return selection;
    }

    /**
     * Sets the selected value for the {@code AbstractJssSwitchAction}. Only one
     * action in the group may be selected at a time.
     *
     * @param a the {@code AbstractJssSwitchAction}
     * @param b {@code true} if this action is to be selected, otherwise
     * {@code false}
     */
    public void setSelected(AbstractJssSwitchAction a, boolean b) {
        if (b && a != null && a != selection) {
            AbstractJssSwitchAction oldSelection = selection;
            selection = a;
            if (oldSelection != null) {
                oldSelection.setSelected(false);
            }
            a.setSelected(true);
        }
    }

    /**
     * Returns whether a {@code AbstractJssSwitchAction} is selected.
     *
     * @param a the {@code AbstractJssSwitchAction}
     * @return {@code true} if the action is selected, otherwise returns
     * {@code false}
     */
    public boolean isSelected(AbstractJssSwitchAction a) {
        return (a == selection);
    }

    /**
     * Returns the number of actions in the group.
     *
     * @return the action count
     */
    public int getActionCount() {
        if (actions == null) {
            return 0;
        } else {
            return actions.size();
        }
    }

    /**
     * Sets whether the {@code ActionGroup} is enabled.
     *
     * <p>
     * Enabling or disabling an {@code ActionGroup} consists in
     * enabling/disabling all actions contained in the group.</p>
     *
     * @param newValue {@code true} to enable the actions in the group,
     * {@code false} to disable them
     * 
     * @see Action#setEnabled
     * 
     * @since 1.4
     */
    public void setEnabled(boolean newValue) {
        if (actions == null || actions.isEmpty()) {
            return;
        }

        for (AbstractJssSwitchAction action : actions) {
            action.setEnabled(newValue);
        }
    }
}
