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

	public final static int BOWMAN_MAX_LIFE = 10;

	public final static int BOWMAN_DEFAULT_ATTACK_DAMAGE = 4;

	public final static int BOWMAN_RANGE = 4;

	public static final int BOWMAN_MOVEMENT_POINTS = 4;

	public final static int BOWMAN_COST = 15;

	public Bowman(Player owner, Pos pos) {
		super(UnitType.BOWMAN, Surface.UNIT_BOWMAN, owner, BOWMAN_RANGE, BOWMAN_COST);
		this.attackDamage = BOWMAN_DEFAULT_ATTACK_DAMAGE;
		this.unitMovementPoints = BOWMAN_MOVEMENT_POINTS;
		this.unitMaxLife = BOWMAN_MAX_LIFE;
		this.unitCurrentLife = BOWMAN_MAX_LIFE;
		this.unitLevel = STARTING_LEVEL;
		this.position = pos;
	}

	@Override
	public void levelUpUnit() {
		this.unitLevel++;
		//The bowman get +5 HP.
		this.unitMaxLife += 0.5 * BOWMAN_MAX_LIFE;
		this.unitCurrentLife += 0.5 * BOWMAN_MAX_LIFE;
		//The bowman get +3 AD.
		this.attackDamage += 0.75 * BOWMAN_DEFAULT_ATTACK_DAMAGE;
	}

}
