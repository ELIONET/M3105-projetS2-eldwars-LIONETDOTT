package fr.iutvalence.groupe8.eldwars.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.iutvalence.groupe8.eldwars.Game;
import fr.iutvalence.groupe8.eldwars.Player;
import fr.iutvalence.groupe8.eldwars.Team;
import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;

/**
 * Main Menu Window Display
 * @author Nicolas,Clément
 *
 */
public class MainMenuWindow extends JFrame {

	/**
	 * The JPanel that contains the title JLabel.
	 */
	private JPanel titleContainer;

	/**
	 * The title JLabel.
	 */
	private JLabel title;

	/**
	 * The JPanel that contains the title JLabel.
	 */
	private JPanel subtitleContainer;

	/**
	 * The title JLabel.
	 */
	private JLabel subtitle;

	/**
	 * The JPanel that contains the title JLabel.
	 */
	private JPanel newGameContainer;

	/**
	 * The new game JButton.
	 */
	private JButton newGame;

	/**
	 * The JPanel that contains the title JLabel.
	 */
	private JPanel loadGameContainer;

	/**
	 * The load game JButton.
	 */
	private JButton loadGame;

	/**
	 * The JPanel that contains the quit and credits JLabels.
	 */
	private JPanel lastButtonsContainer;

	/**
	 * The quit JButton.
	 */
	private JButton quitGame;

	/**
	 * The credits JButton.
	 */
	private JButton credits;

	/**
	 * The last MainMenuActionType.
	 */
	private volatile MainMenuActionType lastAction;

	/**
	 * The MainMenuWindow constructor.
	 */
	public MainMenuWindow() {

		// Window.
		setSize(700, 400);
		setResizable(false);
		setTitle("EldWars - Main menu");
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Title.
		this.titleContainer = new JPanel();
		this.titleContainer.setPreferredSize(new Dimension(getWidth(), 100));
		add(this.titleContainer);

		this.title = new JLabel("EldWars");
		this.title.setFont(new Font("Arial", 100, 100));
		this.titleContainer.add(this.title);

		// Subtitle.
		this.subtitleContainer = new JPanel();
		this.subtitleContainer.setPreferredSize(new Dimension(getWidth(), 48));
		add(this.subtitleContainer);

		this.subtitle = new JLabel("Annihilate the ennemy.");
		this.subtitle.setFont(new Font("Arial", 18, 18));
		this.subtitleContainer.add(this.subtitle);

		// New Game.
		this.newGameContainer = new JPanel();
		this.newGameContainer.setPreferredSize(new Dimension(getWidth(), 48));
		add(this.newGameContainer);

		this.newGame = new JButton("New game");
		this.newGame.setPreferredSize(new Dimension(256, 32));
		this.newGame.addActionListener(new ActionListener() {

			/**
			 * On event on the button.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				MainMenuWindow menu = (MainMenuWindow) btn.getParent().getParent().getParent().getParent().getParent();
				menu.setLastAction(MainMenuActionType.NEW_GAME);
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		this.newGameContainer.add(this.newGame);

		// Load a Game.
		this.loadGameContainer = new JPanel();
		this.loadGameContainer.setPreferredSize(new Dimension(getWidth(), 48));
		add(this.loadGameContainer);

		this.loadGame = new JButton("Load a game");
		this.loadGame.setEnabled(false);
		this.loadGame.setPreferredSize(new Dimension(256, 32));
		this.loadGame.addActionListener(new ActionListener() {

			/**
			 * On event on the button.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				MainMenuWindow menu = (MainMenuWindow) btn.getParent().getParent().getParent().getParent().getParent();
				menu.setLastAction(MainMenuActionType.LOAD_GAME);
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		this.loadGameContainer.add(this.loadGame);

		// Last buttons.
		this.lastButtonsContainer = new JPanel();
		this.lastButtonsContainer.setPreferredSize(new Dimension(getWidth(), 48));
		add(this.lastButtonsContainer);

		this.credits = new JButton("Credits");
		this.credits.setPreferredSize(new Dimension(128, 32));
		this.credits.addActionListener(new ActionListener() {

			/**
			 * On event on the button.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				MainMenuWindow menu = (MainMenuWindow) btn.getParent().getParent().getParent().getParent().getParent();
				menu.setLastAction(MainMenuActionType.CREDITS);
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		this.lastButtonsContainer.add(this.credits);

		this.quitGame = new JButton("Quit");
		this.quitGame.setPreferredSize(new Dimension(123, 32));
		this.quitGame.addActionListener(new ActionListener() {

			/**
			 * On event on the button.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				MainMenuWindow menu = (MainMenuWindow) btn.getParent().getParent().getParent().getParent().getParent();
				menu.setLastAction(MainMenuActionType.QUIT_GAME);
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		this.lastButtonsContainer.add(this.quitGame);

	}

	/**
	 * Gets the last Action chosed (useful to know which button has been
	 * pressed).
	 * 
	 * @param type
	 *            - The MainMenuActionType relative to the pressed button.
	 */
	public void setLastAction(MainMenuActionType type) {
		this.lastAction = type;
	}

	/**
	 * Sets the last Action chosed.
	 * 
	 * @return The last Action.
	 */
	public MainMenuActionType getLastAction() {
		MainMenuActionType temp = this.lastAction;
		this.lastAction = null;
		return temp;
	}
	
	/**
	 * Shows the dialog and hides when the game quits.
	 */
	public void showAndWaitForResult() {
		setVisible(true);
		MainMenuActionType lastAct = null;
		
		while (isVisible()) {
			
			while (isVisible() && lastAct == null) {

				try {
					Thread.currentThread();
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				lastAct = getLastAction();
			}

			setEnabled(false);
			
			if (lastAct == MainMenuActionType.NEW_GAME) {
				setVisible(false);
				Player p1 = new Player(new Team(Color.BLUE));
				Player p2 = new Player(new Team(Color.RED));
				new Game(p1, p2).start();
				setVisible(true);
				setAlwaysOnTop(true);
				setAlwaysOnTop(false);
			} else if(lastAct == MainMenuActionType.LOAD_GAME) {
				System.err.println("This functionnality is not implemented for now. Sorry for the inconveniance.");
			} else if (lastAct == MainMenuActionType.CREDITS) {
				new CreditsWindow().showAndWaitForResult();
				setAlwaysOnTop(true);
				setAlwaysOnTop(false);
			} else if (lastAct == MainMenuActionType.QUIT_GAME) {
				dispose();
			}

			setEnabled(true);
			
			lastAct = null;
			
		}
	}

}
