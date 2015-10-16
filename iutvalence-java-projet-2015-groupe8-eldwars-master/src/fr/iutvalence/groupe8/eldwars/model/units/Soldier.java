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

	public final static int SOLDIER_MAX_LIFE = 15;

	public final static int SOLDIER_DEFAULT_ATTACK_DAMAGE = 4;

	public static final int SOLDIER_RANGE = 1;

	public static final int SOLDIER_MOVEMENT_POINTS = 5;

	public final static int SOLDIER_COST = 10;

	public Soldier(Player owner, Pos pos) {
		super(UnitType.SOLDIER, Surface.UNIT_SOLDIER, owner, SOLDIER_RANGE, SOLDIER_COST);
		this.attackDamage = SOLDIER_DEFAULT_ATTACK_DAMAGE;
		this.unitMovementPoints = SOLDIER_MOVEMENT_POINTS;
		this.unitMaxLife = SOLDIER_MAX_LIFE;
		this.unitCurrentLife = SOLDIER_MAX_LIFE;
		this.unitLevel = STARTING_LEVEL;
		this.position = pos;
	}

	@Override
	public void levelUpUnit() {
		this.unitLevel++;
		//The soldier get +7 HP.
		this.unitMaxLife += 7;
		this.unitCurrentLife += 7;
		//The soldier get +2 AD.
		this.attackDamage += 0.5 * SOLDIER_DEFAULT_ATTACK_DAMAGE;
	}

}
