package fr.iutvalence.groupe8.eldwars;

import fr.iutvalence.groupe8.eldwars.model.map.Grid;

import fr.iutvalence.groupe8.eldwars.view.GameWindow;

/**
 * Round class.
 * 
 * @author Nicolas
 * @version 20150608
 */
public class Round {

	/**
	 * Round GameWindow.
	 */
	private GameWindow window;

	/**
	 * Round Grid.
	 */
	private Grid grid;

	/**
	 * The first player.
	 */
	private final Player p1;

	/**
	 * The second player.
	 */
	private final Player p2;

	/**
	 * The round number.
	 */
	private int roundNumber;

	/**
	 * The Round constructor.
	 * 
	 * @param window
	 *            - The GameWindow to use.
	 * @param grid
	 *            - The Grid to use.
	 */
	public Round(GameWindow window, Grid grid, Player p1, Player p2, int roundNumber) {
		this.window = window;
		this.grid = grid;
		this.p1 = p1;
		this.p2 = p2;
		this.roundNumber = roundNumber;
	}

	/**
	 * Starts the round.
	 */
	public void start() {
		if (this.window.isVisible()) {
			new Turn(this.window, this.grid, this.p1, roundNumber).start();
			if (this.p2.getUnitsNb() > 0) // Do not enter the other player's Turn if the first won.
				new Turn(this.window, this.grid, this.p2, roundNumber).start();
		}
	}

}
