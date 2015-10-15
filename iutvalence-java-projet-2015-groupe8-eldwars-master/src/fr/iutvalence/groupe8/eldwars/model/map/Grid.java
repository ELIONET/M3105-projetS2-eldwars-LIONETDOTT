package fr.iutvalence.groupe8.eldwars.model.map;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fr.iutvalence.groupe8.eldwars.Player;
import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.units.Bowman;
import fr.iutvalence.groupe8.eldwars.model.units.Commander;
import fr.iutvalence.groupe8.eldwars.model.units.Horseman;
import fr.iutvalence.groupe8.eldwars.model.units.Soldier;
import fr.iutvalence.groupe8.eldwars.model.units.Unit;
import fr.iutvalence.groupe8.eldwars.model.units.UnitType;

/**
 * The Grid class.
 * 
 * @author Nicolas, Emmanuel
 *
 */
public class Grid {

	/**
	 * The Grid.
	 */
	private final Cell[][] grid;

	/**
	 * The Grid's width.
	 */
	private final int width;

	/**
	 * The Grid's height.
	 */
	private final int height;

	/**
	 * The Grid constructor.
	 * 
	 * @param width
	 * @param height
	 */
	public Grid(int width, int height) {

		this.width = width;

		this.height = height;

		// Initialization of the grid.
		this.grid = new Cell[width][height];

		// Filling the grid with Cells.
		for (int lines = 0; lines < width; lines++) {
			for (int columns = 0; columns < height; columns++) {
				// Will create a grassy map.
				this.grid[lines][columns] = new Cell();
			}
		}
	}

	/**
	 * Get the String representation of the Grid.
	 * 
	 * @return The Grid in a String.
	 */
	public String getText() {
		StringBuilder sb = new StringBuilder();
		for (Cell[] cells : grid) {
			for (Cell cell : cells) {
				sb.append(cell.getSurface());
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	/**
	 * Get the Cell at the specified position.
	 * 
	 * @param x
	 * @param y
	 * @return A Cell.
	 */
	public Cell getCell(int x, int y) {
		return grid[x][y];
	}

	/**
	 * Get the Cell at the specified position.
	 * 
	 * @param pos
	 * @return A Cell.
	 */
	public Cell getCell(Pos pos) {
		return grid[pos.getX()][pos.getY()];
	}

	/**
	 * Gets possible moves around a specific position.
	 * 
	 * @param pos
	 * @return List of Pos.
	 */
	private Set<Pos> possiblesMovesAroundASpecificPosition(Pos pos) {
		HashSet<Pos> listOfCellAroundASpecificPosition = new HashSet<Pos>();
		int x = pos.getX(), y = pos.getY();

		// We must check if positions around the first one aren't out of the
		// grid and are navigable.
		if (x + 1 < this.width && getCell(x + 1, y).getSurface().canWalkOn()
				&& !listOfCellAroundASpecificPosition.contains(pos.shift(1, 0))) {
			listOfCellAroundASpecificPosition.add(pos.shift(1, 0));
		}

		if (x - 1 >= 0
				&& getCell(x - 1, y).getSurface().canWalkOn()
				&& !listOfCellAroundASpecificPosition
						.contains(pos.shift(-1, 0))) {
			listOfCellAroundASpecificPosition.add(pos.shift(-1, 0));
		}

		if (y + 1 < this.height && getCell(x, y + 1).getSurface().canWalkOn()
				&& !listOfCellAroundASpecificPosition.contains(pos.shift(0, 1))) {
			listOfCellAroundASpecificPosition.add(pos.shift(0, 1));
		}

		if (y - 1 >= 0
				&& getCell(x, y - 1).getSurface().canWalkOn()
				&& !listOfCellAroundASpecificPosition
						.contains(pos.shift(0, -1))) {
			listOfCellAroundASpecificPosition.add(pos.shift(0, -1));
		}

		return listOfCellAroundASpecificPosition;
	}
	
	
	/**
	 * Allows to know where the commander can recruit units
	 * @param unitPosition
	 * @return
	 */
	private List<Pos> cellsAroundACell(Pos unitPosition) {
		List<Pos> list = new LinkedList<Pos>();
		if (unitPosition.getX() + 1 <= this.width && getCell(unitPosition.shift(1, 0)).getSurface().canWalkOn()) {
			list.add(unitPosition.shift(1, 0));
		}
		
		if (unitPosition.getX() - 1 >= 0 && getCell(unitPosition.shift(-1, 0)).getSurface().canWalkOn()) {
			list.add(unitPosition.shift(-1, 0));
		}
		
		if (unitPosition.getY() + 1 <= this.height && getCell(unitPosition.shift(0, 1)).getSurface().canWalkOn()) {
			list.add(unitPosition.shift(0, 1));
		}
		
		if (unitPosition.getY() - 1 >= 0 && getCell(unitPosition.shift(0, -1)).getSurface().canWalkOn()) {
			list.add(unitPosition.shift(0, -1));
		}
		return list;
	}

	/**
	 * Get all cells in range of a unit.
	 * 
	 * @param unitPosition
	 * @return List of pos.
	 */
	private List<Pos> cellInRangeOfAttack(Pos unitPosition) {
		Unit unit = getCell(unitPosition).getUnit();
		List<Pos> listOfCellInRangeOfAttack = new LinkedList<Pos>();
		List<Pos> tempList = new LinkedList<Pos>();
		int unitRange = unit.getRange();
		tempList.add(unitPosition);

		while (unitRange > 0) {
			// For every already reachable positions (in term of range), we add
			// every which are one cell further.
			for (int i = 0; i < tempList.size(); i++) {
				if (tempList.get(i).getX() + 1 < this.width)
					listOfCellInRangeOfAttack.add(tempList.get(i).shift(1, 0));
				if (tempList.get(i).getX() - 1 >= 0)
					listOfCellInRangeOfAttack.add(tempList.get(i).shift(-1, 0));
				if (tempList.get(i).getY() + 1 < this.height)
					listOfCellInRangeOfAttack.add(tempList.get(i).shift(0, 1));
				if (tempList.get(i).getY() - 1 >= 0)
					listOfCellInRangeOfAttack.add(tempList.get(i).shift(0, -1));

			}
			unitRange--;
			tempList.addAll(listOfCellInRangeOfAttack);
		}
		return listOfCellInRangeOfAttack;
	}

	/**
	 * Gets all possible moves for a unit on a position.
	 * 
	 * @param unitPosition
	 *            - The Unit to use.
	 * @return The list of possible moves.
	 */
	private List<Pos> possiblesMoves(Pos unitPosition) {

		Unit unit = getCell(unitPosition).getUnit();
		List<Pos> listOfCell = new LinkedList<Pos>();
		List<Pos> tempList = new LinkedList<Pos>();
		int currentmovementPoints = unit.getMovementPoints();

		tempList.addAll(possiblesMovesAroundASpecificPosition(unitPosition));

		for (int i = unit.getMovementPoints(); i > 0; i--) {
			currentmovementPoints--;
			listOfCell.clear();
			listOfCell.addAll(tempList);
			// Before adding moves, we must check if the unit has enough
			// movement points.
			// So, if the unit has at least 1mp, it can forward 1 cell
			// further than every current moves available.
			if (currentmovementPoints > 0) {
				for (int j = 0; j < listOfCell.size(); j++) {
					// Then, for every already reachable positions, we check if
					// the unit is able to go one cell further.
					tempList.addAll(possiblesMovesAroundASpecificPosition(listOfCell
							.get(j)));
					// Then, we remove duplicates. (It does not perfectly work
					// but I don't know why)
					for (int index = 0; index < tempList.size(); index++) {
						for (int index2 = index + 1; index2 < tempList.size(); index2++) {
							if (tempList.get(index2)
									.equals(tempList.get(index)))
								tempList.remove(index2);
						}
					}
				}
			}

		}

		listOfCell.clear();
		listOfCell.addAll(tempList);

		for (int index = 0; index < listOfCell.size(); index++) {
			for (int index2 = index + 1; index2 < listOfCell.size(); index2++) {
				if (listOfCell.get(index2).equals(listOfCell.get(index)))
					listOfCell.remove(index2);
			}
		}

		return listOfCell;
	}

	/**
	 * Gets all cells that a unit can attack.
	 * 
	 * @param unitPosition
	 * @return
	 */
	private List<Pos> possiblesAttackCellsAroundASpecificPosition(
			Pos unitPosition) {
		List<Pos> listOfPos = new LinkedList<Pos>();
		List<Pos> listOfPosInRange = new LinkedList<Pos>();
		int dX, dY, e, counter = 0;

		listOfPosInRange = cellInRangeOfAttack(unitPosition);
		for (int index = 0; index < listOfPosInRange.size(); index++) {
			for (int index2 = index + 1; index2 < listOfPosInRange.size(); index2++) {
				if (listOfPosInRange.get(index2).equals(
						listOfPosInRange.get(index)))
					listOfPosInRange.remove(index2);
			}
		}
		// The following algorithm is pretty hard to understand and to explain.
		// If you want understand it :
		// http://fr.wikipedia.org/wiki/Algorithme_de_trac%C3%A9_de_segment_de_Bresenham
		// I had to set it, because it wasn't able to work for every situation.
		while (counter < listOfPosInRange.size()) {
			int x1 = unitPosition.getX(), y1 = unitPosition.getY();
			int x2 = listOfPosInRange.get(counter).getX(), y2 = listOfPosInRange
					.get(counter).getY();
			// Searching for case according to the X coordinates if
			// |x1-x2|>|y1-y2|.
			if (Math.abs(x1 - x2) > Math.abs(y1 - y2)) {
				e = Math.abs(x1 - x2);
				dX = e * 2;
				dY = Math.abs(y1 - y2) * 2;
				// To avoid sign problems, we check if x2>=x1
				if (x2 >= x1) {
					while (x1 <= x2
							&& getCell(x1, y1).getSurface().canFireThrough()) {
						listOfPos.add(new Pos(x1, y1));
						x1++;
						e -= dY;
						if (e <= 0 && x1 <= this.height) {
							if (e == 0
									&& getCell(x2, y2).getSurface()
											.canFireThrough())
								listOfPos.add(new Pos(x1, y1));
							y1++;
							e += dX;
						}
						if(y1>this.width || x1>this.height)
							break;
					}
				} else {
					while (x2 <= x1
							&& getCell(x1, y1).getSurface().canFireThrough()) {
						listOfPos.add(new Pos(x1, y1));
						x1--;
						e -= dY;
						if (e <= 0 && x1>=0) {
							if (e == 0 && getCell(x1, y1).getSurface()
									.canFireThrough())
								listOfPos.add(new Pos(x1, y1));
							y1--;
							e += dX;
						}
						if(y1<0 || x1<0)
							break;
					}
				}
			}

			// Else, do according to the Y coordinate
			else {

				e = Math.abs(y1 - y2);
				dX = e * 2;
				dY = Math.abs(x1 - x2) * 2;
				// To avoid sign problems we check if y2>=y1
				if (y2 >= y1) {
					while (y1 <= y2
							&& getCell(x1, y1).getSurface().canFireThrough()) {
						listOfPos.add(new Pos(x1, y1));

						y1++;
						e -= dY;
						if (e <= 0 && y1<=this.width) {
							if (e == 0
									&& getCell(x2, y2).getSurface()
											.canFireThrough())
								listOfPos.add(new Pos(x1, y1));
							x1++;
							e += dX;
						}
						if(x1>this.height || y1>this.width)
							break;
					}
				} else {
					while (y2 <= y1
							&& getCell(x1, y1).getSurface().canFireThrough()) {
						listOfPos.add(new Pos(x1, y1));
						y1--;
						e -= dY;
						if (e <= 0 && y1>=0) {
							if (e == 0
									&& getCell(x1, y1).getSurface()
											.canFireThrough()) {
								listOfPos.add(new Pos(x1, y1));
							}
							x1--;
							e += dX;
							
						}
						if(x1<0 || y1<0)
							break;
					}
				}
			}
			counter++;

		}
		return listOfPos;
	}

	/**
	 * Moves a selected unit to another cell.
	 * 
	 * @param initialPosition
	 * @param endPosition
	 * @return True if the unit has been moved, false otherwise.
	 */
	public boolean moveAUnit(Pos initialPosition, Pos endPosition) {

		Unit selectedUnit = getCell(initialPosition).getUnit();

		int counter = 0;
		List<Pos> reachableCells = new LinkedList<Pos>();
		HashSet<Pos> setTemp = new HashSet<Pos>();
		// setTemp allows to avoid duplicates.
		setTemp.addAll(possiblesMoves(initialPosition));
		reachableCells.addAll(setTemp);

		if (selectedUnit != null && !initialPosition.equals(endPosition)
				&& getCell(endPosition).getUnit() == null) {
			// We must check if the final position is not the initial one, and
			// if the final position is empty.
			while (counter < reachableCells.size()
					&& !reachableCells.get(counter).equals(endPosition)) {
				// Then we check is the final position is in the list of every
				// reachable cells.
				counter++;
			}

			if (counter > reachableCells.size() - 1) {
				// endPosition isn't in reachableCells.
				counter = 0;
				reachableCells.clear();
				return false;
			} else {
				// endPosition is in reachableCells.
				getCell(initialPosition).clearUnit();
				getCell(endPosition).setUnitv2(selectedUnit, endPosition);
				counter = 0;
				reachableCells.clear();
				return true;
			}
		}

		return false;
	}

	/**
	 * Attack a unit, and clears the attacked unit if its life is under 0.
	 * 
	 * @param unit1Position
	 * @param unit2Position
	 * @return True if an unit has been attacked, false otherwise.
	 */
	public boolean attackAUnit(Pos unit1Position, Pos unit2Position) {

		List<Pos> cellsInRange = new LinkedList<Pos>();
		cellsInRange = possiblesAttackCellsAroundASpecificPosition(unit1Position);
		Unit selectedUnit1 = getCell(unit1Position).getUnit();
		Unit selectedUnit2 = getCell(unit2Position).getUnit();

		for (int index = 0; index < cellsInRange.size(); index++) {
			for (int index2 = index + 1; index2 < cellsInRange.size(); index2++) {
				if (cellsInRange.get(index2).equals(cellsInRange.get(index)))
					cellsInRange.remove(index2);
			}
		}

		if (getCell(unit2Position).getUnit() != null
				&& !unit1Position.equals(unit2Position)) {
			if (!cellsInRange.contains(unit2Position)) {
				return false;
			} else {
				selectedUnit2.setLife(selectedUnit2.getLife()
						- selectedUnit1.getAttackDamage());
				if (selectedUnit2.getLife() <= 0) {
					getCell(unit2Position).getUnit().getOwner().removeUnit();
					getCell(unit2Position).clearUnit();
				}
				return true;
			}

		}
		return false;
	}

	/**
	 * Create a unit.
	 * 
	 * @param pos
	 * @param unitType
	 * @param owner
	 * @return True if the unit is created, false otherwise
	 */
	public boolean createUnit(Pos pos, UnitType unitType, Player owner) {

		List<Pos> list = new LinkedList<Pos>();
		List<Pos> tempList = new LinkedList<Pos>();

		// Ugly way to check if the pos is around the commander.
		list.addAll(cellsAroundACell(owner.getCommander().getPosition()));
		tempList.add(pos);
		list.retainAll(tempList);

		if (unitType == UnitType.BOWMAN && owner.getGold() >= Bowman.BOWMAN_COST && getCell(pos).getUnit() == null && !list.isEmpty()) {
			this.getCell(pos).setUnit(new Bowman(owner, pos));
			owner.removeGold(Bowman.BOWMAN_COST);
			owner.addUnit();
			return true;

		} else if (unitType == UnitType.SOLDIER
				&& owner.getGold() >= Soldier.SOLDIER_COST
				&& getCell(pos).getUnit() == null && !list.isEmpty()) {
			this.getCell(pos).setUnit(new Soldier(owner, pos));
			owner.removeGold(Soldier.SOLDIER_COST);
			owner.addUnit();
			return true;

		} else if (unitType == UnitType.HORSEMAN
				&& owner.getGold() >= Horseman.HORSEMAN_COST
				&& getCell(pos).getUnit() == null && !list.isEmpty()) {
			this.getCell(pos).setUnit(new Horseman(owner, pos));
			owner.removeGold(Horseman.HORSEMAN_COST);
			owner.addUnit();
			return true;

		} else
			return false;
	}

	/**
	 * Upgrade a unit.
	 * 
	 * @param unit
	 * @return True if the unit is upgraded, false otherwise.
	 */
	public boolean upgradeUnit(Unit unit) {
		if (unit.getType() == UnitType.BOWMAN && unit.getOwner().getGold() >= Bowman.BOWMAN_COST * unit.getLevel() && unit.getLevel() <= 5) {
			//The bowman can be upgraded 5 times.
			unit.getOwner().removeGold(Bowman.BOWMAN_COST * unit.getLevel());
			unit.levelUpUnit();
			return true;

		}
		
		if (unit.getType() == UnitType.SOLDIER && unit.getOwner().getGold() >= Soldier.SOLDIER_COST * unit.getLevel() && unit.getLevel() <= 5) {
			//The soldier can be upgraded 5 times.
			unit.getOwner().removeGold(Soldier.SOLDIER_COST * unit.getLevel());
			unit.levelUpUnit();
			return true;

		}
		
		if (unit.getType() == UnitType.HORSEMAN && unit.getOwner().getGold() >= Horseman.HORSEMAN_COST * unit.getLevel() && unit.getLevel() <= 5) {
			//The horseman can be upgraded 5 times.
			unit.getOwner().removeGold(Horseman.HORSEMAN_COST * unit.getLevel());
			unit.levelUpUnit();
			return true;
		}
		
		if (unit.getType() == UnitType.COMMANDER && unit.getOwner().getGold() >= Commander.COMMANDER_COST * unit.getLevel() && unit.getLevel() <= 3) {
			//The commander can be upgraded 3times.
			unit.getOwner().removeGold(Commander.COMMANDER_COST * unit.getLevel());
			unit.levelUpUnit();
			return true;
		}

		return false;
	}

}
