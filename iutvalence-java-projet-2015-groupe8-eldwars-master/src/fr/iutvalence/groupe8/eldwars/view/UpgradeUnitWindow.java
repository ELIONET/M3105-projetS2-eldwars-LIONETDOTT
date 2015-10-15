package fr.iutvalence.groupe8.eldwars.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import sun.awt.IconInfo;
import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;
import fr.iutvalence.groupe8.eldwars.model.units.Bowman;
import fr.iutvalence.groupe8.eldwars.model.units.Commander;
import fr.iutvalence.groupe8.eldwars.model.units.Horseman;
import fr.iutvalence.groupe8.eldwars.model.units.Soldier;
import fr.iutvalence.groupe8.eldwars.model.units.UnitType;

public class UpgradeUnitWindow extends JFrame {

	/**
	 * The confirmation text.
	 */
	private JLabel confirmationText;

	/**
	 * The selected Unit's texture.
	 */
	private JLabel unitTexture;

	/**
	 * The specs panel that contains the two sub panels.
	 */
	private JPanel specsPanel;

	/**
	 * The Unit's current specs.
	 */
	private JPanel currentSpecs;

	/**
	 * The Unit's current level.
	 */
	private JLabel currentLevel;

	/**
	 * The Unit's current max life.
	 */
	private JLabel currentMaxLife;

	/**
	 * The Unit's current movement points.
	 */
	private JLabel currentMvtPoints;

	/**
	 * The Unit's current attack points.
	 */
	private JLabel currentAtkPoints;

	/**
	 * The Unit's current range.
	 */
	private JLabel currentRange;

	/**
	 * The Unit's new specs.
	 */
	private JPanel newSpecs;

	/**
	 * The Unit's new level.
	 */
	private JLabel newLevel;

	/**
	 * The Unit's new max life.
	 */
	private JLabel newMaxLife;

	/**
	 * The Unit's new movement points.
	 */
	private JLabel newMvtPoints;

	/**
	 * The Unit's new attack points.
	 */
	private JLabel newAtkPoints;

	/**
	 * The Unit's new range.
	 */
	private JLabel newRange;

	/**
	 * The buttons container.
	 */
	private JPanel buttonsContainer;

	/**
	 * The cancel button.
	 */
	private JButton cancelBtn;

	/**
	 * The confirm button.
	 */
	private JButton confirmBtn;

	/**
	 * The upgrade cost.
	 */
	private JLabel cost;
	
	/**
	 * A boolean representing if the Player clicked a button.
	 */
	private boolean choosen;

	/**
	 * A boolean representing the Player's choice.
	 */
	private boolean confirmation;

	/**
	 * The UpgradeUnitWindow constructor.
	 * 
	 * @param unitType
	 * @param currentLevel
	 * @param currentMaxLife
	 * @param currentMvtPoints
	 * @param currentAtkDamage
	 * @param currentRange
	 * @param unitCost
	 */
	public UpgradeUnitWindow(UnitType unitType, int currentLevel, int currentMaxLife, int currentMvtPoints, int currentAtkDamage, int currentRange, int unitCost) {

		this.confirmation = false;
		this.choosen = false;

		setTitle("Upgrade a unit");
		setSize(300, 204);
		getContentPane().setSize(300, 204);
		setUndecorated(true);
		((JComponent) getContentPane()).setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setOpacity(0.92f);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		
		
		// The Unit's texture.
		this.unitTexture = new JLabel(unitType.toString());
		this.unitTexture.setAlignmentX(CENTER_ALIGNMENT);
		this.unitTexture.setIcon(new ImageIcon(getClass().getResource("textures/" + unitType.toString().toLowerCase() + ".png").getFile()));
		this.unitTexture.setSize(32, 32);
		add(this.unitTexture);

		
		
		// Specs panel.
		this.specsPanel = new JPanel();
		this.specsPanel.setLayout(new GridLayout(1, 2));
		add(this.specsPanel);

		// Current specs.
		this.currentSpecs = new JPanel();
		this.currentSpecs.setAlignmentX(CENTER_ALIGNMENT);
		this.currentSpecs.setLayout(new BoxLayout(this.currentSpecs, BoxLayout.PAGE_AXIS));
		this.currentSpecs.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.specsPanel.add(this.currentSpecs);

		// Current level.
		this.currentLevel = new JLabel(String.valueOf(currentLevel));
		this.currentLevel.setAlignmentX(CENTER_ALIGNMENT);
		this.currentLevel.setFont(new Font("Arial", 32, 32));
		this.currentLevel.setAlignmentX(CENTER_ALIGNMENT);
		this.currentSpecs.add(this.currentLevel);

		// Current max life.
		this.currentMaxLife = new JLabel(String.valueOf(currentMaxLife));
		this.currentMaxLife.setAlignmentX(CENTER_ALIGNMENT);
		this.currentMaxLife.setIcon(new ImageIcon(getClass().getResource("textures/usLife.png").getFile()));
		this.currentSpecs.add(this.currentMaxLife);

		// Current movement points.
		this.currentMvtPoints = new JLabel(String.valueOf(currentMvtPoints));
		this.currentMvtPoints.setAlignmentX(CENTER_ALIGNMENT);
		this.currentMvtPoints.setIcon(new ImageIcon(getClass().getResource("textures/usMvt.png").getFile()));
		this.currentSpecs.add(this.currentMvtPoints);

		// Current movement points.
		this.currentAtkPoints = new JLabel(String.valueOf(currentAtkDamage));
		this.currentAtkPoints.setAlignmentX(CENTER_ALIGNMENT);
		this.currentAtkPoints.setIcon(new ImageIcon(getClass().getResource("textures/usAtk.png").getFile()));
		this.currentSpecs.add(this.currentAtkPoints);

		// Current range.
		this.currentRange = new JLabel(String.valueOf(currentRange));
		this.currentRange.setAlignmentX(CENTER_ALIGNMENT);
		this.currentRange.setIcon(new ImageIcon(getClass().getResource("textures/usRange.png").getFile()));
		this.currentSpecs.add(this.currentRange);

		
		
		// New specs.
		this.newSpecs = new JPanel();
		this.newSpecs.setAlignmentX(CENTER_ALIGNMENT);
		this.newSpecs.setLayout(new BoxLayout(this.newSpecs, BoxLayout.PAGE_AXIS));
		this.newSpecs.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.specsPanel.add(this.newSpecs);

		// New level.
		this.newLevel = new JLabel(String.valueOf(currentLevel + 1));
		this.newLevel.setAlignmentX(CENTER_ALIGNMENT);
		this.newLevel.setFont(new Font("Arial", 32, 32));
		this.newLevel.setAlignmentX(CENTER_ALIGNMENT);
		this.newSpecs.add(this.newLevel);

		// New max life.
		this.newMaxLife = new JLabel();
		this.newMaxLife.setAlignmentX(CENTER_ALIGNMENT);

		if (unitType == UnitType.COMMANDER)
			this.newMaxLife.setText(String.valueOf(currentMaxLife + (3 * Commander.COMMANDER_MAX_LIFE) / 15));
		else if (unitType == UnitType.SOLDIER)
			this.newMaxLife.setText(String.valueOf(currentMaxLife + 7));
		else if (unitType == UnitType.BOWMAN)
			this.newMaxLife.setText(String.valueOf(currentMaxLife + 0.5 * Bowman.BOWMAN_MAX_LIFE));
		else if (unitType == UnitType.HORSEMAN)
			this.newMaxLife.setText(String.valueOf(currentMaxLife + (2 * Horseman.HORSEMAN_MAX_LIFE) / 5));

		this.newMaxLife.setIcon(new ImageIcon(getClass().getResource("textures/usLife.png").getFile()));
		this.newSpecs.add(this.newMaxLife);

		// New movement points.
		this.newMvtPoints = new JLabel(String.valueOf(currentMvtPoints));
		this.newMvtPoints.setAlignmentX(CENTER_ALIGNMENT);
		this.newMvtPoints.setIcon(new ImageIcon(getClass().getResource("textures/usMvt.png").getFile()));
		this.newSpecs.add(this.newMvtPoints);

		// New movement points.
		this.newAtkPoints = new JLabel();
		this.newAtkPoints.setAlignmentX(CENTER_ALIGNMENT);

		if (unitType == UnitType.COMMANDER)
			this.newAtkPoints.setText(String.valueOf(currentAtkDamage + 5));
		else if (unitType == UnitType.SOLDIER)
			this.newAtkPoints.setText(String.valueOf(currentAtkDamage + 0.5 * Soldier.SOLDIER_DEFAULT_ATTACK_DAMAGE));
		else if (unitType == UnitType.BOWMAN)
			this.newAtkPoints.setText(String.valueOf(currentAtkDamage + 0.75 * Bowman.BOWMAN_DEFAULT_ATTACK_DAMAGE));
		else if (unitType == UnitType.HORSEMAN)
			this.newAtkPoints.setText(String.valueOf(currentAtkDamage + 4));

		this.newAtkPoints.setIcon(new ImageIcon(getClass().getResource("textures/usAtk.png").getFile()));
		this.newSpecs.add(this.newAtkPoints);

		// New range.
		this.newRange = new JLabel(String.valueOf(currentRange));
		this.newRange.setAlignmentX(CENTER_ALIGNMENT);
		this.newRange.setIcon(new ImageIcon(getClass().getResource("textures/usRange.png").getFile()));
		this.newSpecs.add(this.newRange);
		
		
		
		// The cost.
		this.cost = new JLabel();
		this.cost.setAlignmentX(CENTER_ALIGNMENT);

		if (unitType == UnitType.COMMANDER)
			this.cost.setText("Cost : " + (currentLevel * Commander.COMMANDER_COST));
		else if (unitType == UnitType.SOLDIER)
			this.cost.setText("Cost : " + (currentLevel * Soldier.SOLDIER_COST));
		else if (unitType == UnitType.BOWMAN)
			this.cost.setText("Cost : " + (currentLevel * Bowman.BOWMAN_COST));
		else if (unitType == UnitType.HORSEMAN)
			this.cost.setText("Cost : " + (currentLevel * Horseman.HORSEMAN_COST));

		this.cost.setIcon(new ImageIcon(getClass().getResource("textures/psGold.png").getFile()));
		add(this.cost);

		
		// The confirmation text.
		this.confirmationText = new JLabel("Are you sure to upgrade this unit?");
		this.confirmationText.setAlignmentX(CENTER_ALIGNMENT);
		add(this.confirmationText);
		
		
		// Buttons container.
		this.buttonsContainer = new JPanel();
		this.buttonsContainer.setAlignmentX(CENTER_ALIGNMENT);
		this.setPreferredSize(new Dimension(getWidth(), 32));
		add(this.buttonsContainer);

		// Cancel button.
		this.cancelBtn = new JButton("No");
		this.cancelBtn.setSize(64, this.cancelBtn.getHeight());
		this.cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				UpgradeUnitWindow window = (UpgradeUnitWindow) btn.getParent().getParent().getParent().getParent().getParent();
				window.setConfirmation(false);
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		this.buttonsContainer.add(this.cancelBtn);

		// Confirm button.
		this.confirmBtn = new JButton("Yes");
		this.confirmBtn.setSize(64, this.confirmBtn.getHeight());
		this.confirmBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				UpgradeUnitWindow window = (UpgradeUnitWindow) btn.getParent().getParent().getParent().getParent().getParent();
				window.setConfirmation(true);
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		this.buttonsContainer.add(this.confirmBtn);

	}

	protected void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
		this.choosen = true;
	}

	protected boolean getConfirmation() {
		return this.confirmation;
	}
	
	private boolean isChoosen() {
		return this.choosen;
	}

	public boolean showAndWaitForResult() {
		setVisible(true);
		while (isVisible() && !isChoosen()) {
			try {
				Thread.currentThread();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setVisible(false);
		return getConfirmation();
	}
}
