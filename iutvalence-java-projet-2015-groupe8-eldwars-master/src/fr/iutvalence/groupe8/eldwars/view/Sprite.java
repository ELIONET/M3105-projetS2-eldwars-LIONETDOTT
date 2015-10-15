package fr.iutvalence.groupe8.eldwars.view;

import javax.swing.JLabel;

import fr.iutvalence.groupe8.eldwars.model.map.Surface;

/**
 * Sprites are used to know the type of Surface.
 * 
 * @author Nicolas
 * @version 20150525
 */
public class Sprite extends JLabel {

	/**
	 * The Sprite's Surface
	 */
	private Surface surface;

	/**
	 * The Sprite constructor.
	 * 
	 * @param grass
	 */
	public Sprite(Surface surface) {
		this.surface = surface;
		addMouseListener(new SpriteMouseAdapter());
	}

	/**
	 * Get the Sprite's Surface.
	 * 
	 * @return A Surface
	 */
	public Surface getSurface() {
		return surface;
	}

	/**
	 * Set this Sprite's Surface
	 * 
	 * @param surface
	 */
	public void setSurface(Surface surface) {
		this.surface = surface;
	}
}
