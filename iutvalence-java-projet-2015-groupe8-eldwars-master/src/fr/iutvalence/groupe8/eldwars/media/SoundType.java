package fr.iutvalence.groupe8.eldwars.media;

/**
 * Sound by type
 * 
 * @author Clément
 *
 */
public enum SoundType {

	/**
	 * Bowman attack sound
	 */
	BOW("sounds/bow.wav"),

	/**
	 * Click sound
	 */
	CLICK("sounds/click.wav"),

	/**
	 * Another click sound
	 */
	CLICK2("sounds/click2.wav"),

	/**
	 * Error click sound
	 */
	ERROR("sounds/error.wav"),

	/**
	 * Another error click sound
	 */
	ERROR2("sounds/error2.wav"),

	/**
	 * Movement on grass sound
	 */
	GRASS("sounds/grass.wav"),

	/**
	 * Movement on sand sound
	 */
	SAND("sounds/sand.wav"),

	/**
	 * Sound when a upgrade/recruit is succesfully done
	 */
	SUCCESS("sounds/success.wav"),

	/**
	 * Sound when you launch a game
	 */
	START("sounds/start.wav"),

	/**
	 * Attack Sound with melee units
	 */
	SWORD("sounds/sword.wav"),

	/**
	 * Victory sound
	 */
	VICTORY("sounds/victory.wav");

	/**
	 * Sound filename
	 */
	private String address;

	/**
	 * SoundType constructorm useful to set the address.
	 * 
	 * @param address - The sound address.
	 */
	private SoundType(String address) {
		this.address = address;
	}

	/**
	 * Get the sound address
	 * 
	 * @return The Sound address.
	 */
	public String getAddress() {
		return this.address;
	}
}
