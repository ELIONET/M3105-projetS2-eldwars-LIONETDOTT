package fr.iutvalence.groupe8.eldwars.view.actions;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.jhlabs.image.GrayscaleFilter;

import fr.iutvalence.groupe8.eldwars.view.GameWindow;

/**
 * Move action class (action button).
 * 
 * @author Nicolas
 * @version 20150610
 */
public class MoveAction extends Action {

	/**
	 * The MoveAction constructor.
	 */
	public MoveAction() {
		super(ActionType.MOVE);
	}

	@Override
	public void grey(boolean bool) {
		if (bool) {
			GrayscaleFilter filter = new GrayscaleFilter();

			BufferedImage tmpImg = new BufferedImage(64, 64, BufferedImage.TRANSLUCENT);
			filter.filter(GameWindow.toBufferedImage(new ImageIcon(getClass().getResource("../textures/btnMove.png")).getImage()), tmpImg);
			setIcon(new ImageIcon(tmpImg));

		} else {
			setIcon(new ImageIcon(getClass().getResource("../textures/btnMove.png")));
		}
	}

}
