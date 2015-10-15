package fr.iutvalence.groupe8.eldwars.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Not used KeyListener to press key on the GameWindow.
 * 
 * @author Nicolas
 * @version 20150610
 */
public class GameWindowKeyListener extends KeyAdapter {

	/**
	 * The keyPressed Event.
	 */
	public void keyPressed(KeyEvent evt) {
		if (evt.getKeyChar() == 'a') {
			System.out.println("Check for key characters: " + evt.getKeyChar());
		}
		if (evt.getKeyCode() == KeyEvent.VK_HOME) {
			System.out.println("Check for key codes: " + evt.getKeyCode());
		}
	}

}
