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

	public final static int HORSEMAN_MAX_LIFE = 25;

	public final static int HORSEMAN_DEFAULT_ATTACK_DAMAGE = 7;

	public static final int HORSEMAN_RANGE = 1;

	public static final int HORSEMAN_MOVEMENT_POINTS = 7;

	public final static int HORSEMAN_COST = 20;

	public Horseman(Player owner, Pos pos) {
		super(UnitType.HORSEMAN, Surface.UNIT_HORSEMAN, owner, HORSEMAN_RANGE, HORSEMAN_COST);
		this.attackDamage = HORSEMAN_DEFAULT_ATTACK_DAMAGE;
		this.unitMovementPoints = HORSEMAN_MOVEMENT_POINTS;
		this.unitMaxLife = HORSEMAN_MAX_LIFE;
		this.unitCurrentLife = HORSEMAN_MAX_LIFE;
		this.unitLevel = STARTING_LEVEL;
		this.position = pos;
	}

	@Override
	public void levelUpUnit() {
		this.unitLevel++;
		//The horseman get +10 HP.
		this.unitMaxLife += (2 * HORSEMAN_MAX_LIFE) / 5;
		this.unitCurrentLife += (2 * HORSEMAN_MAX_LIFE) / 5;
		//The horseman get +4 AD.
		this.attackDamage += 4;
	}

}
