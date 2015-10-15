package fr.iutvalence.groupe8.eldwars.model.map;

import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.units.Unit;

/**
 * The Cell class.
 * 
 * @author Nicolas
 * @version 20150521
 *
 */
public class Cell {

	/**
	 * The Unit placed in this Cell.
	 */
	private Unit unit;

	/**
	 * the Cell type.
	 */
	private Surface surface;

	/**
	 * The default Cell constructor.
	 */
	public Cell() {
		this(null, Surface.GRASS);
	}

	/**
	 * The Cell constructor with a given Unit.
	 * 
	 * @param unit
	 */
	public Cell(Unit unit) {
		this(null, Surface.GRASS);
	}
	
	/**
	 * The Cell constructor with a given surface.
	 * 
	 * @param surface
	 */
	public Cell(Surface surface)
	{
		this(null, surface);
	}

	/**
	 * The Cell constructor with a given Unit and Surface.
	 * 
	 * @param unit
	 * @param surface
	 */
	public Cell(Unit unit, Surface surface) {
		this.unit = unit;
		this.surface = surface;
	}

	/**
	 * Get the unit placed in this Cell.
	 * 
	 * @return A Unit.
	 */
	public Unit getUnit() {
		return this.unit;
	}

	/**
	 * Set the unit placed in this Cell.
	 * 
	 * @param unit
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	
	/**
	 * Set the unit placed in this Cell.
	 * @param unit
	 * @param pos
	 */
	public void setUnitv2(Unit unit, Pos pos) {
		this.unit = unit;
		this.unit.setPosition(pos);
	}
	/**
	 * Clear the Unit placed in this Cell.
	 */
	public void clearUnit() {
		this.unit = null;
	}

	/**
	 * Get the Cell's surface type.
	 * 
	 * @return The Cell's surface type.
	 */
	public Surface getSurface() {
		return this.surface;
	}

	/**
	 * Set the Cell's surface type.
	 * 
	 * @param surface
	 */
	public void setSurface(Surface surface) {
		this.surface = surface;
	}

}
