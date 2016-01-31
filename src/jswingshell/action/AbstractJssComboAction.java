package jswingshell.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    protected transient Map<T, Collection<String>> switchArgumentsByValue = null;
    protected transient Map<String, T> switchValuesByArgument = null;

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
    private transient ActionGroup innerGroup = null;
    /**
     * The internal {@link ComboElementAction} referencing combo items.
     *
     * @since 1.3
     */
    private transient Collection<ComboElementAction<T>> innerElementActions = null;

    // #########################################################################
    public AbstractJssComboAction() {
        this(new DefaultComboBoxModel<T>());
    }

    public AbstractJssComboAction(T[] items) {
        this(new DefaultComboBoxModel<>(items));
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel) {
        super();
        this.dataModel = aModel;
    }

    public AbstractJssComboAction(IJssController shellController) {
        this(new DefaultComboBoxModel<T>(), shellController);
    }

    public AbstractJssComboAction(T[] items, IJssController shellController) {
        this(new DefaultComboBoxModel<>(items), shellController);
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel, IJssController shellController) {
        super(shellController);
        this.dataModel = aModel;
    }

    public AbstractJssComboAction(IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<T>(), shellController, args);
    }

    public AbstractJssComboAction(T[] items, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<>(items), shellController, args);
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel, IJssController shellController, String[] args) {
        super(shellController, args);
        this.dataModel = aModel;
    }

    public AbstractJssComboAction(String name, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<T>(), name, shellController, args);
    }

    public AbstractJssComboAction(T[] items, String name, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<>(items), name, shellController, args);
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel, String name, IJssController shellController, String[] args) {
        super(name, shellController, args);
        this.dataModel = aModel;
    }

    public AbstractJssComboAction(String name, Icon icon, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<T>(), name, icon, shellController, args);
    }

    public AbstractJssComboAction(T[] items, String name, Icon icon, IJssController shellController, String[] args) {
        this(new DefaultComboBoxModel<>(items), name, icon, shellController, args);
    }

    public AbstractJssComboAction(ComboBoxModel<T> aModel, String name, Icon icon, IJssController shellController, String[] args) {
        super(name, icon, shellController, args);
        this.dataModel = aModel;
    }

    // #########################################################################
    /**
     * Sets the data model that a {@code JComboBox} uses to obtain the list of
     * items.
     *
     * @param aModel the {@code ComboBoxModel} that provides the list of items
     *
     * @beaninfo 
     *       bound: true 
     * description: Model that the combo box uses to get data to display.
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
                T elementActionItem = elementAction.getDataItem();
                if ((anItem == null && anItem == elementActionItem)
                        || (anItem != null && anItem.equals(elementActionItem))) {
                    innerGroup.setSelected(elementAction, true);
                }
            }
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public T getSelectedItem() {
        return (T) dataModel.getSelectedItem();
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
                            T elementActionItem = elementAction.getDataItem();
                            if ((item == null && item == elementActionItem)
                                    || (item != null && item.equals(elementActionItem))) {
                                innerGroup.setSelected(elementAction, true);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void setEnabled(boolean newValue) {
        super.setEnabled(newValue);
        // Enable the internal actions with their parent action
        if (hasInnerElementActions() && getInnerGroup() != null) {
            this.getInnerGroup().setEnabled(newValue);
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
        } else {
            // If no event, retrieve the state of the action itself (should already be updated by Swing)
            T selectedItem = getSelectedItem();
            Map<T, Collection<String>> valuesByArgument = getSwitchArgumentsByValue();
            if (selectedItem != null && valuesByArgument != null
                    && valuesByArgument.containsKey(selectedItem)) {
                String commandIdentifier = getDefaultCommandIdentifier();
                eventArgs = new String[]{commandIdentifier, valuesByArgument.get(selectedItem).iterator().next()};
            }
        }

        return eventArgs;
    }

    @Override
    public int run(IJssController shellController, String[] args) {
        int commandReturnStatus = AbstractJssAction.SUCCESS;

        T switchValue = null;
        if (args != null && args.length > 1) {
            Map<String, T> argumentsByValue = getSwitchValuesByArgument();
            if (args.length == 2 && args[1] != null && argumentsByValue != null) {
                String switchArgument = args[1].trim().toUpperCase();

                switchValue = argumentsByValue.get(switchArgument);
                /* 
                 * XXX What to do...
                 * Here, we are handling the invalid argument received by 
                 * publishing an error message (which is hard coded by the way).
                 * Even if it seem coherent with a "command line only" usage of
                 * the shell, it prevents any kind of interaction with the user,
                 * thus not allowing him to fix his error... 
                 * A solution would be to throw an exception but that would mean
                 * a considerable rework of how actions are run and I cannot
                 * estimate (yet) the side effects this might generate.
                 * Food for thought...
                 */
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
            // If switch did not work, return an error code
            commandReturnStatus = AbstractJssAction.ERROR;
        } else if (!Objects.equals(switchValue, this.getSelectedItem())) {
            // If switch worked but selection has not been updated
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

    private static <T> Map<String, T> constructValuesByArgument(Map<T, Collection<String>> argumentsByValue) {
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
     * Set the inner action group that the internal {@link ComboElementAction}
     * belongs to.
     *
     * @param innerGroup the new inner action group that the internal
     * {@link ComboElementAction} belongs to.
     *
     * @see #getInnerGroup()
     * @see #getInnerElementActions()
     * @see #initInnerElements()
     *
     * @since 1.3
     */
    protected final void setInnerGroup(ActionGroup innerGroup) {
        this.innerGroup = innerGroup;
    }

    /**
     * The internal {@link ComboElementAction}s referencing combo items.
     *
     * <p>
     * Each {@code ComboElementAction} is a switch action which shall trigger
     * one of this combo action value. The consistency between the switch
     * actions and the combo selected item is managed through an
     * {@link ActionGroup}.</p>
     *
     * <p>
     * Calling this method will initialize the internal combo element actions.
     * If you need to check if the action currently has defined those internal
     * actions, use {@link #hasInnerElementActions() }.</p>
     *
     * <p>
     * If you need to update the model for this combo action, make sure to reset
     * the inner elements actions ({@link #resetInnerElements() }) to reflect
     * the changes on the model.</p>
     *
     * @return internal collection of {@link ComboElementAction}s referencing
     * combo items.
     *
     * @see ComboElementAction
     * @see #resetInnerElements()
     * @since 1.3
     */
    public final Collection<ComboElementAction<T>> getInnerElementActions() {
        if (innerElementActions == null) {
            innerElementActions = initInnerElements();
        }
        return innerElementActions;
    }

    /**
     * Does this action has any switch actions?
     *
     * @return {@code true} if the action has any inner switch actions,
     * {@code false} otherwise.
     *
     * @since 1.4
     */
    public final boolean hasInnerElementActions() {
        return innerElementActions != null ? !innerElementActions.isEmpty() : false;
    }

    /**
     * Initialize internal {@link ComboElementAction}s referencing combo items
     * and the associated internal {@link ActionGroup}.
     *
     * @return the internal {@link ComboElementAction}s referencing combo items.
     *
     * @see #getInnerGroup()
     * @see #getInnerElementActions()
     * @see #resetInnerElements()
     * @since 1.4
     */
    protected Collection<ComboElementAction<T>> initInnerElements() {
        innerGroup = new ActionGroup();
        innerElementActions = new ArrayList<>(dataModel.getSize());

        for (int i = 0, n = dataModel.getSize(); i < n; i++) {
            ComboElementAction<T> elementAction = this.new ComboElementAction<>(this, dataModel.getElementAt(i));
            elementAction.setEnabled(this.isEnabled());
            innerElementActions.add(elementAction);
            innerGroup.add(elementAction);
        }

        return innerElementActions;
    }

    /**
     * Reset the internal {@link ComboElementAction}s and internal
     * {@link ActionGroup}.
     *
     * @see #initInnerElements()
     * @since 1.3
     */
    public void resetInnerElements() {
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

        private transient String commandBriefHelp;

        private final AbstractJssComboAction<T> parentAction;

        private final T dataItem;

        /**
         * Construct a {@code ComboElementAction} from a parent
         * {@link AbstractJssComboAction} and one of its associated model's
         * data.
         *
         * @param parentAction the parent {@link AbstractJssComboAction}
         * @param dataItem the parent action model's data to associate to this
         * {@code ComboElementAction}
         */
        protected ComboElementAction(AbstractJssComboAction<T> parentAction, T dataItem) {
            super(dataItem.equals(parentAction.getSelectedItem()), dataItem.toString(), parentAction.getDefaultShellController(), new String[]{parentAction.getDefaultCommandIdentifier(), dataItem.toString()});

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

            this.setGroup(this.parentAction.getInnerGroup());
        }

        /**
         * Get the parent action.
         *
         * @return the parent {@link AbstractJssComboAction}.
         *
         * @since 1.4
         */
        public final AbstractJssComboAction<T> getParentAction() {
            return parentAction;
        }

        /**
         * The data item associated to this {@link ComboElementAction}.
         *
         * @return the data item associated to this {@link ComboElementAction}.
         *
         * @since 1.4
         */
        public final T getDataItem() {
            return dataItem;
        }

        // #########################################################################
        @Override
        protected boolean doSwitch(IJssController shellController, Boolean switchValue) {
            boolean switchDone = false;

            if (switchValue) {
                switchDone = parentAction.doSwitch(dataItem);
                if (switchDone
                        && !Objects.equals(switchValue, parentAction.getSelectedItem())) {
                    parentAction.setSelectedItem(dataItem);
                }
            }

            return switchDone;
        }

        @Override
        public final String[] getCommandIdentifiers() {
            return identifiers;
        }

        @Override
        public final String getBriefHelp() {
            if (commandBriefHelp == null) {
                commandBriefHelp = this.initBriefHelp();
            }
            return commandBriefHelp;
        }

        /**
         * Initialize the action's brief help.
         *
         * @return the action's brief help.
         *
         * @see #getBriefHelp()
         * @see #resetBriefHelp()
         *
         * @since 1.4
         */
        protected String initBriefHelp() {
            // Initialize the element brief help from the parent action and item
            this.commandBriefHelp = "Set " + parentAction.getDefaultCommandIdentifier() + " to " + dataItem.toString();
            return this.commandBriefHelp;
        }

        /**
         * Reset to {@code null} the action's brief help.
         *
         * <p>
         * The next call to {@link #getBriefHelp() } will initialize the
         * action's brief help. In the meantime, the brief help will remain
         * empty.</p>
         *
         * @see #getBriefHelp()
         * @see #initBriefHelp()
         *
         * @since 1.4
         */
        public final void resetBriefHelp() {
            commandBriefHelp = null;
        }

    }
}
