package fr.iutvalence.groupe8.eldwars.view;

import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mortennobel.imagescaling.experimental.ImprovedMultistepRescaleOp;

import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.map.Surface;
import fr.iutvalence.groupe8.eldwars.model.units.UnitType;

/**
 * The UnitsDisplay class, used to have all images in a grid.
 * 
 * @author Nicolas
 * @version 20150606
 */
public class UnitsDisplay extends JPanel
{

	/**
	 * The default textures size.
	 */
	public static final int TEXTURE_SIZE = 32;

	/**
	 * The JLabel grid where are the surfaces textures.
	 */
	private final Sprite[][] sprites;

	/**
	 * The Grid's width.
	 */
	private final int gridWidth;

	/**
	 * The Grid's height.
	 */
	private final int gridHeight;

	/**
	 * The current textures size.
	 */
	private int texturesSize;

	/**
	 * The UnitsDisplay constructor. Generates the JLabel (with ImagesIcon)
	 * array.
	 * 
	 * @param gridWidth
	 *            - The grid width.
	 * @param gridHeight
	 *            - The grid height.
	 */
	public UnitsDisplay(int gridWidth, int gridHeight)
	{

		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.texturesSize = TEXTURE_SIZE;

		setLayout(new GridLayout(gridWidth, gridHeight));

		this.sprites = new Sprite[gridWidth][gridHeight];

		for (int line = 0; line < gridWidth; line++)
		{
			for (int column = 0; column < gridHeight; column++)
			{
				// Creates the JLabel.
				Sprite tmpSprite = new Sprite(Surface.EMPTY);

				// Sets the JLabel size.
				tmpSprite.setSize(this.texturesSize, this.texturesSize);

				this.sprites[line][column] = tmpSprite;
				add(tmpSprite);
			}
		}
	}

	/**
	 * Update the surface of a Sprite, so the image too.
	 * 
	 * @param x
	 *            - The position's X coordinate.
	 * @param y
	 *            - The position's Y coordinate.
	 * @param surface
	 *            - The new surface.
	 */
	public void updateSprite(int x, int y, Surface surface)
	{
		// Sets the new surface.
		this.sprites[x][y].setSurface(surface);
		updateDisplay(x, y);
	}

	/**
	 * Update the surface of a Sprite, and refreshes the display.
	 * 
	 * @param pos
	 *            - The position to update.
	 * @param surface
	 *            - The new surface.
	 */
	public void updateSprite(Pos pos, Surface surface)
	{
		updateSprite(pos.getXCoord(), pos.getYCoord(), surface);
	}

	/**
	 * Update the display.
	 */
	public void updateDisplay()
	{
		setTexturesSize(this.texturesSize);
	}

	/**
	 * Update the display for one Sprite.
	 * 
	 * @param x
	 *            - The Sprite's line number.
	 * @param y
	 *            - The Sprite's column number.
	 */
	public void updateDisplay(int x, int y)
	{
		// When the image hasn't been resized yet, it does the job.

		// Gets the image thanks to the Enum Surface.
		ImageIcon icon = new ImageIcon(getClass().getResource("textures/".concat(this.sprites[x][y].getSurface().getFileName())).getFile());

		// Converts the ImageIcon into an Image.
		Image img = icon.getImage();

		// Resizes the image and reconverts it into an ImageIcon.
		ImageIcon imgIcon = new ImageIcon(resize(toBufferedImage(img), this.texturesSize, this.texturesSize));

		// Set the new ImageIcon
		this.sprites[x][y].setIcon(imgIcon);
	}

	/**
	 * Update the display on a specific position.
	 * 
	 * @param pos
	 */
	public void updateDisplay(Pos pos)
	{
		updateDisplay(pos.getXCoord(), pos.getYCoord());
	}

	/**
	 * Get the Grid's width.
	 * 
	 * @return The Grid's width.
	 */
	public int getGridWidth()
	{
		return gridWidth;
	}

	/**
	 * Get the Grid's height.
	 * 
	 * @return The Grid's height.
	 */
	public int getGridHeight()
	{
		return gridHeight;
	}

	/**
	 * Set the textures displaying size.
	 * 
	 * @param size
	 *            - The new textures size.
	 */
	public void setTexturesSize(int size)
	{
		this.texturesSize = size;
		// This HashMap is a cache of the resized textures.
		// This avoids resizing the same image many times.
		HashMap<Surface, ImageIcon> cache = new HashMap<Surface, ImageIcon>();

		for (int line = 0; line < this.gridWidth; line++)
		{
			for (int column = 0; column < this.gridHeight; column++)
			{
				if (cache.containsKey(this.sprites[line][column].getSurface()))
				{
					// When this image has been resized before, it reuses it.
					this.sprites[line][column].setIcon(cache.get(this.sprites[line][column].getSurface()));

				} else
				{
					// When the image hasn't been resized yet, it does the job.

					// Gets the image thanks to the Enum Surface.
					ImageIcon icon = new ImageIcon(getClass().getResource("textures/".concat(this.sprites[line][column].getSurface().getFileName())).getFile());

					// Converts the ImageIcon into an Image.
					Image img = icon.getImage();

					// Resizes the image and reconverts it into an ImageIcon.
					ImageIcon imgIcon = new ImageIcon(resize(toBufferedImage(img), size, size));

					// Set the new ImageIcon
					this.sprites[line][column].setIcon(imgIcon);

					// Put the resized ImageIcon into the cache.
					cache.put(this.sprites[line][column].getSurface(), imgIcon);
				}
				// Resizes the JLabel.
				this.sprites[line][column].setSize(size, size);
			}
		}
		cache.clear();
	}

	/**
	 * Get the actual textures' size.
	 * 
	 * @return The actual textures' size.
	 */
	public int getTexturesSize()
	{
		return this.texturesSize;
	}

	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img
	 *            The Image to be converted
	 * @return The converted BufferedImage
	 */
	private static BufferedImage toBufferedImage(Image img)
	{
		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}

	/**
	 * Get a resized image.
	 * 
	 * @param image
	 *            - The BufferedImage to resize.
	 * @param width
	 *            - The destination width.
	 * @param height
	 *            - The destination height.
	 * 
	 * @return The resized image.
	 */
	private static BufferedImage resize(BufferedImage image, int width, int height)
	{
		ImprovedMultistepRescaleOp resampleOp = new ImprovedMultistepRescaleOp(width, height);
		return resampleOp.filter(image, null);
	}

	/**
	 * Search the given Sprite object in the array and returns its position.
	 * 
	 * @param s
	 *            - The Sprite to search.
	 * @return The Sprite's position.
	 * @throws SpriteNotFoundException
	 *             When the given Sprite isn't in the array.
	 */
	public Pos searchSprite(Sprite s) throws SpriteNotFoundException
	{
		for (int line = 0; line < this.gridWidth; line++)
		{
			for (int column = 0; column < this.gridHeight; column++)
			{
				if (this.sprites[line][column] == s)
					return new Pos(line, column);
			}
		}
		throw new SpriteNotFoundException();
	}
}
