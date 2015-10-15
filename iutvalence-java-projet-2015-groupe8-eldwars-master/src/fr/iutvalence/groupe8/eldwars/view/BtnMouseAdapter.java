package fr.iutvalence.groupe8.eldwars.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import fr.iutvalence.groupe8.eldwars.Pos;
import fr.iutvalence.groupe8.eldwars.media.SoundEngine;
import fr.iutvalence.groupe8.eldwars.media.SoundType;
import fr.iutvalence.groupe8.eldwars.view.actions.Action;
import fr.iutvalence.groupe8.eldwars.view.actions.ActionType;
import fr.iutvalence.groupe8.eldwars.view.actions.ActionsArea;

/**
 * The BtnMouseAdapter class is used to listen mouse clicks.
 * 
 * @author Nicolas
 * @version 20150608
 *
 */
public class BtnMouseAdapter extends MouseAdapter
{

	/**
	 * This event will be performed when clicking on an Action button.
	 */
	public void mousePressed(MouseEvent e)
	{
		Action act = (Action) e.getSource();

		ActionsArea aa = (ActionsArea) act.getParent().getParent().getParent();
		
		if (act.getType() == ActionType.NEXT_ROUND || aa.getUnitSelected() != ActionsArea.SELECTION_NONE)
		{
			SoundEngine.play(SoundType.CLICK2);
			GameWindow gw = (GameWindow) aa.getParent().getParent().getParent()
					.getParent().getParent().getParent();
			
			if (act.getType() == ActionType.RECRUIT && aa.getUnitSelected() == ActionsArea.SELECTION_COMMANDER)
				gw.setLastUsedAction(act.getType());
			else if (act.getType() != ActionType.RECRUIT)
				gw.setLastUsedAction(act.getType());
		} else {
			SoundEngine.play(SoundType.ERROR);
		}
	}
}
