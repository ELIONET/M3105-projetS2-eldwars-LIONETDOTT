package fr.iutvalence.groupe8.eldwars;

import java.util.Collection;
import java.util.LinkedList;

import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;
import fr.iutvalence.groupe8.eldwars.model.map.Grid;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;
import fr.iutvalence.groupe8.eldwars.model.units.Unit;
import fr.iutvalence.groupe8.eldwars.model.units.UnitType;
import fr.iutvalence.groupe8.eldwars.view.GameWindow;
import fr.iutvalence.groupe8.eldwars.view.UnitSelectionWindow;
import fr.iutvalence.groupe8.eldwars.view.UpgradeUnitWindow;
import fr.iutvalence.groupe8.eldwars.view.actions.ActionType;
import fr.iutvalence.groupe8.eldwars.view.actions.ActionsArea;

/**
 * Turn class
 * @author Nicolas,Clément
 *
 */
public class Turn {

	private final GameWindow window;

	private final Grid grid;

	private final Player player;

	private int roundNumber;

	private LinkedList<Unit> unitsThatAttacked;

	private LinkedList<Unit> unitsThatMoved;

	public Turn(GameWindow window, Grid grid, Player player, int roundNumber) {
		this.window = window;
		this.grid = grid;
		this.player = player;
		this.roundNumber=roundNumber;
	}

	public void start() {
		Pos pos = null;
		Pos newPos = null;
		ActionType act = null;
		Unit selectedUnit;
		
		this.unitsThatAttacked = new LinkedList<Unit>();
		this.unitsThatMoved = new LinkedList<Unit>(); 
		this.player.addGoldToThePlayer(10+this.roundNumber);

		this.window.getActionsArea().getPlayerStats().setNickname(this.player.getNickname());

		do {
			this.window.getActionsArea().getPlayerStats().setGold(this.player.getPlayerGold());

			// Main loop, waiting that you have selected a pos and an action or you
			// select the next round button.
			do {

				newPos = window.getLastGivenPos();

				// The selected position is valid.
				if (newPos != null) {
					pos = newPos;
					newPos = null;
					this.window.getSelectionDisplay().unselectAll();
					this.window.getSelectionDisplay().select(pos);
				}

				// When you already selected a position or when you have just done it.
				if (pos != null) {

					if (grid.getCellAtASpecificPos(pos).getUnitOnThisCell() == null) {
						// Cancels the Pos if there's no Unit on it.
						pos = null;
						this.window.getActionsArea().getUnitStats().reset();
					} else {
						selectedUnit = this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell(); // Gets the Unit at the specified Pos.

						// Updates the UnitStats.
						this.window.getActionsArea().getUnitStats().setType(selectedUnit.getType());
						this.window.getActionsArea().getUnitStats().setLevel(selectedUnit.getLevel());
						this.window.getActionsArea().getUnitStats().setLife(selectedUnit.getLife() + " / " + selectedUnit.getMaxLife());
						this.window.getActionsArea().getUnitStats().setRange(selectedUnit.getRange());
						this.window.getActionsArea().getUnitStats().setMvt(selectedUnit.getMovementPoints());
						this.window.getActionsArea().getUnitStats().setAttack(selectedUnit.getAttackDamage());

						if (!grid.getCellAtASpecificPos(pos).getUnitOnThisCell().getOwner().equals(this.player))
							pos = null; // Cancels the Pos if the owner is not the current Player.
					}

				}

				// If the Pos stays valid.
				if (pos != null) {
					
					// Activates the actions according to the selected Unit's type.
					if (this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell().getType() == UnitType.COMMANDER)
						this.window.getActionsArea().unitSelected(ActionsArea.SELECTION_COMMANDER);
					else
						this.window.getActionsArea().unitSelected(ActionsArea.SELECTION_BASIC);
					
					act = null;
				} else {
					// Otherwise.
					this.window.getActionsArea().unitSelected(ActionsArea.SELECTION_NONE); // Deactivates the actions.
				}

				act = this.window.getLastUsedAction(); // Gets the last clicked Action.
				
				if (!this.window.isVisible()) // Stops the loop if the GameWindow is not displayed.
					break;
				
			} while ((act == null || pos == null) && act != ActionType.NEXT_ROUND);
			
			if (!this.window.isVisible()) // Stops the loop if the GameWindow is not displayed.
				break;
			
			this.window.getActionsArea().unitSelected(ActionsArea.SELECTION_NONE); // Deactivates the actions.

		} while(!handleAction(pos, act) || act != ActionType.NEXT_ROUND); // Do what the player wants to be done, exits the loop when the Player wants to stop his round.

	}

	private boolean handleAction(Pos pos, ActionType act) {
		
		boolean toReturn;
		
		if (act == ActionType.MOVE) { // MOVE CASE
			
			
			// The Player wants to move a Unit.
			if (!this.unitsThatMoved.contains(this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell()) && !this.unitsThatAttacked.contains(this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell())) {
				// You can't move a Unit which already moved or attacked.
				
				Pos destPos = null;

					// Asks a Pos from the Player.
					do {
						destPos = this.window.getLastGivenPos();
					} while (destPos == null);

					// Selects the last given Pos.
					this.window.getSelectionDisplay().select(destPos);

				if (this.grid.moveAUnit(pos, destPos)) {
				
					// Adds the Unit to the collection.
					this.unitsThatMoved.add(this.grid.getCellAtASpecificPos(destPos).getUnitOnThisCell());
					
					// Clears the old Cell.
					this.window.getUnitsGridDisplay().updateSprite(pos, Surface.EMPTY);
					
					// Updates the destination Cell.
					this.window.getUnitsGridDisplay().updateSprite(destPos, this.grid.getCellAtASpecificPos(destPos).getUnitOnThisCell().getSurface());
					
					if (this.grid.getCellAtASpecificPos(destPos).getCellSurface() == Surface.GRASS)
						SoundEngine.play(SoundType.GRASS);
					else if (this.grid.getCellAtASpecificPos(destPos).getCellSurface() == Surface.SAND)
						SoundEngine.play(SoundType.SAND);
					
				
					toReturn = true;
					
				} else {
					toReturn = false;
					SoundEngine.play(SoundType.ERROR2);
				}
				
			} else {
				toReturn = false;
				SoundEngine.play(SoundType.ERROR2);
			}

		} else if (act == ActionType.ATTACK) { // ATTACK CASE
			
			// The Player wants to attack.
			if (!this.unitsThatAttacked.contains(this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell())) {
				// You can't move a Unit which already attacked.
				
				Pos targetPos;
				
				// Ask a position.
				targetPos = this.window.waitForAPos();

				if (this.grid.attackAUnit(pos, targetPos)) {
					// The attack succeed.
					
					if (this.grid.getCellAtASpecificPos(targetPos).getUnitOnThisCell() == null) {
						
						// The Unit is now dead.
						this.window.getUnitsGridDisplay().updateSprite(targetPos, Surface.EMPTY);
						
					} else {
						
						// The Unit stills alive
						this.window.getUnitsGridDisplay().updateSprite(targetPos, this.grid.getCellAtASpecificPos(targetPos).getUnitOnThisCell().getSurface());
						
					}
					
					this.unitsThatAttacked.add(this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell());
					
					if (this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell().getType() == UnitType.BOWMAN)
						SoundEngine.play(SoundType.BOW);
					else
						SoundEngine.play(SoundType.SWORD);
					
					toReturn = true;
					
				} else {
					// It failed.
					toReturn = false;
					SoundEngine.play(SoundType.ERROR2);
				}
				
			} else {
				toReturn = false;
				SoundEngine.play(SoundType.ERROR2);
			}
		
		} else if (act == ActionType.RECRUIT && this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell().getType() == UnitType.COMMANDER) { // RECRUIT CASE
			
			Pos posToAddUnit = null;
			this.window.setEnabled(false);
			UnitType unitToAddType = new UnitSelectionWindow().showAndWaitForResult();
			this.window.setAlwaysOnTop(true);
			this.window.setAlwaysOnTop(false);
			this.window.setEnabled(true);
			
			if (unitToAddType != null) {
				
				posToAddUnit = this.window.waitForAPos();
				
				toReturn = this.grid.createUnit(posToAddUnit, unitToAddType, this.player);
				
				// Updates the display on the window.
				if (toReturn && unitToAddType == UnitType.BOWMAN)
					this.window.getUnitsGridDisplay().updateSprite(posToAddUnit, Surface.UNIT_BOWMAN);
				
				else if (toReturn &&unitToAddType == UnitType.HORSEMAN)
					this.window.getUnitsGridDisplay().updateSprite(posToAddUnit, Surface.UNIT_HORSEMAN);
				
				else if (toReturn && unitToAddType == UnitType.SOLDIER)
					this.window.getUnitsGridDisplay().updateSprite(posToAddUnit, Surface.UNIT_SOLDIER);
				
				if (toReturn)
					SoundEngine.play(SoundType.SUCCESS);
				else
					SoundEngine.play(SoundType.ERROR2);
				
			} else {
				toReturn = false;
				SoundEngine.play(SoundType.ERROR2);
			}
			
		} else if (act == ActionType.UPGRADE) { // UPGRADE CASE
			
			Unit selectedUnit = this.grid.getCellAtASpecificPos(pos).getUnitOnThisCell();
			
			this.window.setEnabled(false);
			if (new UpgradeUnitWindow(selectedUnit.getType(), selectedUnit.getLevel(), selectedUnit.getMaxLife(), selectedUnit.getMovementPoints(), selectedUnit.getAttackDamage(), selectedUnit.getRange(), selectedUnit.getCost()).showAndWaitForResult()) {
				this.window.setEnabled(true);
				this.window.setAlwaysOnTop(true);
				this.window.setAlwaysOnTop(false);
				toReturn = this.grid.upgradeUnit(selectedUnit);
				
				if (toReturn)
					SoundEngine.play(SoundType.SUCCESS);
				else
					SoundEngine.play(SoundType.ERROR2);
				
			} else {
				this.window.setEnabled(true);
				this.window.setAlwaysOnTop(true);
				this.window.setAlwaysOnTop(false);
				toReturn = false;
			}
			
		} else if (act == ActionType.NEXT_ROUND) { // NEXT ROUND CASE
			
			toReturn = true;
			
		} else {
			
			toReturn = false;
			
		}

		this.window.getSelectionDisplay().unselectAll();
		
		return toReturn; // If it returns false, the action failed.

	}
	
	public static <T> boolean containsReferenceTo(Collection<T> collection,
	        T element) {
	    if (collection == null)
	        throw new NullPointerException("collection cannot be null");

	    for (T x : collection) {
	        if (x == element) {
	            return true;
	        }
	    }
	    return false;
	}

}
