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

	/**
	 * The first player.
	 */
	private Player p1;

	/**
	 * The second Player.
	 */
	private Player p2;

	/**
	 * The Map.
	 */
	private Map map;

	/**
	 * The main GameWindow.
	 */
	private GameWindow mainWindow;

	/**
	 * The Game constructor (used for a new game).
	 * 
	 * @param p1
	 * @param p2
	 */
	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;

		// Generates the Map.
		this.map = new Map();
		GridGenerator mg = new GridGenerator();
		this.map.setGrid(mg.nextGrid());

		// Places the Players' Commanders on the Map's extremities.
		p1.getCommander().setPosition(new Pos(0, 0));
		this.map.getGrid().getCell(0, 0).setUnit(p1.getCommander());
		
		
		p2.getCommander().setPosition(new Pos(1, 0));
		this.map.getGrid().getCell(1, 0).setUnit(p2.getCommander());
		

//		p2.getCommander().setPosition(new Pos(Map.DEFAULT_MAP_WIDTH - 1, Map.DEFAULT_MAP_HEIGHT - 1));
//		this.map.getGrid().getCell(Map.DEFAULT_MAP_WIDTH - 1, Map.DEFAULT_MAP_HEIGHT - 1).setUnit(p2.getCommander());
	}

	/**
	 * The Game constructor (used for a loaded game).
	 * 
	 * @param p1
	 * @param p2
	 */
	public Game(Player p1, Player p2, Map map) {
		this.p1 = p1;
		this.p2 = p2;
		this.map = map;

		// TODO The steps of loading an existing game.
	}

	/**
	 * Starts the Game.
	 */
	public void start() {
		int counter = 0;
		// Creates the GameWindow.
		this.mainWindow = new GameWindow(this.map.getMapWidth(), this.map.getMapHeight());

		for (int line = 0; line < this.map.getMapWidth(); line++) {
			for (int column = 0; column < this.map.getMapHeight(); column++) {
				this.mainWindow.getGroundGridDisplay().updateSprite(line, column, this.map.getGrid().getCell(line, column).getSurface());
			}
		}

		this.mainWindow.getUnitsGridDisplay().updateSprite(this.p1.getCommander().getPosition(), this.p1.getCommander().getSurface());
		this.mainWindow.getUnitsGridDisplay().updateSprite(this.p2.getCommander().getPosition(), this.p2.getCommander().getSurface());

		this.mainWindow.setVisible(true); // Shows the window.

		SoundEngine.play(SoundType.START);
		
		while (this.mainWindow.isVisible()) { // Ends the game when closing the GameWindow.
			counter++;
			new Round(this.mainWindow, this.map.getGrid(), this.p1, this.p2, counter).start();

			if (this.p1.getUnitsNb() == 0) {
				this.mainWindow.setEnabled(false);
				new VictoryWindow(this.p2.getNickname()).showAndWaitForResult();
				this.mainWindow.setEnabled(true);
				break;
			}
			
			if (this.p2.getUnitsNb() == 0) {
				this.mainWindow.setEnabled(false);
				new VictoryWindow(this.p1.getNickname()).showAndWaitForResult();
				this.mainWindow.setEnabled(true);
				break;
			}
		}
		
		this.mainWindow.setVisible(false);

	}

}
