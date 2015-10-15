package fr.iutvalence.groupe8.eldwars.view.actions;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Abstract class for the buttons.
 * 
 * @author Nicolas
 * @version 20150610
 */
public abstract class Action extends JButton {
	/**
	 * The Action's type.
	 */
	private final ActionType type;

	/**
	 * The Action constructor.
	 * 
	 * @param type
	 *            - The Action's type.
	 */
	public Action(ActionType type) {
		super();
		this.type = type;
	}

	/**
	 * Get the Action's type.
	 * 
	 * @return
	 */
	public ActionType getType() {
		return this.type;
	}

	/**
	 * Greys the button.
	 * 
	 * @param bool
	 *            - True to grey the button. False to gives it its colors.
	 */
	public abstract void grey(boolean bool);

}
