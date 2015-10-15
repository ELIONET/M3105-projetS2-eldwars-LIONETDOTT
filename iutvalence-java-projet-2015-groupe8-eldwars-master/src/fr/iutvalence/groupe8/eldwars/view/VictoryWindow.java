package fr.iutvalence.groupe8.eldwars.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;

/**
 * Victory window
 * 
 * @author Nicolas Jullien,Clément Dott
 *
 */
public class VictoryWindow extends JFrame {

	/**
	 * Victory Icon
	 */
	private JLabel winIcon;

	/**
	 * Victory text
	 */
	private JLabel winText;

	/**
	 * The close button.
	 */
	private JButton closeBtn;

	/**
	 * Victory Window Constructor
	 * 
	 * @param winnerNickname
	 */
	public VictoryWindow(String winnerNickname) {

		this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(512, 256);
		setUndecorated(true);
		((JComponent) getContentPane()).setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		setOpacity(0.92f);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Victory!");

		this.winIcon = new JLabel();
		this.winIcon.setAlignmentX(CENTER_ALIGNMENT);
		this.winIcon.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		this.winIcon.setIcon(new ImageIcon(getClass().getResource("textures/victory.png").getFile()));
		this.winIcon.setSize(128, 128);
		add(this.winIcon);

		this.winText = new JLabel("Congratulations " + winnerNickname + "! You won the game.");
		this.winText.setFont(new Font("Arial", 20, 20));
		this.winText.setAlignmentX(CENTER_ALIGNMENT);
		add(this.winText);
		
		this.closeBtn = new JButton("Close");
		this.closeBtn.setAlignmentX(CENTER_ALIGNMENT);
		this.closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		add(this.closeBtn);

		SoundEngine.play(SoundType.VICTORY);

	}
	
	/**
	 * Displays the window and waits until it's closed.
	 */
	public void showAndWaitForResult() {
		setVisible(true);
		setAlwaysOnTop(true);
		setAlwaysOnTop(false);
		while (isVisible()) {
			try {
				Thread.currentThread();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setVisible(false);
	}
}
