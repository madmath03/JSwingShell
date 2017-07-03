package jswingshell;

import java.util.Collection;

import jswingshell.action.IJssAction;

/**
 * A simple shell model.
 *
 * @author Mathieu Brunot
 */
public class JssSimpleModel extends AbstractJssModel {

  /**
   * The {@code serialVersionUID}.
   */
  private static final long serialVersionUID = -1068968720410737151L;

  /**
   * Construct a shell model with no controller.
   * 
   * @see #setController(jswingshell.IJssController)
   * @see AbstractJssModel#AbstractJssModel()
   * 
   * @since 1.4
   */
  protected JssSimpleModel() {
    super();
  }

  /**
   * Construct a shell model, with no controller, and the available commands.
   *
   * @param actions the available commands.
   *
   * @see #setController(jswingshell.IJssController)
   *
   * @since 1.4
   */
  protected JssSimpleModel(Collection<IJssAction> actions) {
    super(actions);
  }

  /**
   * Construct a shell model and initializes the controller.
   *
   * @param controller the shell controller to attach to this shell model.
   *
   * @see AbstractJssModel#AbstractJssModel(jswingshell.IJssController)
   */
  public JssSimpleModel(IJssController controller) {
    super(controller);
  }

  /**
   * Construct a shell model and initializes the controller and the initial capacity for available
   * commands.
   *
   * @param controller the shell controller to attach to this shell model.
   * @param initialCapacity the initial capacity for available commands.
   *
   * @see AbstractJssModel#AbstractJssModel(jswingshell.IJssController, int)
   */
  public JssSimpleModel(IJssController controller, int initialCapacity) {
    super(controller, initialCapacity);
  }

  /**
   * Construct a shell model and initializes the controller and the available commands.
   *
   * @param controller the shell controller to attach to this shell model.
   * @param actions the available commands.
   *
   * @see AbstractJssModel#AbstractJssModel(jswingshell.IJssController, java.util.Collection)
   */
  public JssSimpleModel(IJssController controller, Collection<IJssAction> actions) {
    super(controller, actions);
  }

  /**
   * Construct a copy of a shell model.
   *
   * <p>
   * This will create a copy of the model's available actions and attach the new model to the same
   * controller. If you need to attach the new model to another controller, please look at
   * {@link JssSimpleModel#JssSimpleModel(jswingshell.IJssController, jswingshell.JssSimpleModel) }.
   * </p>
   *
   * @param anotherModel the shell model to copy.
   *
   * @see AbstractJssModel#AbstractJssModel(jswingshell.AbstractJssModel)
   */
  public JssSimpleModel(AbstractJssModel anotherModel) {
    super(anotherModel);
  }

  /**
   * Construct a copy of a shell model and attach it to a different controller.
   *
   * @param anotherModel the shell model to copy.
   * @param anotherController the shell controller to attach to this shell model.
   *
   * @see AbstractJssModel#AbstractJssModel(jswingshell.IJssController,
   *      jswingshell.AbstractJssModel)
   */
  public JssSimpleModel(IJssController anotherController, JssSimpleModel anotherModel) {
    super(anotherController, anotherModel);
  }

}
