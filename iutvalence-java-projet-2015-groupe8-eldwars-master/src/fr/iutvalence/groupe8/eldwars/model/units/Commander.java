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

	public final static int COMMANDER_MAX_LIFE = 100;
	
	/*
	 * public final static int COMMANDER_MAX_LIFE = 5;
	 */

	public final static int COMMANDER_DEFAULT_ATTACK_DAMAGE = 12;

	public final static int COMMANDER_RANGE = 2;

	public static final int COMMANDER_MOVEMENT_POINTS = 3;

	// We cannot train another commander, but we need a cost to upgrade it.
	public final static int COMMANDER_COST = 30;

	public Commander(Player owner, Pos pos) {
		super(UnitType.COMMANDER, pos, owner, COMMANDER_RANGE, Surface.UNIT_COMMANDER, COMMANDER_COST, COMMANDER_MAX_LIFE, COMMANDER_MOVEMENT_POINTS, COMMANDER_DEFAULT_ATTACK_DAMAGE, 1);
	}

	@Override
	public void levelUpUnit() {
		this.unitLevel++;
		//The commander get +30 HP.
		this.unitMaxLife += (3 * COMMANDER_MAX_LIFE) / 15;
		this.unitCurrentLife += (3 * COMMANDER_MAX_LIFE) / 15;
		//The commander get +5 AD.
		this.attackDamage += 5;
	}

}
