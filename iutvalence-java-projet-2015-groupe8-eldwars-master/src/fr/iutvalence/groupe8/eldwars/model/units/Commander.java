package fr.iutvalence.groupe8.eldwars.model.units;

import fr.iutvalence.groupe8.eldwars.Player;
import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.map.Grid;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;

/**
 * Commander class
 * 
 * @author Emmanuel,Clément
 *
 */
public class Commander extends Unit {

	/**
	 * Commander max life.
	 */
	public final static int COMMANDER_MAX_LIFE = 100;
	/*
	 * public final static int COMMANDER_MAX_LIFE = 5;
	 */

	/**
	 * Commander default attack damage.
	 */
	public final static int COMMANDER_DEFAULT_ATTACK_DAMAGE = 12;

	/**
	 * Commander range.
	 */
	public final static int COMMANDER_RANGE = 2;

	/**
	 * Soldier movement points.
	 */
	public static final int COMMANDER_MOVEMENT_POINTS = 3;

	/**
	 * Commander cost.
	 */
	// We cannot train another commander, but we need a cost to upgrade it.
	public final static int COMMANDER_COST = 30;

	/**
	 * Commander constructor.
	 * 
	 * @param owner
	 */
	public Commander(Player owner, Pos pos) {
		super(UnitType.COMMANDER, pos, owner, COMMANDER_RANGE, Surface.UNIT_COMMANDER, COMMANDER_COST, COMMANDER_MAX_LIFE, COMMANDER_MOVEMENT_POINTS, COMMANDER_DEFAULT_ATTACK_DAMAGE, 1);
	}

	/**
	 * Allows to up the commander level.
	 */
	@Override
	public void levelUpUnit() {
		this.level++;
		//The commander get +30 HP.
		this.maxLife += (3 * COMMANDER_MAX_LIFE) / 15;
		this.life += (3 * COMMANDER_MAX_LIFE) / 15;
		//The commander get +5 AD.
		this.attackDamage += 5;
	}

}
