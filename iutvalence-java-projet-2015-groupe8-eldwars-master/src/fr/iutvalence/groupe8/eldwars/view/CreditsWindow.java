package fr.iutvalence.groupe8.eldwars.view;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Credits Window
 * 
 * @author Nicolas Jullien, Clément Dott
 * @version 20150611
 *
 */
public class CreditsWindow extends JFrame {

	/**
	 * Jlabel of the projectLeader
	 */
	private JLabel projectLeader;
	
	/**
	 * Jlabel of the codeLeader
	 */
	private JLabel codeLeader;
	
	/**
	 * Jlabel of the codeAids
	 */
	private JLabel codeAids;
	
	/**
	 * Jlabel of the Game art designer
	 */
	private JLabel gameArt;
	
	/**
	 * Jlabel of the composer
	 */
	private JLabel composer;
	
	/**
	 * Credits window builder
	 */
	public CreditsWindow() {
		
		setSize(300, 340);
		setTitle("Credits");
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setLocationRelativeTo(null);
		
		this.projectLeader = new JLabel("Project leader - Emmanuel Lionet");
		this.projectLeader.setAlignmentX(CENTER_ALIGNMENT);
		this.projectLeader.setBorder(BorderFactory.createEmptyBorder(30, 0, 40, 0));
		add(this.projectLeader);
		
		this.codeLeader = new JLabel("Code leader - Nicolas Jullien");
		this.codeLeader.setAlignmentX(CENTER_ALIGNMENT);
		this.codeLeader.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		add(this.codeLeader);
		
		this.codeAids = new JLabel("Code aids - Florian Pignard, Clément Dott");
		this.codeAids.setAlignmentX(CENTER_ALIGNMENT);
		this.codeAids.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		add(this.codeAids);
		
		this.gameArt = new JLabel("Game Art - Clément Dott");
		this.gameArt.setAlignmentX(CENTER_ALIGNMENT);
		this.gameArt.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		add(this.gameArt);
		
		this.composer = new JLabel("Composer - Nicolas Jullien");
		this.composer.setAlignmentX(CENTER_ALIGNMENT);
		this.composer.setBorder(BorderFactory.createEmptyBorder(0, 0, 40, 0));
		add(this.composer);

		
	}

	/**
	 * Waits for the window to close
	 */
	public void showAndWaitForResult() {
		setVisible(true);
		while (isVisible()) {
			
			try {
				Thread.currentThread();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return;
	}

}
