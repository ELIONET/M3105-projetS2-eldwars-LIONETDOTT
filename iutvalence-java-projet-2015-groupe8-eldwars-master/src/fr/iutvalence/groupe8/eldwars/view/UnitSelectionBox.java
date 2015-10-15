package fr.iutvalence.groupe8.eldwars.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;
import fr.iutvalence.groupe8.eldwars.model.units.Bowman;
import fr.iutvalence.groupe8.eldwars.model.units.Horseman;
import fr.iutvalence.groupe8.eldwars.model.units.Soldier;
import fr.iutvalence.groupe8.eldwars.model.units.UnitType;

/**
 * The UnitSelectionBox class shows the specs of a UnitType in order to buy it.
 * 
 * @author Nicolas
 * @version 20150610
 */
public class UnitSelectionBox extends JPanel {

	/**
	 * True if the button has been clicked.
	 */
	private boolean choosen;

	/**
	 * The Unit's type.
	 */
	private UnitType unitType;

	/**
	 * The JLabel for the Unit's type.
	 */
	private JLabel unitTypeName;

	/**
	 * The JLabel for the Unit's life.
	 */
	private JLabel life;

	/**
	 * The JLabel for the Unit's range.
	 */
	private JLabel range;

	/**
	 * The JLabel for the Unit's movement points.
	 */
	private JLabel mvt;

	/**
	 * The JLabel for the Unit's attack points.
	 */
	private JLabel attack;

	/**
	 * The JLabel for the Unit's cost.
	 */
	private JLabel cost;

	/**
	 * The JLabel for the Unit's life.
	 */
	private JButton buyBtn;

	/**
	 * The UnitSelectionBox constructor.
	 * 
	 * @param unitType
	 *            - The Unit's type.
	 */
	public UnitSelectionBox(UnitType unitType) {

		// Initialization
		this.unitType = unitType;

		this.unitTypeName = new JLabel(unitType.toString());
		this.unitTypeName.setAlignmentX(CENTER_ALIGNMENT);
		this.unitTypeName.setIcon(new ImageIcon(getClass().getResource("textures/" + unitType.toString().toLowerCase() + ".png")));
		this.unitTypeName.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0));
		add(this.unitTypeName);

		this.life = new JLabel();

		if (unitType == UnitType.BOWMAN)
			this.life.setText(String.valueOf(Bowman.BOWMAN_MAX_LIFE));

		else if (unitType == UnitType.HORSEMAN)
			this.life.setText(String.valueOf(Horseman.HORSEMAN_MAX_LIFE));

		else if (unitType == UnitType.SOLDIER)
			this.life.setText(String.valueOf(Soldier.SOLDIER_MAX_LIFE));

		this.life.setToolTipText(unitType.toString() + "'s default maximum life.");
		this.life.setAlignmentX(CENTER_ALIGNMENT);
		this.life.setIcon(new ImageIcon(getClass().getResource("textures/usLife.png")));
		this.life.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		add(this.life);

		this.range = new JLabel();

		if (unitType == UnitType.BOWMAN)
			this.range.setText(String.valueOf(Bowman.BOWMAN_RANGE));

		else if (unitType == UnitType.HORSEMAN)
			this.range.setText(String.valueOf(Horseman.HORSEMAN_RANGE));

		else if (unitType == UnitType.SOLDIER)
			this.range.setText(String.valueOf(Soldier.SOLDIER_RANGE));

		this.range.setToolTipText(unitType.toString() + "'s default range.");
		this.range.setAlignmentX(CENTER_ALIGNMENT);
		this.range.setIcon(new ImageIcon(getClass().getResource("textures/usRange.png")));
		this.range.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		add(this.range);

		this.mvt = new JLabel();

		if (unitType == UnitType.BOWMAN)
			this.mvt.setText(String.valueOf(Bowman.BOWMAN_MOVEMENT_POINTS));

		else if (unitType == UnitType.HORSEMAN)
			this.mvt.setText(String.valueOf(Horseman.HORSEMAN_MOVEMENT_POINTS));

		else if (unitType == UnitType.SOLDIER)
			this.mvt.setText(String.valueOf(Soldier.SOLDIER_MOVEMENT_POINTS));

		this.mvt.setToolTipText(unitType.toString() + "'s default movement points.");
		this.mvt.setAlignmentX(CENTER_ALIGNMENT);
		this.mvt.setIcon(new ImageIcon(getClass().getResource("textures/usMvt.png")));
		this.mvt.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		add(this.mvt);

		this.attack = new JLabel();

		if (unitType == UnitType.BOWMAN)
			this.attack.setText(String.valueOf(Bowman.BOWMAN_DEFAULT_ATTACK_DAMAGE));

		else if (unitType == UnitType.HORSEMAN)
			this.attack.setText(String.valueOf(Horseman.HORSEMAN_DEFAULT_ATTACK_DAMAGE));

		else if (unitType == UnitType.SOLDIER)
			this.attack.setText(String.valueOf(Soldier.SOLDIER_DEFAULT_ATTACK_DAMAGE));

		this.attack.setToolTipText(unitType.toString() + "'s default attack points.");
		this.attack.setAlignmentX(CENTER_ALIGNMENT);
		this.attack.setIcon(new ImageIcon(getClass().getResource("textures/usAtk.png")));
		this.attack.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		add(this.attack);

		this.cost = new JLabel();

		if (unitType == UnitType.BOWMAN)
			this.cost.setText(String.valueOf(Bowman.BOWMAN_COST));

		else if (unitType == UnitType.HORSEMAN)
			this.cost.setText(String.valueOf(Horseman.HORSEMAN_COST));

		else if (unitType == UnitType.SOLDIER)
			this.cost.setText(String.valueOf(Soldier.SOLDIER_COST));

		this.cost.setToolTipText(unitType.toString() + "'s price.");
		this.cost.setAlignmentX(CENTER_ALIGNMENT);
		this.cost.setIcon(new ImageIcon(getClass().getResource("textures/psGold.png")));
		this.cost.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
		add(this.cost);

		this.buyBtn = new JButton("Recruit");
		this.buyBtn.setAlignmentX(CENTER_ALIGNMENT);
		this.buyBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton buyBtn = (JButton) e.getSource();
				UnitSelectionBox box = (UnitSelectionBox) buyBtn.getParent();
				box.setChoosen(true);
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		add(this.buyBtn);

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	}

	/**
	 * Gets if the player clicked the buy button.
	 * 
	 * @return A boolean.
	 */
	public boolean isChoosen() {
		return choosen;
	}

	/**
	 * Sets if the player clicked the buy button.
	 * 
	 * @param choosen
	 *            - True if the player clicked the buy button.
	 */
	public void setChoosen(boolean choosen) {
		this.choosen = choosen;
	}

	/**
	 * Gets the UnitType.
	 * 
	 * @return A UnitType.
	 */
	public UnitType getUnitType() {
		return this.unitType;
	}

}
