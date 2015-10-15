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

	private Unit unitOnThisCell;

	private Surface cellSurface;

	
	public Cell() {
		this(null, Surface.GRASS);
	}
	
	public Cell(Unit unit) {
		this(null, Surface.GRASS);
	}
	
	public Cell(Surface surface)
	{
		this(null, surface);
	}
	
	public Cell(Unit unit, Surface surface) {
		this.unitOnThisCell = unit;
		this.cellSurface = surface;
	}

	public Unit getUnitOnThisCell() {
		return this.unitOnThisCell;
	}

	public void setUnitOnThisCell(Unit unit) {
		this.unitOnThisCell = unit;
	}

	public void setUnitOnASpecificCell(Unit unit, Pos pos) {
		this.unitOnThisCell = unit;
		this.unitOnThisCell.setPosition(pos);
	}

	public void removeUnitFromThisCell() {
		this.unitOnThisCell = null;
	}

	public Surface getCellSurface() {
		return this.cellSurface;
	}

	public void setCellSurface(Surface surface) {
		this.cellSurface = surface;
	}

}
