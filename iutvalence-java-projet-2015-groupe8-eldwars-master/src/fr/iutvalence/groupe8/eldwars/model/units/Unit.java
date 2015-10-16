package fr.iutvalence.groupe8.eldwars.model.units;

import fr.iutvalence.groupe8.eldwars.Player;

import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.map.Cell;
import fr.iutvalence.groupe8.eldwars.model.map.Grid;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;

/**
 * The abstract Unit class.
 * 
 * @author Nicolas
 *
 */
public abstract class Unit {
	
	protected final static int STARTING_LEVEL = 1;

	protected final UnitType unitType;

	protected int unitMaxLife;

	protected int unitCurrentLife;

	protected int unitLevel;

	protected int unitMovementPoints;

	/**
	 * The Unit's attack damage.
	 */
	protected int attackDamage;

	/**
	 * The Unit's range.
	 */
	protected int range;

	/**
	 * The Unit's owner.
	 */
	protected final Player owner;

	/**
	 * The Unit's cost.
	 */
	protected final int cost;

	/**
	 * The Unit's position.
	 */
	protected Pos position;

	/**
	 * The Unit's surface.
	 */
	protected Surface surface;

	/**
	 * The Unit constructor (test).
	 * 
	 * @param type
	 */
	public Unit(UnitType type, Surface surface, Player owner, int range, int cost) {
		this.unitType = type;
		this.surface = surface;
		this.owner = owner;
		this.range = range;
		this.cost = cost;
	}

	public Unit(UnitType type, Pos pos, Player owner, int range, Surface surface, int cost, int maxLife, int movementPoints, int attackDamage, int level) {
		this.unitType = type;
		this.surface = surface;
		this.owner = owner;
		this.range = range;
		this.position = pos;
		this.cost = cost;
		this.unitMaxLife = maxLife;
		this.unitCurrentLife = maxLife;
		this.unitMovementPoints = movementPoints;
		this.unitLevel = level;
		this.attackDamage = attackDamage;
	}

	/**
	 * Gets the Unit's life.
	 * 
	 * @return An integer.
	 */
	public int getLife() {
		return unitCurrentLife;
	}

	/**
	 * Sets the Unit's life.
	 * 
	 * @param life
	 *            - The new Unit's life.
	 */
	public void setLife(int life) {
		this.unitCurrentLife = life;
		if (this.unitCurrentLife < 0)
			this.unitCurrentLife = 0;
	}

	/**
	 * Gets true if the Unit is dead.
	 * 
	 * @return A boolean.
	 */
	public boolean isDead() {
		return (this.unitCurrentLife == 0);
	}

	/**
	 * Gets the Unit's actual level.
	 * 
	 * @return An integer.
	 */
	public int getLevel() {
		return unitLevel;
	}

	/**
	 * Gets how many positions this Units can move.
	 * 
	 * @return An integer.
	 */
	public int getMovementPoints() {
		return this.unitMovementPoints;
	}

	/**
	 * Sets how many positions this Units can move.
	 * 
	 * @param movementPoints
	 *            - The movement points.
	 */
	public void setMovementPoints(int movementPoints) {
		this.unitMovementPoints = movementPoints;
	}

	/**
	 * Gets the Unit's maximum life.
	 * 
	 * @return An integer.
	 */
	public int getMaxLife() {
		return this.unitMaxLife;
	}

	/**
	 * Sets the Unit's maximum life.
	 * 
	 * @param maxLife
	 *            - The new maximum.
	 */
	public void setMaxLife(int maxLife) {
		this.unitMaxLife = maxLife;
	}

	/**
	 * Gets the Unit's type.
	 * 
	 * @return A UnitType.
	 */
	public UnitType getType() {
		return unitType;
	}

	/**
	 * Gets how many points this Unit inflicts to others.
	 * 
	 * @return An integer.
	 */
	public int getAttackDamage() {
		return this.attackDamage;
	}

	/**
	 * Sets how many points this Unit inflicts to others.
	 * 
	 * @param attackdamage
	 *            - The new attack damage points.
	 */
	public void setAttackDamage(int attackdamage) {
		this.attackDamage = attackdamage;
	}

	/**
	 * Gets the Unit's attack range.
	 * 
	 * @return An integer.
	 */
	public int getRange() {
		return this.range;
	}

	/**
	 * Gets the Unit's owner.
	 * 
	 * @return A Player.
	 */
	public Player getOwner() {
		return this.owner;
	}

	/**
	 * Gets the Unit's Position.
	 * 
	 * @return A Pos.
	 */
	public Pos getPosition() {
		return position;
	}

	/**
	 * Sets the Unit's position. Using this method will NOT move its position on
	 * the Grid.
	 * 
	 * @param position
	 *            - The new position.
	 */
	public void setPosition(Pos position) {
		this.position = position;
	}

	/**
	 * Get the Unit's Surface.
	 * 
	 * @return A Surface.
	 */
	public Surface getSurface() {
		return this.surface;
	}

	/**
	 * Get the Unit's cost.
	 * 
	 * @return An int.
	 */
	public int getCost() {
		return this.cost;
	}
	
	@Override
	public boolean equals(Object el) {
		if (el instanceof Unit)
			return this == el;
		return false;
	}

	public abstract void levelUpUnit();

}
