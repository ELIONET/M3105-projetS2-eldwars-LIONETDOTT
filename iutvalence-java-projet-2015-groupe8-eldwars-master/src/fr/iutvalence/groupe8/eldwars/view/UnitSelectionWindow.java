package fr.iutvalence.groupe8.eldwars.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;
import fr.iutvalence.groupe8.eldwars.model.units.UnitType;

/**
 * Recruit units display (Jframe)
 * 
 * @author Nicolas,Clément
 *
 */
public class UnitSelectionWindow extends JFrame {

	private JPanel mainPanel;
	
	private UnitSelectionBox leftPanel;

	private UnitSelectionBox centerPanel;

	private UnitSelectionBox rightPanel;
	
	private JButton closeBtn;

	/**
	 * Different JPanels with units choice
	 */
	public UnitSelectionWindow() {

		setTitle("Select a unit.");
		setSize(500, 300);
		setUndecorated(true);
		((JComponent) getContentPane()).setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		setOpacity(0.92f);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.mainPanel = new JPanel(new GridLayout(1, 3));
		add(this.mainPanel);

		this.leftPanel = new UnitSelectionBox(UnitType.SOLDIER);
		this.leftPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.mainPanel.add(this.leftPanel);

		this.centerPanel = new UnitSelectionBox(UnitType.BOWMAN);
		this.centerPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.mainPanel.add(this.centerPanel);

		this.rightPanel = new UnitSelectionBox(UnitType.HORSEMAN);
		this.rightPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.mainPanel.add(this.rightPanel);
		
		this.closeBtn = new JButton("Cancel");
		this.closeBtn.setAlignmentX(CENTER_ALIGNMENT);
		this.closeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SoundEngine.play(SoundType.CLICK2);
			}
		});
		add(this.closeBtn);

	}

	/**
	 * Displays the window and waits until it's closed to return the choosen
	 * UnitType.
	 * 
	 * @return A UnitType.
	 */
	public UnitType showAndWaitForResult() {
		setVisible(true);
		while (this.isVisible() && !this.leftPanel.isChoosen() && !this.centerPanel.isChoosen() && !this.rightPanel.isChoosen()) {
			try {
				Thread.currentThread();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		setVisible(false);

		if (this.leftPanel.isChoosen())
			return this.leftPanel.getUnitType();

		else if (this.centerPanel.isChoosen())
			return this.centerPanel.getUnitType();

		else if (this.rightPanel.isChoosen())
			return this.rightPanel.getUnitType();

		else
			return null;
	}

}
