package fr.iutvalence.groupe8.eldwars.view.actions;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import fr.iutvalence.groupe8.eldwars.view.BtnMouseAdapter;
import fr.iutvalence.groupe8.eldwars.view.actions.UnitStats;

/**
 * The area where are placed all Action buttons.
 * 
 * @author Nicolas
 * @version 20150610
 */
public class ActionsArea extends JPanel {

	/**
	 * Used by the method unitSelected. This constant means that the selected
	 * Unit isn't a Commander.
	 */
	public static final int SELECTION_BASIC = 0;

	/**
	 * Used by the method unitSelected. This constant means that the selected
	 * Unit is a Commander.
	 */
	public static final int SELECTION_COMMANDER = 1;

	/**
	 * Used by the method unitSelected. This constant means that there is no
	 * selected Unit.
	 */
	public static final int SELECTION_NONE = 3;

	/**
	 * The height of the actions area.
	 */
	public static final int ACTIONS_AREA_HEIGHT = 128;

	/**
	 * An int that indicates what type of Unit is selected.
	 */
	private int unitSelected;

	/**
	 * Global container.
	 */
	private JPanel globalContainer;

	/**
	 * The container of the different Action buttons.
	 */
	private JPanel actionsContainer;

	/**
	 * The place where the currently selected Unit statistics are.
	 */
	private UnitStats unitStats;

	/**
	 * The move Action button.
	 */
	private Action move;

	/**
	 * The recruit Action button.
	 */
	private Action recruit;

	/**
	 * The attack Action button.
	 */
	private Action attack;

	/**
	 * The upgrade Action button.
	 */
	private Action upgrade;

	/**
	 * The next round Action button.
	 */
	private Action nextRound;

	/**
	 * The place where the current Player statistics are.
	 */
	private PlayerStats playerStats;

	/**
	 * The ActionsArea constructor.
	 */
	public ActionsArea() {

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		this.globalContainer = new JPanel();
		this.globalContainer.setLayout(new BoxLayout(this.globalContainer, BoxLayout.LINE_AXIS));

		this.unitStats = new UnitStats();
		this.unitStats.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
		this.globalContainer.add(this.unitStats);

		this.actionsContainer = new JPanel();
		this.actionsContainer.setLayout(new BoxLayout(this.actionsContainer, BoxLayout.LINE_AXIS));
		this.actionsContainer.setMaximumSize(new Dimension(420, 64));
		this.actionsContainer.setBounds(0, 0, 420, 64);

		this.move = new MoveAction();
		this.move.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.move.setContentAreaFilled(false);
		this.move.setSize(64, 64);
		this.move.setToolTipText("Move a unit.");
		this.move.addMouseListener(new BtnMouseAdapter());
		this.actionsContainer.add(this.move);

		this.recruit = new RecruitAction();
		this.recruit.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.recruit.setContentAreaFilled(false);
		this.recruit.setSize(64, 64);
		this.recruit.setToolTipText("Recruit units.");
		this.recruit.addMouseListener(new BtnMouseAdapter());
		this.actionsContainer.add(this.recruit);

		this.attack = new AttackAction();
		this.attack.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.attack.setContentAreaFilled(false);
		this.attack.setSize(64, 64);
		this.attack.setToolTipText("Attack a unit.");
		this.attack.addMouseListener(new BtnMouseAdapter());
		this.actionsContainer.add(this.attack);

		this.upgrade = new UpgradeAction();
		this.upgrade.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.upgrade.setContentAreaFilled(false);
		this.upgrade.setSize(64, 64);
		this.upgrade.setToolTipText("Upgrade a unit.");
		this.upgrade.addMouseListener(new BtnMouseAdapter());
		this.actionsContainer.add(this.upgrade);

		this.nextRound = new NextRoundAction();
		this.nextRound.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.nextRound.setContentAreaFilled(false);
		this.nextRound.setSize(64, 32);
		this.nextRound.setToolTipText("End your round.");
		this.nextRound.addMouseListener(new BtnMouseAdapter());
		this.actionsContainer.add(this.nextRound);

		this.globalContainer.add(this.actionsContainer);

		this.playerStats = new PlayerStats();
		this.globalContainer.add(this.playerStats);

		add(this.globalContainer);

		unitSelected(SELECTION_NONE);

	}

	/**
	 * Greys the buttons or not.
	 * 
	 * @param unitSelected
	 *            - Constant that means if a unit is currently selected.
	 */
	public void unitSelected(int selectionType) {
		if (selectionType != SELECTION_NONE) {

			if (selectionType == SELECTION_COMMANDER)
				this.recruit.grey(false);
			else
				this.recruit.grey(true);

			this.unitSelected = selectionType;
			this.move.grey(false);
			this.attack.grey(false);
			this.upgrade.grey(false);
			this.nextRound.grey(false);

		} else {

			this.unitSelected = selectionType;
			this.move.grey(true);
			this.recruit.grey(true);
			this.attack.grey(true);
			this.upgrade.grey(true);
			this.nextRound.grey(false);

		}
	}

	/**
	 * Get the currently selected Unit type.
	 * 
	 * @return An int.
	 * @see ActionsArea.SELECTION_BASIC, ActionsArea.SELECTION_COMMANDER,
	 *      ActionsArea.SELECTION_NONE
	 */
	public int getUnitSelected() {
		return this.unitSelected;
	}

	/**
	 * Get The PlayerStats object.
	 * 
	 * @return A PlayerStats object.
	 */
	public PlayerStats getPlayerStats() {
		return this.playerStats;
	}

	/**
	 * Get The UnitStats object.
	 * 
	 * @return A UnitStats object.
	 */
	public UnitStats getUnitStats() {
		return this.unitStats;
	}

}
