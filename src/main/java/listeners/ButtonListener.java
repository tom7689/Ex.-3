package listeners;

import game.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by khopf on 02/06/2017.
 */
public class ButtonListener implements ActionListener
{
	Controller controller;

	public ButtonListener(Controller parent)
	{
		this.controller = parent;
	}

	/**
	 * Invoked when an action occurs.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		controller.stepForward();
	}
	
}
