package fr.iutvalence.groupe8.eldwars.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;

/**
 * The SpriteMouseAdapter class is used to listen mouse clicks.
 * 
 * @author Nicolas
 * @version 20150526
 *
 */
public class SpriteMouseAdapter extends MouseAdapter {
	
	/**
	 * This event will be performed when clicking on a Sprite.
	 */
	public void mousePressed(MouseEvent e) {
		Sprite sprite = (Sprite) e.getSource();
		
		UnitsDisplay grid = (UnitsDisplay) sprite.getParent();
		GameWindow gw = (GameWindow) grid.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
		
		
		try {
			Pos pos = grid.searchSprite(sprite);
			gw.setLastGivenPos(pos);
			SoundEngine.play(SoundType.CLICK);
		} catch (SpriteNotFoundException e1) {
			System.err
					.println("SUCH BIG ERROR! WOW SHOULD FIX THIS DOGY ISSUE!");
		}
	}
}
