package fr.iutvalence.groupe8.eldwars;

import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;
import fr.iutvalence.groupe8.eldwars.model.map.Map;
import fr.iutvalence.groupe8.eldwars.model.map.GridGenerator;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;
import fr.iutvalence.groupe8.eldwars.view.GameWindow;
import fr.iutvalence.groupe8.eldwars.view.GroundDisplay;
import fr.iutvalence.groupe8.eldwars.view.VictoryWindow;

/**
 * Game class.
 * 
 * @author Nicolas
 * @version 20150606
 *
 */
public class Game {


	private Player player1;

	private Player player2;

	private Map map;

	private GameWindow mainWindow;

	
	public Game(Player p1, Player p2) {
		this.player1 = p1;
		this.player2 = p2;

		// Generates the Map.
		this.map = new Map();
		GridGenerator mg = new GridGenerator();
		this.map.setGrid(mg.nextGrid());

		// Places the Players' Commanders on the Map's extremities.
		p1.getCommander().setPosition(new Pos(0, 0));
		this.map.getGrid().getCellAtSpecificsCoords(0, 0).setUnitOnThisCell(p1.getCommander());
		
		
		p2.getCommander().setPosition(new Pos(1, 0));
		this.map.getGrid().getCellAtSpecificsCoords(1, 0).setUnitOnThisCell(p2.getCommander());
		

//		p2.getCommander().setPosition(new Pos(Map.DEFAULT_MAP_WIDTH - 1, Map.DEFAULT_MAP_HEIGHT - 1));
//		this.map.getGrid().getCell(Map.DEFAULT_MAP_WIDTH - 1, Map.DEFAULT_MAP_HEIGHT - 1).setUnit(p2.getCommander());
	}
	
	public void startGame() {
		int counter = 0;
		// Creates the GameWindow.
		this.mainWindow = new GameWindow(this.map.getMapWidth(), this.map.getMapHeight());

		for (int line = 0; line < this.map.getMapWidth(); line++) {
			for (int column = 0; column < this.map.getMapHeight(); column++) {
				this.mainWindow.getGroundGridDisplay().updateSprite(line, column, this.map.getGrid().getCellAtSpecificsCoords(line, column).getCellSurface());
			}
		}

		this.mainWindow.getUnitsGridDisplay().updateSprite(this.player1.getCommander().getPosition(), this.player1.getCommander().getSurface());
		this.mainWindow.getUnitsGridDisplay().updateSprite(this.player2.getCommander().getPosition(), this.player2.getCommander().getSurface());

		this.mainWindow.setVisible(true); // Shows the window.

		SoundEngine.play(SoundType.START);
		
		while (this.mainWindow.isVisible()) { // Ends the game when closing the GameWindow.
			counter++;
			new Round(this.mainWindow, this.map.getGrid(), this.player1, this.player2, counter).startRound();

			if (this.player1.getUnitsNb() == 0) {
				this.mainWindow.setEnabled(false);
				new VictoryWindow(this.player2.getNickname()).showAndWaitForResult();
				this.mainWindow.setEnabled(true);
				break;
			}
			
			if (this.player2.getUnitsNb() == 0) {
				this.mainWindow.setEnabled(false);
				new VictoryWindow(this.player1.getNickname()).showAndWaitForResult();
				this.mainWindow.setEnabled(true);
				break;
			}
		}
		
		this.mainWindow.setVisible(false);

	}

}
