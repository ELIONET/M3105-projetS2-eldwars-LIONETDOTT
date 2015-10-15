package fr.iutvalence.groupe8.eldwars.view.actions;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.jhlabs.image.GrayscaleFilter;

import fr.iutvalence.groupe8.eldwars.view.GameWindow;

/**
 * Attack action class (action button).
 * 
 * @author Nicolas
 * @version 20150610
 */
public class AttackAction extends Action {

	/**
	 * The AttackAction constructor.
	 */
	public AttackAction() {
		super(ActionType.ATTACK);
	}

	@Override
	public void grey(boolean bool) {
		if (bool) {
			GrayscaleFilter filter = new GrayscaleFilter();

			BufferedImage tmpImg = new BufferedImage(64, 64, BufferedImage.TRANSLUCENT);
			filter.filter(GameWindow.toBufferedImage(new ImageIcon(getClass().getResource("../textures/btnAttack.png")).getImage()), tmpImg);
			setIcon(new ImageIcon(tmpImg));

		} else {
			setIcon(new ImageIcon(getClass().getResource("../textures/btnAttack.png")));
		}
	}

}
