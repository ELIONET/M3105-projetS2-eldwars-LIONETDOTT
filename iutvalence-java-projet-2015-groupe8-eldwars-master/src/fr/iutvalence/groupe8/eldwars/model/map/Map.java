package fr.iutvalence.groupe8.eldwars.model.map;

/**
 * The Map class.
 * 
 * @author Nicolas
 *
 */
public class Map {

	/**
	 * The default Map width.
	 */
	public final static int DEFAULT_MAP_WIDTH = 32;

	/**
	 * The default Map height
	 */
	public final static int DEFAULT_MAP_HEIGHT = 32;

	/**
	 * The Map's width.
	 */
	private final int mapWidth;

	/**
	 * The Map's height.
	 */
	private final int mapHeight;

	/**
	 * The Map's grid.
	 */
	private Grid grid;

	/**
	 * The Map's name.
	 */
	private final String name;

	/**
	 * The Map constructor.
	 */
	public Map() {
		this(DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT);
	}

	/**
	 * The Map constructor with given width and height.
	 * 
	 * @param mapWidth
	 * @param mapHeight
	 */
	public Map(int mapWidth, int mapHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;

		this.name = "Ezergal's Plains";
		this.grid = new Grid(mapWidth, mapHeight);

	}

	/**
	 * Get the Map Grid.
	 * 
	 * @return The grid.
	 */
	public Grid getGrid() {
		return grid;
	}
	
	/**
	 * Set the Map Grid.
	 * @param grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	

	/**
	 * Get the Map name.
	 * 
	 * @return The Map name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the Map width
	 * 
	 * @return The Map width.
	 */
	public int getMapWidth() {
		return mapWidth;
	}

	/**
	 * Get the Map height.
	 * 
	 * @return The Map height.
	 */
	public int getMapHeight() {
		return mapHeight;
	}
}
