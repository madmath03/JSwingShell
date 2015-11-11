package jswingshell.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.event.ListDataListener;
import jswingshell.IJssController;

/**
 * A {@code AbstractJssComboAction} is a multiple choice operation that can be
 * called through an id and a parameter.
 *
 * <p>
 * This action is used to define a status or mode among multiple choice. It is
 * higly appropriate for {@code JComboBox} GUI components.</p>
 *
 * <p>
 * Note: this class has a natural ordering that is inconsistent with equals.</p>
 *
 * @author Mathieu Brunot
 *
 * @param <T> Data type of the values associated to this action.
 *
 * @since 1.2
 */
public abstract class AbstractJssComboAction<T> extends AbstractJssAction implements ComboBoxModel<T> {

    protected Map<T, Collection<String>> switchArgumentsByValue;
    protected Map<String, T> switchValuesByArgument;

    /**
     * This protected field is implementation specific. Do not access directly
     * or override. Use the accessor methods instead.
     *
     * @see #getModel
     * @see #setModel
     */
    protected ComboBoxModel<T> dataModel;
    /**
     * The inner action group that the internal {@link ComboElementAction}
     * belongs to.
     *
     * @since 1.3
     */
    private ActionGroup innerGroup = null;
    /**
     * The internal {@link ComboElementAction} referencing combo items.
     *
     * @since 1.3
     */
    private Collection<ComboElementAction<T>> innerElementActions;

    // #########################################################################
    public AbstractJssComboAction() {
        this(new DefaultComboBoxModel<T>());
    }

    public AbstractJssComboAction(T[] items) {
        this(new DefaultComboBoxModel<>(items));
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel) {
        super();
        this.setModel(aModel);
    }

    public AbstractJssComboAction(IJssController shellController) {
        this(new DefaultComboBoxModel<T>(), shellController);
    }

    public AbstractJssComboAction(T[] items, IJssController shellController) {
        this(new DefaultComboBoxModel<>(items), shellController);
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel, IJssController shellController) {
        super(shellController);
        this.setModel(aModel);
    }

    public AbstractJssComboAction(IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<T>(), shellController, args);
    }

    public AbstractJssComboAction(T[] items, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<>(items), shellController, args);
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel, IJssController shellController, String[] args) {
        super(shellController, args);
        this.setModel(aModel);
    }

    public AbstractJssComboAction(String name, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<T>(), name, shellController, args);
    }

    public AbstractJssComboAction(T[] items, String name, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<>(items), name, shellController, args);
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel, String name, IJssController shellController, String[] args) {
        super(name, shellController, args);
        this.setModel(aModel);
    }

    public AbstractJssComboAction(String name, Icon icon, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<T>(), name, icon, shellController, args);
    }

    public AbstractJssComboAction(T[] items, String name, Icon icon, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<>(items), name, icon, shellController, args);
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel, String name, Icon icon, IJssController shellController, String[] args) {
        super(name, icon, shellController, args);
        this.setModel(aModel);
    }

    // #########################################################################
    /**
     * Sets the data model that a {@code JComboBox} uses to obtain the list of
     * items.
     *
     * @param aModel the {@code ComboBoxModel} that provides the list of items
     *
     * @beaninfo bound: true description: Model that the combo box uses to get
     * data to display.
     */
    public final void setModel(ComboBoxModel<T> aModel) {
        this.dataModel = aModel;
    }

    /**
     * Returns the data model currently used by the
     * {@code AbstractJssComboAction}.
     *
     * @return the {@code ComboBoxModel} that provides the displayed list of
     * items
     */
    public final ComboBoxModel<T> getModel() {
        return dataModel;
    }

    /**
     * Set the selected item. The implementation of this method should notify
     * all registered {@code ListDataListener}s that the contents have changed.
     *
     * @param anItem the list object to select or {@code null} to clear the
     * selection
     */
    @Override
    public void setSelectedItem(Object anItem) {
        dataModel.setSelectedItem(anItem);
        // If this is action has a default argument that corresponds to a value
        if (getDefaultArguments() != null && getDefaultArguments().length > 1) {
            String name = getDefaultArguments()[1];
            if (name instanceof String) {
                super.putValue(SELECTED_KEY, getSwitchValuesByArgument().containsKey(name));
            }
        }
        // If this action has a inner group and element actions
        if (innerGroup != null) {
            for (ComboElementAction<T> elementAction : innerElementActions) {
                if ((anItem == null && anItem == elementAction.dataItem) || (anItem != null && anItem.equals(elementAction.dataItem))) {
                    innerGroup.setSelected(elementAction, true);
                }
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object getSelectedItem() {
        return dataModel.getSelectedItem();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getSize() {
        return dataModel.getSize();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T getElementAt(int index) {
        return dataModel.getElementAt(index);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void addListDataListener(ListDataListener l) {
        dataModel.addListDataListener(l);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeListDataListener(ListDataListener l) {
        dataModel.removeListDataListener(l);
    }

    // #########################################################################
    @Override
    public void putValue(String key, Object newValue) {
        Object oldValue = getValue(key);
        if (oldValue == null || !oldValue.equals(newValue)) {
            super.putValue(key, newValue);
            // If this action has a default argument that corresponds to value
            if (SELECTED_KEY.equals(key) && getDefaultArguments() != null && getDefaultArguments().length > 1) {
                String name = getDefaultArguments()[1];
                Map<String, T> argumentsByValue = getSwitchValuesByArgument();
                if (argumentsByValue != null && argumentsByValue.containsKey(name)) {
                    T item = argumentsByValue.get(name);
                    dataModel.setSelectedItem(item);
                    // If this action has a inner group and element actions
                    if (innerGroup != null) {
                        for (ComboElementAction<T> elementAction : innerElementActions) {
                            if ((item == null && item == elementAction.dataItem) || (item != null && item.equals(elementAction.dataItem))) {
                                innerGroup.setSelected(elementAction, true);
                            }
                        }
                    }
                }
            }
        }
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
        stringBuilder.append("\n").append("You can set the value as follow:");
        Map<T, Collection<String>> argumentsByValue = getSwitchArgumentsByValue();
        if (argumentsByValue != null) {
            for (Map.Entry<T, Collection<String>> entry : argumentsByValue.entrySet()) {
                stringBuilder.append("\n\t").append(commandIdsAsString).append(" ").append(getArgumentsAsString(entry.getValue()));
            }
        }

        return stringBuilder.toString();
    }

    @Override
    protected String[] extractArgumentsFromEvent(ActionEvent e) {
        String[] eventArgs = null;

        if (e != null) {
            if (e.getSource() instanceof JComboBox) {
                JComboBox sourceCombo = (JComboBox) e.getSource();
                String commandIdentifier = getDefaultCommandIdentifier();
                Object selectedItem = sourceCombo.getSelectedItem();
                eventArgs = new String[]{commandIdentifier, selectedItem != null ? selectedItem.toString() : null};
            } else if (e.getSource() instanceof JRadioButton) {
                JRadioButton sourceRadio = (JRadioButton) e.getSource();
                String commandIdentifier = getDefaultCommandIdentifier();
                Object selectedItem;
                if (sourceRadio.getActionCommand() != null) {
                    selectedItem = sourceRadio.getActionCommand();
                } else {
                    selectedItem = sourceRadio.getText();
                }
                eventArgs = new String[]{commandIdentifier, selectedItem != null ? selectedItem.toString() : null};
            } else if (e.getSource() instanceof JRadioButtonMenuItem) {
                JRadioButtonMenuItem sourceRadio = (JRadioButtonMenuItem) e.getSource();
                String commandIdentifier = getDefaultCommandIdentifier();
                Object selectedItem;
                if (sourceRadio.getActionCommand() != null) {
                    selectedItem = sourceRadio.getActionCommand();
                } else {
                    selectedItem = sourceRadio.getText();
                }
                eventArgs = new String[]{commandIdentifier, selectedItem != null ? selectedItem.toString() : null};
            }
        }

        return eventArgs;
    }

    @Override
    public int run(IJssController shellController, String[] args
    ) {
        int commandReturnStatus = AbstractJssAction.SUCCESS;

        T switchValue = null;
        if (args != null && args.length > 1) {
            Map<String, T> argumentsByValue = getSwitchValuesByArgument();
            if (args.length == 2 && args[1] != null && argumentsByValue != null) {
                String switchArgument = args[1].trim().toUpperCase();

                switchValue = argumentsByValue.get(switchArgument);
                if (switchValue == null && !argumentsByValue.containsKey(switchArgument)) {
                    shellController.publish(IJssController.PublicationLevel.WARNING, "\"" + switchArgument + "\" is not a valid value.");
                }

            } else if (shellController != null) {
                switchValue = null;
                shellController.publish(IJssController.PublicationLevel.WARNING, getHelp(shellController));
            }
        }

        // Do switch
        if (!doSwitch(shellController, switchValue)) {
            commandReturnStatus = AbstractJssAction.ERROR;
        } else {
            this.setSelectedItem(switchValue);
        }

        return commandReturnStatus;
    }

    public Map<String, T> getSwitchValuesByArgument() {
        if (switchValuesByArgument == null) {
            switchValuesByArgument = constructValuesByArgument(getSwitchArgumentsByValue());
        }
        return switchValuesByArgument;
    }

    private Map<String, T> constructValuesByArgument(Map<T, Collection<String>> argumentsByValue) {
        Map<String, T> valuesByArgument = new HashMap<>();

        if (argumentsByValue != null) {
            for (Map.Entry<T, Collection<String>> entry : argumentsByValue.entrySet()) {
                for (String arg : entry.getValue()) {
                    valuesByArgument.put(arg.trim().toUpperCase(), entry.getKey());
                }
            }
        }

        return valuesByArgument;
    }

    protected Map<T, Collection<String>> getSwitchArgumentsByValue() {
        if (switchArgumentsByValue == null) {
            switchArgumentsByValue = constructArgumentsByValue();
        }
        return switchArgumentsByValue;
    }

    /**
     * The actual switch operation for this action.
     *
     * @param switchValue the value for the switch.
     *
     * @return {@code true} if the switch was done.
     */
    protected boolean doSwitch(T switchValue) {
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
    protected abstract boolean doSwitch(IJssController shellController, T switchValue);

    protected Map<T, Collection<String>> constructArgumentsByValue() {
        Map<T, Collection<String>> argumentsByValue = new HashMap<>(this.getSize());

        for (int i = 0, n = this.getSize(); i < n; i++) {
            T data = this.getElementAt(i);
            if (data != null) {
                argumentsByValue.put(data, Collections.singleton(data.toString()));
            } else {
                argumentsByValue.put(data, Collections.singleton(""));
            }
        }

        return argumentsByValue;
    }

    // #########################################################################
    /**
     * The inner action group that the internal {@link ComboElementAction}
     * belongs to.
     *
     * @return inner action group that the internal {@link ComboElementAction}
     * belongs to.
     *
     * @see #getInnerElementActions()
     * @since 1.3
     */
    protected final ActionGroup getInnerGroup() {
        return innerGroup;
    }

    /**
     * The internal {@link ComboElementAction}s referencing combo items.
     *
     * <p>
     * Each {@code ComboElementAction} is a switch action which shall trigger
     * one of this combo action value. The consistency between the switch
     * actions and the combo selected item is managed through the an
     * {@link ActionGroup}.</p>
     *
     * <p>
     * If you need to update the model for this combo action, make sure to reset
     * the inner elements actions.</p>
     *
     * @return internal collection of {@link ComboElementAction}s referencing
     * combo items.
     *
     * @see ComboElementAction
     * @see #resetInnerElementActions()
     * @since 1.3
     */
    public final Collection<ComboElementAction<T>> getInnerElementActions() {
        if (innerElementActions == null) {
            innerGroup = new ActionGroup();
            innerElementActions = new ArrayList<>(dataModel.getSize());
            for (int i = 0, n = dataModel.getSize(); i < n; i++) {
                ComboElementAction<T> elementAction = this.new ComboElementAction<>(this, dataModel.getElementAt(i));
                innerElementActions.add(elementAction);
                innerGroup.add(elementAction);
            }
        }
        return innerElementActions;
    }

    /**
     * Reset the combo elements switch action and group.
     *
     * @since 1.3
     */
    public final void resetInnerElementActions() {
        if (innerElementActions != null) {
            for (ComboElementAction<T> elementAction : innerElementActions) {
                elementAction.setSelected(false);
                if (elementAction.getGroup() != null && elementAction.getGroup() == innerGroup) {
                    elementAction.getGroup().remove(elementAction);
                    elementAction.setGroup(null);
                }
            }
            innerElementActions.clear();
            innerElementActions = null;
        }
        if (innerGroup != null) {
            innerGroup.clearSelection();
            innerGroup = null;
        }
    }

    // #########################################################################
    /**
     * A {@code ComboElementAction} is a switch operation (<em>on/off</em>) that
     * can be called through an id and a parameter, that refers to a
     * {@link AbstractJssComboAction} item.
     *
     * <p>
     * This action is used to toggle a {@link AbstractJssComboAction} item from
     * {@code TRUE} to {@code FALSE}. It is higly appropriate for
     * {@code JCheckBox} and {@code JCheckBoxMenuItem} GUI components.</p>
     *
     * <p>
     * Note: this class has a natural ordering that is inconsistent with
     * equals.</p>
     *
     * @param <T> Data type of the values associated to this action.
     *
     * @since 1.3
     */
    public class ComboElementAction<T> extends AbstractJssSwitchAction {

        private final String[] identifiers;

        private final String commandBriefHelp;

        private final AbstractJssComboAction<T> parentAction;

        private final T dataItem;

        private ComboElementAction(AbstractJssComboAction<T> parentAction, T dataItem) {
            super(parentAction.getSelectedItem() == dataItem, dataItem.toString(), parentAction.getDefaultShellController(), new String[]{parentAction.getDefaultCommandIdentifier(), dataItem.toString()});

            this.parentAction = parentAction;
            this.dataItem = dataItem;

            // Initialize the element identifiers from the parent action and item
            String[] parentIdentifiers = parentAction.getCommandIdentifiers();
            String[] elementIdentifiers = new String[parentIdentifiers.length];
            for (int i = 0, n = parentIdentifiers.length; i < n; i++) {
                String parentIdentifier = parentIdentifiers[i];
                elementIdentifiers[i] = parentIdentifier + "_" + dataItem.toString();
            }
            this.identifiers = elementIdentifiers;

            // Initialize the element brief help from the parent action and item
            this.commandBriefHelp = "Set " + parentAction.getDefaultCommandIdentifier() + " to " + dataItem.toString();
            this.setGroup(this.parentAction.getInnerGroup());
        }

        @Override
        protected boolean doSwitch(IJssController shellController, Boolean switchValue) {
            boolean switchDone = false;

            if (switchValue) {
                switchDone = parentAction.doSwitch(dataItem);
                if (switchDone) {
                    parentAction.setSelectedItem(dataItem);
                }
            }

            return switchDone;
        }

        @Override
        public String[] getCommandIdentifiers() {
            return identifiers;
        }

        @Override
        public String getBriefHelp() {
            return commandBriefHelp;
        }

    }
}
