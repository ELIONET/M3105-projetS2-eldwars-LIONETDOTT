package fr.iutvalence.groupe8.eldwars.model.units;

import fr.iutvalence.groupe8.eldwars.Player;
import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;

/**
 * Horseman Class
 * 
 * @author Emmanuel,Clément
 *
 */

public class Horseman extends Unit {

	/**
	 * Horseman max life.
	 */
	public final static int HORSEMAN_MAX_LIFE = 25;

	/**
	 * Horseman default attack damage.
	 */
	public final static int HORSEMAN_DEFAULT_ATTACK_DAMAGE = 7;

	/**
	 * Horseman range.
	 */
	public static final int HORSEMAN_RANGE = 1;

	/**
	 * Horseman movement points.
	 */
	public static final int HORSEMAN_MOVEMENT_POINTS = 7;

	/**
	 * Horseman cost.
	 */
	public final static int HORSEMAN_COST = 20;

	/**
	 * Horseman constructor.
	 * 
	 * @param owner
	 * @param cost
	 */
	public Horseman(Player owner, Pos pos) {
		super(UnitType.HORSEMAN, Surface.UNIT_HORSEMAN, owner, HORSEMAN_RANGE, HORSEMAN_COST);
		this.attackDamage = HORSEMAN_DEFAULT_ATTACK_DAMAGE;
		this.movementPoints = HORSEMAN_MOVEMENT_POINTS;
		this.maxLife = HORSEMAN_MAX_LIFE;
		this.life = HORSEMAN_MAX_LIFE;
		this.level = LEVEL_ONE;
		this.position = pos;
	}

	/**
	 * Allows to increase the horseman level.
	 */
	@Override
	public void levelUpUnit() {
		this.level++;
		//The horseman get +10 HP.
		this.maxLife += (2 * HORSEMAN_MAX_LIFE) / 5;
		this.life += (2 * HORSEMAN_MAX_LIFE) / 5;
		//The horseman get +4 AD.
		this.attackDamage += 4;
	}

}
