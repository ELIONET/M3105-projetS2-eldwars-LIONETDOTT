package fr.iutvalence.groupe8.eldwars.view.actions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.iutvalence.groupe8.eldwars.model.units.UnitType;

/**
 * Unit Stats
 * 
 * @author Florian, Nicolas
 * @version 20150611
 * Java doc : Clément
 *
 */
public class UnitStats extends JPanel
{

	/**
	 * Unit Type.
	 */
	private final static String DEFAULT_TYPE = "No unit selected.";
	
	/**
	 * Unit default level.
	 */
	private static final int DEFAULT_LEVEL = 0;
	
	/**
	 * Unit Default Life.
	 */
	private final static int DEFAULT_LIFE = 0;
	
	/**
	 * Unit default range.
	 */
	private final static int DEFAULT_RANGE = 0;
	
	/**
	 * Unit Default movement points.
	 */
	private final static int DEFAULT_MVT = 0;
	
	/**
	 * Unit default attack.
	 */
	private final static int DEFAULT_ATK = 0;
	
	/**
	 * Label with the Unit's type.
	 */
	private JLabel unitType;

	/**
	 * The container of the specs.
	 */
	private JPanel specsContainer;
	
	/**
	 * Label with the Unit's level.
	 */
	private JLabel unitLevel;

	/**
	 * Label with the Unit's Life amount
	 */
	private JLabel unitLife;

	/**
	 * Label with the Unit's range value
	 */
	private JLabel unitRange;

	/**
	 * Label with the unit's movement point amount
	 */
	private JLabel unitMvt;
	
	/**
	 * Label with the unit's attack value
	 */

	private JLabel unitAttack;

	/**
	 * Construtor of UnitStats without parameters.
	 */
	public UnitStats()
	{
		this(DEFAULT_TYPE, DEFAULT_LEVEL, DEFAULT_LIFE, DEFAULT_RANGE, DEFAULT_MVT, DEFAULT_ATK);

	}

	/**
	 * Construtor of UnitStats.
	 * 
	 * @param type
	 *            - Unit's Type
	 * @param life
	 *            - Unit's Life
	 * @param range
	 *            - Unit's Range
	 * @param mvt
	 *            - Unit's Movement points
	 * @param attack
	 *            - Unit's Attack
	 */
	public UnitStats(String type, int level, int life, int range, int mvt, int attack)
	{
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.unitType = new JLabel();
		this.unitType.setPreferredSize(new Dimension(64, 20));
		this.unitType.setAlignmentX(CENTER_ALIGNMENT);
		if (type != null)
			this.unitType.setText(type);
		else
			this.unitType.setText(DEFAULT_TYPE);
		this.unitType.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 0));
		add(this.unitType);

		// The specs container.
		this.specsContainer = new JPanel();
		this.specsContainer.setAlignmentX(CENTER_ALIGNMENT);
		this.specsContainer.setPreferredSize(new Dimension(getWidth(), 15));
		add(this.specsContainer);

		// The Unit's level.
		this.unitLevel = new JLabel();
		this.unitLevel.setIcon(new ImageIcon(getClass().getResource("../textures/usLevel.png")));
		this.unitLevel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.unitLevel.setToolTipText("The unit's level.");
		this.unitLevel.setText(String.valueOf(level));
		this.specsContainer.add(this.unitLevel);

		// The Unit's life.
		this.unitLife = new JLabel();
		this.unitLife.setIcon(new ImageIcon(getClass().getResource("../textures/usLife.png")));
		this.unitLife.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.unitLife.setToolTipText("The unit's life.");
		this.unitLife.setText(String.valueOf(life));
		this.specsContainer.add(this.unitLife);

		// The Unit's range.
		this.unitRange = new JLabel();
		this.unitRange.setIcon(new ImageIcon(getClass().getResource("../textures/usRange.png")));
		this.unitRange.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.unitRange.setToolTipText("The unit's range.");
		this.unitRange.setText(String.valueOf(range));
		this.specsContainer.add(this.unitRange);
		
		// The Unit's movement points.
		this.unitMvt = new JLabel();
		this.unitMvt.setIcon(new ImageIcon(getClass().getResource("../textures/usMvt.png")));
		this.unitMvt.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.unitMvt.setToolTipText("The unit's movement points.");
		this.unitMvt.setText(String.valueOf(mvt));
		this.specsContainer.add(this.unitMvt);

		// The Unit's attack points.
		this.unitAttack = new JLabel();
		this.unitAttack.setIcon(new ImageIcon(getClass().getResource("../textures/usAtk.png")));
		this.unitAttack.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		this.unitAttack.setToolTipText("The unit's attack points.");
		this.unitAttack.setText(String.valueOf(attack));
		this.specsContainer.add(this.unitAttack);
	}

	/**
	 * Method which reset life, range, movement, attack and type.
	 */
	public void reset()
	{
		if (DEFAULT_TYPE != null)
			this.unitType.setText(DEFAULT_TYPE.toString());
		else
			this.unitType.setText("No unit selected.");
		
		this.unitLife.setText(String.valueOf(DEFAULT_LIFE));
		this.unitRange.setText(String.valueOf(DEFAULT_RANGE));
		this.unitMvt.setText(String.valueOf(DEFAULT_MVT));
		this.unitAttack.setText(String.valueOf(DEFAULT_ATK));
	}

	/**
	 * Method which set the type of the unit.
	 * 
	 * @param type
	 *            - Type of the unit.
	 */
	public void setType(UnitType type)
	{
		if (type != null)
			this.unitType.setText(type.toString());
		else
			this.unitType.setText(DEFAULT_TYPE);
	}

	/**
	 * Method which set the level of the unit.
	 * 
	 * @param level
	 *            - Level of the unit.
	 */
	public void setLevel(int level)
	{
		this.unitLevel.setText(String.valueOf(level));
	}

	/**
	 * Method which set the life of the unit.
	 * 
	 * @param life
	 *            - Life of the unit.
	 */
	public void setLife(String life)
	{
		this.unitLife.setText(life);
	}

	/**
	 * Method which set the range of the unit.
	 * 
	 * @param range
	 *            - Range of the unit.
	 */
	public void setRange(int range)
	{
		this.unitRange.setText(String.valueOf(range));
	}

	/**
	 * Method which set the movement points of the unit.
	 * 
	 * @param mvt
	 *            - Movement points of the unit.
	 */
	public void setMvt(int mvt)
	{
		this.unitMvt.setText(String.valueOf(mvt));
	}

	/**
	 * Method which set the attack of the unit.
	 * 
	 * @param attack
	 *            - Attack of the unit.
	 */
	public void setAttack(int attack)
	{
		this.unitAttack.setText(String.valueOf(attack));
	}

}
