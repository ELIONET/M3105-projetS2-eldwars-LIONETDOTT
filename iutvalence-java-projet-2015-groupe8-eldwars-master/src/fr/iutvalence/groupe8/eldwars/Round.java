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

	private GameWindow window;

	private Grid grid;

	private final Player player1;

	private final Player player2;

	private int roundNumber;

	public Round(GameWindow window, Grid grid, Player p1, Player p2, int roundNumber) {
		this.window = window;
		this.grid = grid;
		this.player1 = p1;
		this.player2 = p2;
		this.roundNumber = roundNumber;
	}

	public void startRound() {
		if (this.window.isVisible()) {
			new Turn(this.window, this.grid, this.player1, roundNumber).start();
			if (this.player2.getUnitsNb() > 0) // Do not enter the other player's Turn if the first won.
				new Turn(this.window, this.grid, this.player2, roundNumber).start();
		}
	}

}
