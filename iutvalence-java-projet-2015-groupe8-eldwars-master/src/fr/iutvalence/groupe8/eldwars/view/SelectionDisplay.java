package fr.iutvalence.groupe8.eldwars.view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.mortennobel.imagescaling.experimental.ImprovedMultistepRescaleOp;

import fr.iutvalence.groupe8.eldwars.Pos;

/**
 * The grid which displays the Player's selections.
 * 
 * @author Nicolas
 * @version 20150610
 */
public class SelectionDisplay extends JPanel {

	/**
	 * The textures side size.
	 */
	public static final int TEXTURE_SIZE = 32;

	/**
	 * The JLabel grid where are the JLabels.
	 */
	private final JLabel[][] gridDisplay;

	/**
	 * The boolean grid where are the selections.
	 */
	private final boolean[][] grid;

	/**
	 * The Grid's width.
	 */
	private final int gridWidth;

	/**
	 * The Grid's height.
	 */
	private final int gridHeight;

	/**
	 * The current textures' size.
	 */
	private int texturesSize;

	/**
	 * The GroundDisplay constructor. Generates the JLabel (with ImagesIcon)
	 * array.
	 * 
	 * @param gridWidth
	 * @param gridHeight
	 */
	public SelectionDisplay(int gridWidth, int gridHeight) {

		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.texturesSize = TEXTURE_SIZE;

		setLayout(new GridLayout(gridWidth, gridHeight));

		this.grid = new boolean[gridWidth][gridHeight];
		this.gridDisplay = new JLabel[gridWidth][gridHeight];

		for (int line = 0; line < gridWidth; line++) {
			for (int column = 0; column < gridHeight; column++) {

				this.grid[line][column] = false;

				// Creates the JLabel.
				JLabel tmpJLabel = new JLabel();

				// Sets the JLabel size.
				tmpJLabel.setSize(TEXTURE_SIZE, TEXTURE_SIZE);

				this.gridDisplay[line][column] = tmpJLabel;
				add(tmpJLabel);
			}
		}
	}

	/**
	 * Update the display.
	 */
	public void updateDisplay() {
		setTexturesSize(this.texturesSize);
	}

	/**
	 * Update the display for one Sprite.
	 * 
	 * @param x
	 *            - The cell's line number.
	 * @param y
	 *            - The cell's column number.
	 */
	public void updateDisplay(int x, int y) {
		// When the image hasn't been resized yet, it does the job.

		// Gets the image thanks to the Enum Surface.
		ImageIcon icon = new ImageIcon(getClass().getResource("textures/selection.png").getFile());

		// Converts the ImageIcon into an Image.
		Image img = icon.getImage();

		// Resizes the image and reconverts it into an ImageIcon.
		ImageIcon imgIcon = new ImageIcon(resize(GameWindow.toBufferedImage(img), this.texturesSize, this.texturesSize));

		// Set the new ImageIcon
		if (this.grid[x][y] == true)
			this.gridDisplay[x][y].setIcon(imgIcon);
		else
			this.gridDisplay[x][y].setIcon(null);
	}

	/**
	 * Update the display.
	 * 
	 * @param pos
	 */
	public void updateDisplay(Pos pos) {
		updateDisplay(pos.getXCoord(), pos.getYCoord());
	}

	/**
	 * Get the Grid's width.
	 * 
	 * @return The Grid's width.
	 */
	public int getGridWidth() {
		return gridWidth;
	}

	/**
	 * Get the Grid's height.
	 * 
	 * @return The Grid's height.
	 */
	public int getGridHeight() {
		return gridHeight;
	}

	/**
	 * Set the textures displaying size.
	 * 
	 * @param size
	 *            - The new textures size.
	 */
	public void setTexturesSize(int size) {
		this.texturesSize = size;

		// Gets the image thanks to the Enum Surface.
		ImageIcon icon = new ImageIcon(getClass().getResource("textures/selection.png").getFile());

		// Converts the ImageIcon into an Image.
		Image img = icon.getImage();

		// Resizes the image and reconverts it into an ImageIcon.
		ImageIcon imgIcon = new ImageIcon(resize(GameWindow.toBufferedImage(img), size, size));

		for (int line = 0; line < this.gridWidth; line++) {
			for (int column = 0; column < this.gridHeight; column++) {

				// Set the new ImageIcon
				if (grid[line][column] == true)
					this.gridDisplay[line][column].setIcon(imgIcon);
				else
					this.gridDisplay[line][column].setIcon(null);

				// Resizes the JLabel.
				this.gridDisplay[line][column].setSize(size, size);
			}
		}
	}

	/**
	 * Get the actual textures' size.
	 * 
	 * @return The actual textures' size.
	 */
	public int getTexturesSize() {
		return this.texturesSize;
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
	 * @return The resized image.
	 */
	private static BufferedImage resize(BufferedImage image, int width, int height) {
		ImprovedMultistepRescaleOp resampleOp = new ImprovedMultistepRescaleOp(width, height);
		return resampleOp.filter(image, null);
	}

	/**
	 * Selects one cell and updates the Display.
	 * 
	 * @param x
	 *            - The cell's x coordinate.
	 * @param y
	 *            - The cell's y coordinate.
	 */
	public void select(int x, int y) {
		this.grid[x][y] = true;
		updateDisplay(x, y);
	}

	/**
	 * Selects one cell and updates the Display.
	 * 
	 * @param pos
	 *            - The cell's position.
	 */
	public void select(Pos pos) {
		select(pos.getXCoord(), pos.getYCoord());
	}

	/**
	 * Unselects all cells.
	 */
	public void unselectAll() {
		for (int line = 0; line < this.gridWidth; line++) {
			for (int column = 0; column < this.gridHeight; column++) {
				this.grid[line][column] = false;
				updateDisplay(line, column);
			}
		}
	}

	/**
	 * Unselects one cell and updates the Display.
	 * 
	 * @param x
	 *            - The cell's x coordinate.
	 * @param y
	 *            - The cell's y coordinate.
	 */
	public void unselect(int x, int y) {
		this.grid[x][y] = false;
		updateDisplay(x, y);
	}

	/**
	 * Unselects one cell and updates the Display.
	 * 
	 * @param pos
	 *            - The cell's position.
	 */
	public void unselect(Pos pos) {
		unselect(pos.getXCoord(), pos.getYCoord());
	}

}
