package fr.iutvalence.groupe8.eldwars.model.units;

import fr.iutvalence.groupe8.eldwars.Player;

import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;

/**
 * Solider class
 * 
 * @author Clément
 *
 */
public class Soldier extends Unit {

	/**
	 * Soldier max life.
	 */
	public final static int SOLDIER_MAX_LIFE = 15;

	/**
	 * Soldier default attack damage.
	 */
	public final static int SOLDIER_DEFAULT_ATTACK_DAMAGE = 4;

	/**
	 * Soldier range.
	 */
	public static final int SOLDIER_RANGE = 1;

	/**
	 * Soldier movement points.
	 */
	public static final int SOLDIER_MOVEMENT_POINTS = 5;

	/**
	 * Soldier cost.
	 */
	public final static int SOLDIER_COST = 10;

	/**
	 * Soldier constructor.
	 * 
	 * @param owner
	 * @param cost
	 */
	public Soldier(Player owner, Pos pos) {
		super(UnitType.SOLDIER, Surface.UNIT_SOLDIER, owner, SOLDIER_RANGE, SOLDIER_COST);
		this.attackDamage = SOLDIER_DEFAULT_ATTACK_DAMAGE;
		this.movementPoints = SOLDIER_MOVEMENT_POINTS;
		this.maxLife = SOLDIER_MAX_LIFE;
		this.life = SOLDIER_MAX_LIFE;
		this.level = LEVEL_ONE;
		this.position = pos;
	}

	/**
	 * Allows to increase the soldier level.
	 */
	@Override
	public void levelUpUnit() {
		this.level++;
		//The soldier get +7 HP.
		this.maxLife += 7;
		this.life += 7;
		//The soldier get +2 AD.
		this.attackDamage += 0.5 * SOLDIER_DEFAULT_ATTACK_DAMAGE;
	}

}
