package jswingshell.action;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import jswingshell.IJssController;

/**
 * A {@code AbstractJssSwitchAction} is a switch operation (<em>on/off</em>)
 * that can be called through an id and a parameter.
 *
 * <p>
 * This action is used to toggle a state from {@code TRUE} to {@code FALSE}. It
 * is higly appropriate for {@code JCheckBox} and {@code JCheckBoxMenuItem} GUI
 * components.</p>
 *
 * <p>
 * Note: this class has a natural ordering that is inconsistent with equals.</p>
 *
 * @author Mathieu Brunot
 */
public abstract class AbstractJssSwitchAction extends AbstractJssAction {

    protected static final Collection<String> ON_ARGUMENTS;

    static {
        Set<String> onArguments = new HashSet<>();
        onArguments.add("1");
        onArguments.add("TRUE");
        onArguments.add("Y");
        onArguments.add("ON");
        ON_ARGUMENTS = Collections.unmodifiableSet(onArguments);
    }

    protected static final Collection<String> OFF_ARGUMENTS;

    static {
        Set<String> offArguments = new HashSet<>();
        offArguments.add("0");
        offArguments.add("FALSE");
        offArguments.add("N");
        offArguments.add("OFF");
        OFF_ARGUMENTS = Collections.unmodifiableSet(offArguments);
    }

    // #########################################################################
    /**
     * The action group that the action belongs to.
     *
     * @since 1.3
     */
    protected ActionGroup group = null;

    public AbstractJssSwitchAction() {
        super();
    }

    public AbstractJssSwitchAction(Boolean selected) {
        super();
        setSelected(selected);
    }

    public AbstractJssSwitchAction(IJssController shellController) {
        super(shellController);
    }

    public AbstractJssSwitchAction(Boolean selected, IJssController shellController) {
        super(shellController);
        setSelected(selected);
    }

    public AbstractJssSwitchAction(IJssController shellController, String[] args) {
        super(shellController, args);
    }

    public AbstractJssSwitchAction(Boolean selected, IJssController shellController, String[] args) {
        super(shellController, args);
        setSelected(selected);
    }

    public AbstractJssSwitchAction(String name, IJssController shellController, String[] args) {
        super(name, shellController, args);
    }

    public AbstractJssSwitchAction(Boolean selected, String name, IJssController shellController, String[] args) {
        super(name, shellController, args);
        setSelected(selected);
    }

    public AbstractJssSwitchAction(String name, Icon icon, IJssController shellController, String[] args) {
        super(name, icon, shellController, args);
    }

    public AbstractJssSwitchAction(Boolean selected, String name, Icon icon, IJssController shellController, String[] args) {
        super(name, icon, shellController, args);
        setSelected(selected);
    }

    // #########################################################################
    /**
     * Get an extensive text describing the use of this shell command in regards
     * to a given shell.
     *
     * @param shellController The shell controller for which we should retrieve
     * the action's help. This is useful for
     *
     * @return a string describing the use of this shell command.
     */
    @Override
    public String getHelp(IJssController shellController) {
        StringBuilder stringBuilder = new StringBuilder();

        String commandIdsAsString = this.getCommandIdentifiersAsString();
        stringBuilder.append(this.getBriefHelp());
        stringBuilder.append("\n\t").append(commandIdsAsString);
        stringBuilder.append("\n");
        stringBuilder.append("\n").append("You can set switch as follow:");
        stringBuilder.append("\n\t").append(commandIdsAsString).append(" ").append(getOnArgumentsAsString());
        stringBuilder.append("\n\t").append(commandIdsAsString).append(" ").append(getOffArgumentsAsString());

        return stringBuilder.toString();
    }

    @Override
    public int run(IJssController shellController, String[] args) {
        int commandReturnStatus = AbstractJssAction.SUCCESS;

        // Init with property (already updated by JCheckBox items)
        Boolean switchValue = null;
        if (args != null && args.length > 1) {
            if (args.length == 2 && args[1] != null) {
                String switchArgument = args[1].trim().toUpperCase();

                if (getOnArguments().contains(switchArgument)) {
                    switchValue = true;
                } else if (getOffArguments().contains(switchArgument)) {
                    switchValue = false;
                } else {
                    switchValue = null;
                    if (shellController != null) {
                        shellController.publish(IJssController.PublicationLevel.WARNING, switchArgument + " is not a valid value");
                    }
                }

            }

            if (shellController != null && switchValue == null) {
                shellController.publish(IJssController.PublicationLevel.WARNING, getHelp(shellController));
            }
        } else {
            // If no arguments were given, toggle the state of the action
            Boolean isActionSelected = isSelected();
            if (isActionSelected != null) {
                switchValue = !isActionSelected;
            }
        }

        // Do switch
        if (switchValue != null && doSwitch(shellController, switchValue)) {
            setSelected(switchValue);
        } else {
            commandReturnStatus = AbstractJssAction.ERROR;
        }

        return commandReturnStatus;
    }

    @Override
    protected String[] extractArgumentsFromEvent(ActionEvent e) {
        String[] eventArgs = null;

        if (e != null) {
            if (e.getSource() instanceof JCheckBox) {
                JCheckBox sourceCombo = (JCheckBox) e.getSource();
                String commandIdentifier = getDefaultCommandIdentifier();
                boolean isSelected = sourceCombo.isSelected();
                if (isSelected && getOnArguments() != null && !getOnArguments().isEmpty()) {
                    eventArgs = new String[]{commandIdentifier, getOnArguments().iterator().next()};
                } else if (getOffArguments() != null && !getOffArguments().isEmpty()) {
                    eventArgs = new String[]{commandIdentifier, getOffArguments().iterator().next()};
                }
            } else if (e.getSource() instanceof JCheckBoxMenuItem) {
                JCheckBoxMenuItem sourceCombo = (JCheckBoxMenuItem) e.getSource();
                String commandIdentifier = getDefaultCommandIdentifier();
                boolean isSelected = sourceCombo.isSelected();
                if (isSelected && getOnArguments() != null && !getOnArguments().isEmpty()) {
                    eventArgs = new String[]{commandIdentifier, getOnArguments().iterator().next()};
                } else if (getOffArguments() != null && !getOffArguments().isEmpty()) {
                    eventArgs = new String[]{commandIdentifier, getOffArguments().iterator().next()};
                }
            }
        } else {
            // If some dummy removed the event, retrieve the state of the action itself (should already be updated by Swing)
            Boolean isActionSelected = isSelected();
            if (isActionSelected != null) {
                String commandIdentifier = getDefaultCommandIdentifier();
                if (isActionSelected && getOnArguments() != null && !getOnArguments().isEmpty()) {
                    eventArgs = new String[]{commandIdentifier, getOnArguments().iterator().next()};
                } else if (getOffArguments() != null && !getOffArguments().isEmpty()) {
                    eventArgs = new String[]{commandIdentifier, getOffArguments().iterator().next()};
                }
            }
        }

        return eventArgs;
    }

    /**
     * Get arguments allowed to enable this switch action.
     *
     * <p>
     * The returned collection is unmodifiable.</p>
     *
     * @return arguments allowed to enable this switch action.
     */
    public Collection<String> getOnArguments() {
        return ON_ARGUMENTS;
    }

    /**
     * Get a string desrcibing the arguments allowed to enable this switch
     * action.
     *
     * @return a string desrcibing the arguments allowed to enable this switch
     * action.
     */
    public final String getOnArgumentsAsString() {
        return getArgumentsAsString(getOnArguments());
    }

    /**
     * Get arguments allowed to disable this switch action.
     *
     * <p>
     * The returned collection is unmodifiable.</p>
     *
     * @return arguments allowed to disable this switch action.
     */
    public Collection<String> getOffArguments() {
        return OFF_ARGUMENTS;
    }

    /**
     * Get a string desrcibing the arguments allowed to disable this switch
     * action.
     *
     * @return a string desrcibing the arguments allowed to disable this switch
     * action.
     */
    public final String getOffArgumentsAsString() {
        return getArgumentsAsString(getOffArguments());
    }

    /**
     * The actual switch operation for this action.
     *
     * @param switchValue the value for the switch.
     *
     * @return {@code true} if the switch was done.
     */
    protected boolean doSwitch(Boolean switchValue) {
        return doSwitch(getDefaultShellController(), switchValue);
    }

    /**
     * The actual switch operation for this action.
     *
     * @param shellController the shell controller for which to execute the
     * action
     *
     * @param switchValue the value for the switch.
     *
     * @return {@code true} if the switch was done.
     */
    protected abstract boolean doSwitch(IJssController shellController, Boolean switchValue);

    @Override
    public void putValue(String key, Object newValue) {
        if (SELECTED_KEY.equals(key) && newValue instanceof Boolean) {
            Boolean selected = (Boolean) newValue;
            // Update group selection
            if (selected && group != null && group.getSelection() != this) {
                group.setSelected(this, selected);
            }
            super.putValue(SELECTED_KEY, selected);
        } else {
            super.putValue(key, newValue);
        }
    }

    /**
     * Indicates if the action has been selected.
     *
     * @return {@code true} if the action is selected
     *
     * @since 1.3
     */
    public final Boolean isSelected() {
        return (Boolean) getValue(SELECTED_KEY);
    }

    /**
     * Selects or deselects the action.
     *
     * @param selected {@code true} selects the action, {@code false} deselects
     * the action
     *
     * @since 1.3
     */
    public final void setSelected(Boolean selected) {
        putValue(SELECTED_KEY, selected);
    }

    /**
     * Identifies the group the action belongs to -- needed for radio buttons,
     * which are mutually exclusive within their group.
     *
     * @param group the {@code ActionGroup} the action belongs to
     *
     * @since 1.3
     */
    public final void setGroup(ActionGroup group) {
        this.group = group;
    }

    /**
     * Returns the group that the action belongs to. Normally used with radio
     * buttons, which are mutually exclusive within their group.
     *
     * @return the {@code ActionGroup} that the action belongs to
     *
     * @since 1.3
     */
    public final ActionGroup getGroup() {
        return group;
    }
}
