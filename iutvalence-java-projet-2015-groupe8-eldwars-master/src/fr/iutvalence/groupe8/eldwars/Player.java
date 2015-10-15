package fr.iutvalence.groupe8.eldwars;


import fr.iutvalence.groupe8.eldwars.model.units.Commander;
import fr.iutvalence.groupe8.eldwars.view.StringAskWindow;

/**
 * Player class.
 * 
 * @author Nicolas
 * @version 20150606
 */
public class Player {

	/**
	 * The number of units.
	 */
	private int nbUnits;

	/**
	 * The Player's gold amount.
	 */
	private int gold;

	/**
	 * The Player's nickname.
	 */
	private final String nickname;

	/**
	 * The commander unit.
	 */
	private final Commander commander;
	
	/**
	 * The Player's Team.
	 */
	private final Team team;

	/**
	 * The Player constructor.
	 */
	public Player(Team team) {
		this.team = team;
		this.commander = new Commander(this, null);
		this.nbUnits = 1;
		this.gold = 0;
		String temp = "";
		do {
			StringAskWindow newPlayerWindow = new StringAskWindow("Dialog", "Enter your nickname, please.");
			temp = newPlayerWindow.showAndWaitForResult();
		} while(temp.equals(""));
		this.nickname = temp;
	}

	/**
	 * Get the number of units.
	 * 
	 * @return The number of units.
	 */
	public int getUnitsNb() {
		return this.nbUnits;
	}

	/**
	 * Get the Player's Team.
	 * 
	 * @return A Team object.
	 */
	public Team getTeam() {
		return this.team;
	}

	/**
	 * Get the Player's Commander.
	 * 
	 * @return The Player's Commander.
	 */
	public Commander getCommander() {
		return this.commander;
	}
	
	/**
	 * Get the Player's Current gold
	 * 
	 * @return The Player's Gold
	 */
	public int getGold(){
		return this.gold;
	}
	
	/**
	 * Get the Player's nickname
	 * 
	 * @return The Player's Nickname
	 */
	public String getNickname(){
		return this.nickname;
	}

	/**
	 * Increase by one the Player's amount of unit.
	 */
	public void addUnit(){
		this.nbUnits++;
	}
	/**
	 * Remove by to the Player's amount of unit.
	 */
	public void removeUnit(){
		this.nbUnits--;
	}
	
	/**
	 * Add gold to the Player.
	 * @param amount
	 */
	public void addGold(int amount){
		this.gold+=amount;
	}
	/**
	 * Remove gold to the player.
	 * @param amount
	 */
	public void removeGold(int amount){
		this.gold-=amount;
	}
	
	/**
	 * Set gold to the Player.
	 * @param amount
	 */
	public void setGold(int amount){
		this.gold=amount;
	}
	
	public String toString() {
		return this.nickname + "|" + this.commander.toString();
	}
	
	public boolean equals(Player player) {
		if (player != null)
			return toString().equals(player.toString());
		
		return false;
	}
}
