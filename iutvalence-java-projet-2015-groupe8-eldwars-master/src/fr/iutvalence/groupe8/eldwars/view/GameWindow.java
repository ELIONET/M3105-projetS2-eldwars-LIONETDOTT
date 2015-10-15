package fr.iutvalence.groupe8.eldwars.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.model.map.Map;
import fr.iutvalence.groupe8.eldwars.view.actions.ActionType;
import fr.iutvalence.groupe8.eldwars.view.actions.ActionsArea;

/**
 * The GameWindow class, used to display the game.
 * 
 * @author Nicolas
 * @version 20150606
 */
public class GameWindow extends JFrame {

	/**
	 * The GameWindow default width.
	 */
	private static final int WINDOW_DEFAULT_WIDTH = 960;

	/**
	 * The GameWindow default height.
	 */
	private static final int WINDOW_DEFAULT_HEIGHT = 640;

	/**
	 * True if the keyPress event has to be listened.
	 */
	private boolean listeningKeyPressEvent;

	/**
	 * The main JPanel.
	 */
	private JPanel mainPanel;

	/**
	 * The panel used to split the window.
	 */
	private JSplitPane panelSeparator;

	/**
	 * The JPanel where is placed the Grid.
	 */
	private JLayeredPane gridArea;

	/**
	 * The ground grid JPanel.
	 */
	private GroundDisplay groundGrid;

	/**
	 * The selection grid JPanel.
	 */
	private SelectionDisplay selectionGrid;

	/**
	 * The units grid JPanel.
	 */
	private UnitsDisplay unitsGrid;

	/**
	 * The JPanel where are placed the action buttons.
	 */
	private ActionsArea actionsArea;

	/**
	 * The last clicked position on the display.
	 */
	private volatile Pos lastGivenPos;

	/**
	 * The last used action (by clicking an action button).
	 */
	private volatile ActionType lastUsedAction;

	/**
	 * The default GameWindow constructor.
	 */
	public GameWindow(int gridWidth, int gridHeight) {

		this.listeningKeyPressEvent = false;

		// Sets the default operation when clicking on the window's X button.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Sets the window's main panel.
		this.mainPanel = new JPanel();
		getContentPane().add(this.mainPanel);

		// Sets the window's minimum size.
		this.setMinimumSize(new Dimension(WINDOW_DEFAULT_WIDTH, WINDOW_DEFAULT_HEIGHT));

		// Sets a BoxLayout to it.
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.PAGE_AXIS));

		// Sets the window's size.
		this.setSize(WINDOW_DEFAULT_WIDTH, WINDOW_DEFAULT_HEIGHT);

		// Centers the window.
		setLocationRelativeTo(null);

		// Sets the window's title.
		setTitle("EldWars, the game.");

		// Adds a listener on the resize event.
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent evt) {
				
				GameWindow gw = (GameWindow) evt.getSource();
				
				// Resizes the GameWindow when it's to small.
				Dimension d = gw.getSize();
				Dimension minD = gw.getMinimumSize();
				if (d.width < minD.width)
					d.width = minD.width;
				if (d.height < minD.height)
					d.height = minD.height;
				gw.setSize(d);

				// Recovers the source of the event.
				GameWindow c = (GameWindow) evt.getSource();

				// Replaces all its components.
				c.replaceComponents();
			}
		});

		// Creates the main split pane.
		this.panelSeparator = new JSplitPane();

		// Sets its orientation to vertical.
		this.panelSeparator.setOrientation(JSplitPane.VERTICAL_SPLIT);

		// Sets the divider size.
		this.panelSeparator.setDividerSize(1);

		// Sets the divider location.
		this.panelSeparator.setDividerLocation(getHeight() - ActionsArea.ACTIONS_AREA_HEIGHT);

		// Locks its position.
		this.panelSeparator.setEnabled(false);
		// Adds the JSplitPanel.
		this.mainPanel.add(this.panelSeparator);

		// Creates the grid panel.
		this.gridArea = new JLayeredPane();

		// Cancels its layout.
		this.gridArea.setLayout(null);

		// Adds the grid to the upper part of the separated panel.
		this.panelSeparator.setTopComponent(this.gridArea);

		// Creates the actions panel.
		this.actionsArea = new ActionsArea();

		// Adds the actions to the lower part of the separated panel.
		this.panelSeparator.setBottomComponent(this.actionsArea);

		// Creates the ground grid.
		this.groundGrid = new GroundDisplay(gridWidth, gridHeight);

		// Sets its layout to a GridLayout.
		this.groundGrid.setLayout(new GridLayout(gridWidth, gridHeight));

		// Places and resizes the ground grid.
		this.groundGrid.setBounds(0, 0, gridWidth * GroundDisplay.TEXTURE_SIZE, gridHeight * GroundDisplay.TEXTURE_SIZE);

		// Adds the ground grid to the layer 2.
		this.gridArea.add(this.groundGrid, 2);

		this.groundGrid.setOpaque(false);

		// Creates the selection grid;
		this.selectionGrid = new SelectionDisplay(gridWidth, gridHeight);

		// Sets its layout to a GridLayout.
		this.selectionGrid.setLayout(new GridLayout(gridWidth, gridHeight));

		// Places and resizes the ground grid.
		this.selectionGrid.setBounds(0, 0, gridWidth * GroundDisplay.TEXTURE_SIZE, gridHeight * GroundDisplay.TEXTURE_SIZE);

		// Adds the ground grid to the layer 0.
		this.gridArea.add(this.selectionGrid, 0);

		this.selectionGrid.setOpaque(false);

		// Displays the units grid.
		this.unitsGrid = new UnitsDisplay(gridWidth, gridHeight);

		// Sets its layout to a GridLayout.
		this.unitsGrid.setLayout(new GridLayout(gridWidth, gridHeight));

		// Places and resizes the units grid.
		this.unitsGrid.setBounds(0, 0, gridWidth * GroundDisplay.TEXTURE_SIZE, gridHeight * GroundDisplay.TEXTURE_SIZE);

		// Adds the units grid to the layer 1.
		this.gridArea.add(this.unitsGrid, 1);

		this.unitsGrid.setOpaque(false);

	}

	/**
	 * Replaces all components after a resize.
	 */
	public void replaceComponents() {
		// Replaces the divider.
		this.panelSeparator.setDividerLocation(getHeight() - ActionsArea.ACTIONS_AREA_HEIGHT);

		// Calculates the window's bigest side.
		int maxWindowSideSize;
		if (getWidth() > getHeight() - ActionsArea.ACTIONS_AREA_HEIGHT)
			maxWindowSideSize = getHeight() - ActionsArea.ACTIONS_AREA_HEIGHT - 16;
		else
			maxWindowSideSize = getWidth() - 16;

		// Calculates the Grid's biger side.
		int maxGridSideSize;
		if (this.groundGrid.getGridWidth() > this.groundGrid.getGridHeight())
			maxGridSideSize = this.groundGrid.getGridWidth();
		else
			maxGridSideSize = this.groundGrid.getGridHeight();

		// Calculates the resize ratio.
		float ratio = (float) maxWindowSideSize / (maxGridSideSize * GroundDisplay.TEXTURE_SIZE);

		// Applies the ratio to the default texture size.
		int texSize = (int) (GroundDisplay.TEXTURE_SIZE * ratio);

		// Calculates the grid's side lenght.
		int gridSizeX = this.groundGrid.getGridWidth() * texSize;
		int gridSizeY = this.groundGrid.getGridHeight() * texSize;

		// Resizes and replaces the groundGrid.
		this.groundGrid.setTexturesSize(texSize);
		this.groundGrid.setBounds(getWidth() / 2 - gridSizeX / 2, (getHeight() / 2 - ActionsArea.ACTIONS_AREA_HEIGHT / 2) - gridSizeY / 2, gridSizeX, gridSizeY);

		// Resizes and replaces the unitsGrid.
		this.unitsGrid.setTexturesSize(texSize);
		this.unitsGrid.setBounds(getWidth() / 2 - gridSizeX / 2, (getHeight() / 2 - ActionsArea.ACTIONS_AREA_HEIGHT / 2) - gridSizeY / 2, gridSizeX, gridSizeY);

		// Resizes and replaces the selectionGrid.
		this.selectionGrid.setTexturesSize(texSize);
		this.selectionGrid.setBounds(getWidth() / 2 - gridSizeX / 2, (getHeight() / 2 - ActionsArea.ACTIONS_AREA_HEIGHT / 2) - gridSizeY / 2, gridSizeX, gridSizeY);

		// Runs the garbage collector.
		System.gc();
	}

	@Override
	public void setVisible(boolean bool) {
		super.setVisible(bool);
		if (bool) {
			setAlwaysOnTop(true);
			setAlwaysOnTop(false);
		}
	}
	
	/**
	 * Gets the ground GridDisplay.
	 * 
	 * @return A GridDisplay.
	 */
	public GroundDisplay getGroundGridDisplay() {
		return this.groundGrid;
	}

	/**
	 * Gets the selection SelectionDisplay.
	 * 
	 * @return A SelectionDisplay.
	 */
	public SelectionDisplay getSelectionDisplay() {
		return this.selectionGrid;
	}

	/**
	 * Gets the units UnitsDisplay.
	 * 
	 * @return A GridDisplay.
	 */
	public UnitsDisplay getUnitsGridDisplay() {
		return this.unitsGrid;
	}

	/**
	 * Gets the actions area.
	 * 
	 * @return The actions area.
	 */
	public ActionsArea getActionsArea() {
		return this.actionsArea;
	}

	/**
	 * Gets the last click position of the display.
	 * 
	 * @return A position.
	 */
	public Pos getLastGivenPos() {
		// Waits a little time to recover the attribute.
		try {
			Thread.currentThread();
			Thread.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Pos tempPos = this.lastGivenPos;
		this.lastGivenPos = null;
		return tempPos;
	}

	/**
	 * Sets the last clicked position of the display.
	 */
	public void setLastGivenPos(Pos pos) {
		this.lastGivenPos = pos;
	}

	/**
	 * Waits until a Pos is returned.
	 * 
	 * @return A Pos.
	 */
	public Pos waitForAPos() {
		Pos givenPos = null;
		do {
			givenPos = this.getLastGivenPos();
		} while (givenPos == null);
		return givenPos;
	}

	/**
	 * Converts a given Image into a BufferedImage.
	 *
	 * @param img
	 *            - The Image to be converted.
	 * @return The converted BufferedImage.
	 */
	public static BufferedImage toBufferedImage(Image img) {
		// Create a buffered image with transparency.
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image.
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image.
		return bimage;
	}

	/**
	 * Sets the last pressed Action button.
	 * 
	 * @param An ActionType.
	 */
	public void setLastUsedAction(ActionType type) {
		this.lastUsedAction = type;
	}

	/**
	 * Gets the last pressed Action button.
	 * 
	 * @return An ActionType.
	 */
	public ActionType getLastUsedAction() {
		ActionType tmp = this.lastUsedAction;
		this.lastUsedAction = null;
		return tmp;
	}

}
