package fr.iutvalence.groupe8.eldwars.view.actions;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Player Stats Class
 * 
 * @author Clément
 * @version 20150609
 */
public class PlayerStats extends JPanel {

	/**
	 * The default nickname.
	 */
	private static final String DEFAULT_NICKNAME = "Nope";

	/**
	 * The default gold amount.
	 */
	private static final int DEFAULT_GOLD = 0;

	/**
	 * Player's Nickname
	 */
	private JLabel nickname;

	/**
	 * Player's Gold
	 */
	private JLabel gold;

	/**
	 * Gold Icon
	 */
	private JLabel goldIcon;

	/**
	 * The default PlayerStat constructor
	 */
	public PlayerStats() {
		this(DEFAULT_NICKNAME, DEFAULT_GOLD);
	}

	/**
	 * The PlayerStat constructor
	 * 
	 * @param nickname
	 * @param gold
	 */
	public PlayerStats(String nickname, int gold) {

		this.nickname = new JLabel();
		this.nickname.setText(nickname);
		add(this.nickname);

		this.goldIcon = new JLabel();
		add(this.goldIcon);
		goldIcon.setIcon(new ImageIcon(getClass().getResource("../textures/psGold.png")));
		goldIcon.setSize(16, 16);
		goldIcon.setToolTipText("Your Current Gold");

		this.gold = new JLabel();
		this.gold.setText(String.valueOf(gold));
		add(this.gold);
	}

	/**
	 * Set the Player's nickname
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {

		this.nickname.setText(nickname);
	}

	/**
	 * Set the Player's Gold
	 * 
	 * @param gold
	 */
	public void setGold(int gold) {

		this.gold.setText(String.valueOf(gold));

	}

}
