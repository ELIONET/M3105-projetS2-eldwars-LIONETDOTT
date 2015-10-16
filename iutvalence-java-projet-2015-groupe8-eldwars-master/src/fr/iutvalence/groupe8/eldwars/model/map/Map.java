package fr.iutvalence.groupe8.eldwars.model.map;

/**
 * The Map class.
 * 
 * @author Nicolas
 *
 */
public class Map {

	public final static int DEFAULT_MAP_WIDTH = 32;

	public final static int DEFAULT_MAP_HEIGHT = 32;

	private final int mapWidth;

	private final int mapHeight;
	
	private Grid mapGrid;

	private final String mapName;

	public Map() {
		this(DEFAULT_MAP_WIDTH, DEFAULT_MAP_HEIGHT);
	}

	public Map(int mapWidth, int mapHeight) {
		this.mapWidth = mapWidth;
		this.mapHeight = mapHeight;

		this.mapName = "Ezergal's Plains";
		this.mapGrid = new Grid(mapWidth, mapHeight);

	}

	public Grid getGrid() {
		return mapGrid;
	}

	public void setGrid(Grid grid) {
		this.mapGrid = grid;
	}

	public String getName() {
		return mapName;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}
}
