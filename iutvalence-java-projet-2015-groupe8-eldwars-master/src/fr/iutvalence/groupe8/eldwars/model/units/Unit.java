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

	/**
	 * The level one.
	 */
	protected final static int LEVEL_ONE = 1;

	/**
	 * The Unit's type.
	 */
	protected final UnitType type;

	/**
	 * The Unit's max life.
	 */
	protected int maxLife;

	/**
	 * The Unit's current life.
	 */
	protected int life;

	/**
	 * The Unit's level
	 */
	protected int level;

	/**
	 * The Unit's movement points.
	 */
	protected int movementPoints;

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
		this.type = type;
		this.surface = surface;
		this.owner = owner;
		this.range = range;
		this.cost = cost;
	}

	public Unit(UnitType type, Pos pos, Player owner, int range, Surface surface, int cost, int maxLife, int movementPoints, int attackDamage, int level) {
		this.type = type;
		this.surface = surface;
		this.owner = owner;
		this.range = range;
		this.position = pos;
		this.cost = cost;
		this.maxLife = maxLife;
		this.life = maxLife;
		this.movementPoints = movementPoints;
		this.level = level;
		this.attackDamage = attackDamage;
	}

	/**
	 * Gets the Unit's life.
	 * 
	 * @return An integer.
	 */
	public int getLife() {
		return life;
	}

	/**
	 * Sets the Unit's life.
	 * 
	 * @param life
	 *            - The new Unit's life.
	 */
	public void setLife(int life) {
		this.life = life;
		if (this.life < 0)
			this.life = 0;
	}

	/**
	 * Gets true if the Unit is dead.
	 * 
	 * @return A boolean.
	 */
	public boolean isDead() {
		return (this.life == 0);
	}

	/**
	 * Gets the Unit's actual level.
	 * 
	 * @return An integer.
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets how many positions this Units can move.
	 * 
	 * @return An integer.
	 */
	public int getMovementPoints() {
		return this.movementPoints;
	}

	/**
	 * Sets how many positions this Units can move.
	 * 
	 * @param movementPoints
	 *            - The movement points.
	 */
	public void setMovementPoints(int movementPoints) {
		this.movementPoints = movementPoints;
	}

	/**
	 * Gets the Unit's maximum life.
	 * 
	 * @return An integer.
	 */
	public int getMaxLife() {
		return this.maxLife;
	}

	/**
	 * Sets the Unit's maximum life.
	 * 
	 * @param maxLife
	 *            - The new maximum.
	 */
	public void setMaxLife(int maxLife) {
		this.maxLife = maxLife;
	}

	/**
	 * Gets the Unit's type.
	 * 
	 * @return A UnitType.
	 */
	public UnitType getType() {
		return type;
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
