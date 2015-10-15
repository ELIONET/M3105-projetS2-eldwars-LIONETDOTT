package fr.iutvalence.groupe8.eldwars.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;

import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;

/**
 * JFrame class used to ask a text.
 * 
 * @author Nicolas
 * @version 20150610
 *
 */
public class StringAskWindow extends JFrame {

	/**
	 * The default "OK" button text.
	 */
	private static final String DEFAULT_OK_BUTTON_TEXT = "OK";

	/**
	 * The info label.
	 */
	private JLabel infoLabel;

	/**
	 * The text entry field.
	 */
	private JTextField textEntry;

	/**
	 * The "OK" button.
	 */
	private JButton okButton;

	/**
	 * The StringAskWindow constructor.
	 * 
	 * @param title
	 *            - The Window's title.
	 * @param labelText
	 *            - The displayed information text.
	 */
	public StringAskWindow(String title, String labelText) {
		this(title, labelText, DEFAULT_OK_BUTTON_TEXT);
	}

	/**
	 * The StringAskWindow constructor.
	 * 
	 * @param title
	 *            -The Window's title.
	 * @param labelText
	 *            - The displayed information text.
	 * @param buttonText
	 *            - The button's text.
	 */
	public StringAskWindow(String title, String labelText, String buttonText) {

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		setResizable(false);
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(360, 110);
		setLocationRelativeTo(null);
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Add the information label.
		this.infoLabel = new JLabel(labelText);
		this.infoLabel.setAlignmentX(CENTER_ALIGNMENT);
		add(this.infoLabel);

		// Add the text entry.
		this.textEntry = new JTextField();
		this.textEntry.setAlignmentX(CENTER_ALIGNMENT);
		add(this.textEntry);

		// Add the OK button.
		this.okButton = new JButton(buttonText);

		this.okButton.addActionListener(new ActionListener() {

			/**
			 * On event on the button.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				SoundEngine.play(SoundType.CLICK2);
			}
		});

		this.okButton.setAlignmentX(CENTER_ALIGNMENT);

		add(this.okButton);

	}

	/**
	 * Shows the dialog and returns the entered text.
	 * 
	 * @return The entered text in a String.
	 */
	public String showAndWaitForResult() {
		this.setVisible(true);

		// Waits until the window is not visible.
		while (this.isVisible()) {
			
			try {
				Thread.currentThread();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		return this.textEntry.getText();
	}

}
