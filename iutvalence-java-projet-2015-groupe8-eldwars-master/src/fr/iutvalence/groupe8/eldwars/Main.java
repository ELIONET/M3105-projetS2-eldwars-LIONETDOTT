package fr.iutvalence.groupe8.eldwars;

import fr.iutvalence.groupe8.eldwars.view.MainMenuWindow;

/**
 * Main class.
 * 
 * @author Nicolas
 * @version 20150521
 *
 */
public class Main {

	/**
	 * Main method, launched first.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		new MainMenuWindow().showAndWaitForResult(); // Shows the main menu.
		System.exit(0);
	}
	
}
