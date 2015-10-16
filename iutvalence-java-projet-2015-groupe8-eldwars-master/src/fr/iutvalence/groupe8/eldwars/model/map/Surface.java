package fr.iutvalence.groupe8.eldwars.model.map;

/**
 * All surface types.
 * 
 * @author jullinic
 * @version 20150610
 */
public enum Surface {

	GRASS("G", "grass.png", true, true),

	ROCK("R", "rock.png", false, false),

	WATER("W", "water.png", false, true),

	SAND("S", "sand.png", true, true),

	EMPTY(" ", "empty.png", true, true),

	UNIT_BOWMAN("B", "bowman.png", false, true),

	UNIT_COMMANDER("C", "commander.png", false, true),

	UNIT_HORSEMAN("H", "horseman.png", false, true),

	UNIT_SOLDIER("So", "soldier.png", false, true);

	private String representation;

	private String fileName;

	private boolean canWalkOnSurface;

	private boolean canFireThroughSurface;

	private Surface(String desc, String fileName, boolean canWalkOn, boolean canFireThrough) {
		this.representation = desc;
		this.fileName = fileName;
		this.canWalkOnSurface = canWalkOn;
		this.canFireThroughSurface = canFireThrough;
	}

	public String toString() {
		return this.representation;
	}

	public String getFileName() {
		return this.fileName;
	}

	public boolean canWalkOnSurface() {
		return this.canWalkOnSurface;
	}

	public boolean canFireThrough() {
		return this.canFireThroughSurface;
	}

	public boolean equals(Surface surface) {
		if (surface.toString() == this.toString())
			return true;
		return false;
	}

}
