package fr.iutvalence.groupe8.eldwars.model.units;

import fr.iutvalence.groupe8.eldwars.Player;
import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;

/**
 * Bowman Class
 * 
 * @author Emmanuel,Clément
 *
 */
public class Bowman extends Unit {

	/**
	 * Bowman max life.
	 */
	public final static int BOWMAN_MAX_LIFE = 10;

	/**
	 * Bowman default attack damage.
	 */
	public final static int BOWMAN_DEFAULT_ATTACK_DAMAGE = 4;

	/**
	 * Bowman range.
	 */
	public final static int BOWMAN_RANGE = 4;

	/**
	 * Bowman movement points.
	 */
	public static final int BOWMAN_MOVEMENT_POINTS = 4;

	/**
	 * Bowman cost.
	 */
	public final static int BOWMAN_COST = 15;

	/**
	 * Bowman constructor.
	 * 
	 * @param owner
	 * @param cost
	 */
	public Bowman(Player owner, Pos pos) {
		super(UnitType.BOWMAN, Surface.UNIT_BOWMAN, owner, BOWMAN_RANGE, BOWMAN_COST);
		this.attackDamage = BOWMAN_DEFAULT_ATTACK_DAMAGE;
		this.movementPoints = BOWMAN_MOVEMENT_POINTS;
		this.maxLife = BOWMAN_MAX_LIFE;
		this.life = BOWMAN_MAX_LIFE;
		this.level = LEVEL_ONE;
		this.position = pos;
	}

	/**
	 * Allows to increase the bowman level.
	 */
	@Override
	public void levelUpUnit() {
		this.level++;
		//The bowman get +5 HP.
		this.maxLife += 0.5 * BOWMAN_MAX_LIFE;
		this.life += 0.5 * BOWMAN_MAX_LIFE;
		//The bowman get +3 AD.
		this.attackDamage += 0.75 * BOWMAN_DEFAULT_ATTACK_DAMAGE;
	}

}
