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

	private final Cell[][] grid;

	private final int gridWidth;

	private final int gridHeight;

	public Grid(int width, int height) {

		this.gridWidth = width;

		this.gridHeight = height;

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

	public String getStringRepresentationOfTheGrid() {
		StringBuilder sb = new StringBuilder();
		for (Cell[] cells : grid) {
			for (Cell cell : cells) {
				sb.append(cell.getCellSurface());
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public Cell getCellAtSpecificsCoords(int x, int y) {
		return grid[x][y];
	}

	public Cell getCellAtASpecificPos(Pos pos) {
		return grid[pos.getXCoord()][pos.getYCoord()];
	}

	private Set<Pos> possiblesMovesAroundASpecificPosition(Pos pos) {
		HashSet<Pos> listOfCellAroundASpecificPosition = new HashSet<Pos>();
		int x = pos.getXCoord(), y = pos.getYCoord();

		// We must check if positions around the first one aren't out of the
		// grid and are navigable.
		if (x + 1 < this.gridWidth && getCellAtSpecificsCoords(x + 1, y).getCellSurface().canWalkOn()
				&& !listOfCellAroundASpecificPosition.contains(pos.newPos(1, 0))) {
			listOfCellAroundASpecificPosition.add(pos.newPos(1, 0));
		}

		if (x - 1 >= 0
				&& getCellAtSpecificsCoords(x - 1, y).getCellSurface().canWalkOn()
				&& !listOfCellAroundASpecificPosition
						.contains(pos.newPos(-1, 0))) {
			listOfCellAroundASpecificPosition.add(pos.newPos(-1, 0));
		}

		if (y + 1 < this.gridHeight && getCellAtSpecificsCoords(x, y + 1).getCellSurface().canWalkOn()
				&& !listOfCellAroundASpecificPosition.contains(pos.newPos(0, 1))) {
			listOfCellAroundASpecificPosition.add(pos.newPos(0, 1));
		}

		if (y - 1 >= 0
				&& getCellAtSpecificsCoords(x, y - 1).getCellSurface().canWalkOn()
				&& !listOfCellAroundASpecificPosition
						.contains(pos.newPos(0, -1))) {
			listOfCellAroundASpecificPosition.add(pos.newPos(0, -1));
		}

		return listOfCellAroundASpecificPosition;
	}
	
	private List<Pos> cellsAroundACell(Pos unitPosition) {
		List<Pos> list = new LinkedList<Pos>();
		if (unitPosition.getXCoord() + 1 <= this.gridWidth && getCellAtASpecificPos(unitPosition.newPos(1, 0)).getCellSurface().canWalkOn()) {
			list.add(unitPosition.newPos(1, 0));
		}
		
		if (unitPosition.getXCoord() - 1 >= 0 && getCellAtASpecificPos(unitPosition.newPos(-1, 0)).getCellSurface().canWalkOn()) {
			list.add(unitPosition.newPos(-1, 0));
		}
		
		if (unitPosition.getYCoord() + 1 <= this.gridHeight && getCellAtASpecificPos(unitPosition.newPos(0, 1)).getCellSurface().canWalkOn()) {
			list.add(unitPosition.newPos(0, 1));
		}
		
		if (unitPosition.getYCoord() - 1 >= 0 && getCellAtASpecificPos(unitPosition.newPos(0, -1)).getCellSurface().canWalkOn()) {
			list.add(unitPosition.newPos(0, -1));
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
		Unit unit = getCellAtASpecificPos(unitPosition).getUnitOnThisCell();
		List<Pos> listOfCellInRangeOfAttack = new LinkedList<Pos>();
		List<Pos> tempList = new LinkedList<Pos>();
		int unitRange = unit.getRange();
		tempList.add(unitPosition);

		while (unitRange > 0) {
			// For every already reachable positions (in term of range), we add
			// every which are one cell further.
			for (int i = 0; i < tempList.size(); i++) {
				if (tempList.get(i).getXCoord() + 1 < this.gridWidth)
					listOfCellInRangeOfAttack.add(tempList.get(i).newPos(1, 0));
				if (tempList.get(i).getXCoord() - 1 >= 0)
					listOfCellInRangeOfAttack.add(tempList.get(i).newPos(-1, 0));
				if (tempList.get(i).getYCoord() + 1 < this.gridHeight)
					listOfCellInRangeOfAttack.add(tempList.get(i).newPos(0, 1));
				if (tempList.get(i).getYCoord() - 1 >= 0)
					listOfCellInRangeOfAttack.add(tempList.get(i).newPos(0, -1));

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

		Unit unit = getCellAtASpecificPos(unitPosition).getUnitOnThisCell();
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

	private List<Pos> possiblesAttackCellsAroundASpecificPosition(
			Pos unitPosition) {
		List<Pos> listOfPos = new LinkedList<Pos>();
		List<Pos> listOfPosInRange = new LinkedList<Pos>();
		int coordXErrorValue, coordYErrorValue, errorValue, counter = 0;

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
			int unitXCoord = unitPosition.getXCoord(), unitYCoord = unitPosition.getYCoord();
			int cellXCoord = listOfPosInRange.get(counter).getXCoord(), cellYCoord = listOfPosInRange
					.get(counter).getYCoord();
			// Searching for case according to the X coordinates if
			// |unitXCoord-cellXCoord|>|unitYCorod-cellY|.
			if (Math.abs(unitXCoord - cellXCoord) > Math.abs(unitYCoord - cellYCoord)) {
				errorValue = Math.abs(unitXCoord - cellXCoord);
				coordXErrorValue = errorValue * 2;
				coordYErrorValue = Math.abs(unitYCoord - cellYCoord) * 2;
				// To avoid sign problems, we check if x2>=x1
				if (cellXCoord >= unitXCoord) {
					while (unitXCoord <= cellXCoord
							&& getCellAtSpecificsCoords(unitXCoord, unitYCoord).getCellSurface().canFireThrough()) {
						listOfPos.add(new Pos(unitXCoord, unitYCoord));
						unitXCoord++;
						errorValue -= coordYErrorValue;
						if (errorValue <= 0 && unitXCoord <= this.gridHeight) {
							if (errorValue == 0
									&& getCellAtSpecificsCoords(cellXCoord, cellYCoord).getCellSurface()
											.canFireThrough())
								listOfPos.add(new Pos(unitXCoord, unitYCoord));
							unitYCoord++;
							errorValue += coordXErrorValue;
						}
						if(unitYCoord>this.gridWidth || unitXCoord>this.gridHeight)
							break;
					}
				} else {
					while (cellXCoord <= unitXCoord
							&& getCellAtSpecificsCoords(unitXCoord, unitYCoord).getCellSurface().canFireThrough()) {
						listOfPos.add(new Pos(unitXCoord, unitYCoord));
						unitXCoord--;
						errorValue -= coordYErrorValue;
						if (errorValue <= 0 && unitXCoord>=0) {
							if (errorValue == 0 && getCellAtSpecificsCoords(unitXCoord, unitYCoord).getCellSurface()
									.canFireThrough())
								listOfPos.add(new Pos(unitXCoord, unitYCoord));
							unitYCoord--;
							errorValue += coordXErrorValue;
						}
						if(unitYCoord<0 || unitXCoord<0)
							break;
					}
				}
			}

			// Else, do according to the Y coordinate
			else {

				errorValue = Math.abs(unitYCoord - cellYCoord);
				coordXErrorValue = errorValue * 2;
				coordYErrorValue = Math.abs(unitXCoord - cellXCoord) * 2;
				// To avoid sign problems we check if y2>=y1
				if (cellYCoord >= unitYCoord) {
					while (unitYCoord <= cellYCoord
							&& getCellAtSpecificsCoords(unitXCoord, unitYCoord).getCellSurface().canFireThrough()) {
						listOfPos.add(new Pos(unitXCoord, unitYCoord));

						unitYCoord++;
						errorValue -= coordYErrorValue;
						if (errorValue <= 0 && unitYCoord<=this.gridWidth) {
							if (errorValue == 0
									&& getCellAtSpecificsCoords(cellXCoord, cellYCoord).getCellSurface()
											.canFireThrough())
								listOfPos.add(new Pos(unitXCoord, unitYCoord));
							unitXCoord++;
							errorValue += coordXErrorValue;
						}
						if(unitXCoord>this.gridHeight || unitYCoord>this.gridWidth)
							break;
					}
				} else {
					while (cellYCoord <= unitYCoord
							&& getCellAtSpecificsCoords(unitXCoord, unitYCoord).getCellSurface().canFireThrough()) {
						listOfPos.add(new Pos(unitXCoord, unitYCoord));
						unitYCoord--;
						errorValue -= coordYErrorValue;
						if (errorValue <= 0 && unitYCoord>=0) {
							if (errorValue == 0
									&& getCellAtSpecificsCoords(unitXCoord, unitYCoord).getCellSurface()
											.canFireThrough()) {
								listOfPos.add(new Pos(unitXCoord, unitYCoord));
							}
							unitXCoord--;
							errorValue += coordXErrorValue;
							
						}
						if(unitXCoord<0 || unitYCoord<0)
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

		Unit selectedUnit = getCellAtASpecificPos(initialPosition).getUnitOnThisCell();

		int counter = 0;
		List<Pos> reachableCells = new LinkedList<Pos>();
		HashSet<Pos> setTemp = new HashSet<Pos>();
		// setTemp allows to avoid duplicates.
		setTemp.addAll(possiblesMoves(initialPosition));
		reachableCells.addAll(setTemp);

		if (selectedUnit != null && !initialPosition.equals(endPosition)
				&& getCellAtASpecificPos(endPosition).getUnitOnThisCell() == null) {
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
				getCellAtASpecificPos(initialPosition).removeUnitFromThisCell();
				getCellAtASpecificPos(endPosition).setUnitOnASpecificCell(selectedUnit, endPosition);
				counter = 0;
				reachableCells.clear();
				return true;
			}
		}

		return false;
	}

	public boolean attackAUnit(Pos unit1Position, Pos unit2Position) {

		List<Pos> cellsInRange = new LinkedList<Pos>();
		cellsInRange = possiblesAttackCellsAroundASpecificPosition(unit1Position);
		Unit selectedUnit1 = getCellAtASpecificPos(unit1Position).getUnitOnThisCell();
		Unit selectedUnit2 = getCellAtASpecificPos(unit2Position).getUnitOnThisCell();

		for (int index = 0; index < cellsInRange.size(); index++) {
			for (int index2 = index + 1; index2 < cellsInRange.size(); index2++) {
				if (cellsInRange.get(index2).equals(cellsInRange.get(index)))
					cellsInRange.remove(index2);
			}
		}

		if (getCellAtASpecificPos(unit2Position).getUnitOnThisCell() != null
				&& !unit1Position.equals(unit2Position)) {
			if (!cellsInRange.contains(unit2Position)) {
				return false;
			} else {
				selectedUnit2.setLife(selectedUnit2.getLife()
						- selectedUnit1.getAttackDamage());
				if (selectedUnit2.getLife() <= 0) {
					getCellAtASpecificPos(unit2Position).getUnitOnThisCell().getOwner().removeOneUnitToPlayerCounter();
					getCellAtASpecificPos(unit2Position).removeUnitFromThisCell();
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

		if (unitType == UnitType.BOWMAN && owner.getPlayerGold() >= Bowman.BOWMAN_COST && getCellAtASpecificPos(pos).getUnitOnThisCell() == null && !list.isEmpty()) {
			this.getCellAtASpecificPos(pos).setUnitOnThisCell(new Bowman(owner, pos));
			owner.removeGoldToThePlayer(Bowman.BOWMAN_COST);
			owner.addOneUnitToPlayerCounter();
			return true;

		} else if (unitType == UnitType.SOLDIER
				&& owner.getPlayerGold() >= Soldier.SOLDIER_COST
				&& getCellAtASpecificPos(pos).getUnitOnThisCell() == null && !list.isEmpty()) {
			this.getCellAtASpecificPos(pos).setUnitOnThisCell(new Soldier(owner, pos));
			owner.removeGoldToThePlayer(Soldier.SOLDIER_COST);
			owner.addOneUnitToPlayerCounter();
			return true;

		} else if (unitType == UnitType.HORSEMAN
				&& owner.getPlayerGold() >= Horseman.HORSEMAN_COST
				&& getCellAtASpecificPos(pos).getUnitOnThisCell() == null && !list.isEmpty()) {
			this.getCellAtASpecificPos(pos).setUnitOnThisCell(new Horseman(owner, pos));
			owner.removeGoldToThePlayer(Horseman.HORSEMAN_COST);
			owner.addOneUnitToPlayerCounter();
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
		if (unit.getType() == UnitType.BOWMAN && unit.getOwner().getPlayerGold() >= Bowman.BOWMAN_COST * unit.getLevel() && unit.getLevel() <= 5) {
			//The bowman can be upgraded 5 times.
			unit.getOwner().removeGoldToThePlayer(Bowman.BOWMAN_COST * unit.getLevel());
			unit.levelUpUnit();
			return true;

		}
		
		if (unit.getType() == UnitType.SOLDIER && unit.getOwner().getPlayerGold() >= Soldier.SOLDIER_COST * unit.getLevel() && unit.getLevel() <= 5) {
			//The soldier can be upgraded 5 times.
			unit.getOwner().removeGoldToThePlayer(Soldier.SOLDIER_COST * unit.getLevel());
			unit.levelUpUnit();
			return true;

		}
		
		if (unit.getType() == UnitType.HORSEMAN && unit.getOwner().getPlayerGold() >= Horseman.HORSEMAN_COST * unit.getLevel() && unit.getLevel() <= 5) {
			//The horseman can be upgraded 5 times.
			unit.getOwner().removeGoldToThePlayer(Horseman.HORSEMAN_COST * unit.getLevel());
			unit.levelUpUnit();
			return true;
		}
		
		if (unit.getType() == UnitType.COMMANDER && unit.getOwner().getPlayerGold() >= Commander.COMMANDER_COST * unit.getLevel() && unit.getLevel() <= 3) {
			//The commander can be upgraded 3times.
			unit.getOwner().removeGoldToThePlayer(Commander.COMMANDER_COST * unit.getLevel());
			unit.levelUpUnit();
			return true;
		}

		return false;
	}

}
