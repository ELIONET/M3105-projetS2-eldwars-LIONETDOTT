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

	
	private int nbUnits;

	private int playerGold;

	private final String playerNickname;

	private final Commander commander;
	
	private final Team playerTeam;


	public Player(Team team) {
		this.playerTeam = team;
		this.commander = new Commander(this, null);
		this.nbUnits = 1;
		this.playerGold = 0;
		String temp = "";
		do {
			StringAskWindow newPlayerWindow = new StringAskWindow("Dialog", "Enter your nickname, please.");
			temp = newPlayerWindow.showAndWaitForResult();
		} while(temp.equals(""));
		this.playerNickname = temp;
	}

	public int getUnitsNb() {
		return this.nbUnits;
	}

	public Team getPlayerTeam() {
		return this.playerTeam;
	}

	public Commander getCommander() {
		return this.commander;
	}
	
	public int getPlayerGold(){
		return this.playerGold;
	}
	
	public String getNickname(){
		return this.playerNickname;
	}

	public void addOneUnitToPlayerCounter(){
		this.nbUnits++;
	}

	public void removeOneUnitToPlayerCounter(){
		this.nbUnits--;
	}
	
	public void addGoldToThePlayer(int amount){
		this.playerGold+=amount;
	}

	public void removeGoldToThePlayer(int amount){
		this.playerGold-=amount;
	}
	
	public void setPlayerGold(int amount){
		this.playerGold=amount;
	}
	
	public String toString() {
		return this.playerNickname + "|" + this.commander.toString();
	}
	
	public boolean equals(Player player) {
		if (player != null)
			return toString().equals(player.toString());
		
		return false;
	}
}
