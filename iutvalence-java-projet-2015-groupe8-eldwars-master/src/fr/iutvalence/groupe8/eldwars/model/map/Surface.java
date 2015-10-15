package fr.iutvalence.groupe8.eldwars.model.map;

/**
 * All surface types.
 * 
 * @author jullinic
 * @version 20150610
 */
public enum Surface {

	/**
	 * Grass surface, Units can walk on.
	 */
	GRASS("G", "grass.png", true, true),

	/**
	 * Rock surface, Units can't walk on.
	 */
	ROCK("R", "rock.png", false, false),

	/**
	 * Water surface, Units can't swim.
	 */
	WATER("W", "water.png", false, true),

	/**
	 * Sand surface, Units can walk on.
	 */
	SAND("S", "sand.png", true, true),

	/**
	 * Empty surface.
	 */
	EMPTY(" ", "empty.png", true, true),

	/**
	 * When a bowman is on a surface.
	 */
	UNIT_BOWMAN("B", "bowman.png", false, true),

	/**
	 * When a commander is on a surface.
	 */
	UNIT_COMMANDER("C", "commander.png", false, true),

	/**
	 * When a horseman is on a surface.
	 */
	UNIT_HORSEMAN("H", "horseman.png", false, true),

	/**
	 * When a soldier is on a surface.
	 */
	UNIT_SOLDIER("So", "soldier.png", false, true);

	/**
	 * The String representation.
	 */
	private String representation;

	/**
	 * The file name.
	 */
	private String fileName;

	/**
	 * True if the player is able to walk on this Surface.
	 */
	private boolean canWalkOn;

	/**
	 * True if the player is able to fire through this Surface.
	 */
	private boolean canFireThrough;

	/**
	 * The Surface constructor.
	 * 
	 * @param desc
	 * @param fileName
	 */
	private Surface(String desc, String fileName, boolean canWalkOn, boolean canFireThrough) {
		this.representation = desc;
		this.fileName = fileName;
		this.canWalkOn = canWalkOn;
		this.canFireThrough = canFireThrough;
	}

	/**
	 * gets the String representation.
	 */
	public String toString() {
		return this.representation;
	}

	/**
	 * Gets the file name.
	 * 
	 * @return A string.
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * Returns true if a unit can walk on the surface, else otherwise.
	 * 
	 * @return A boolean.
	 */
	public boolean canWalkOn() {
		return this.canWalkOn;
	}

	/**
	 * Returns true if a unit can fire through the surface, else otherwise.
	 * 
	 * @return A boolean
	 */
	public boolean canFireThrough() {
		return this.canFireThrough;
	}

	/**
	 * Checks if the given Surface is the same as this one.
	 * 
	 * @param surface
	 *            - The second Surface.
	 * @return A boolean.
	 */
	public boolean equals(Surface surface) {
		if (surface.toString() == this.toString())
			return true;
		return false;
	}

}
