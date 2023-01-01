package listeners;

import game.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CellToggleListener implements MouseListener
{
	Controller controller;

	public CellToggleListener(Controller parent)
	{
		controller = parent;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int posX = e.getX();
		int posY = e.getY();

		int tile = controller.getGamegrid().tileSize;

		int cellX = posX / tile;
		int cellY = posY / tile;

		controller.setCell(cellX, cellY);
	}


	@Override
	public void mousePressed(MouseEvent e)
	{
		/**
		 * IGNORE
		 */
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{

		/**
		 * IGNORE
		 */
	}


	@Override
	public void mouseEntered(MouseEvent e)
	{

		/**
		 * IGNORE
		 */
	}


	@Override
	public void mouseExited(MouseEvent e)
	{
		/**
		 * IGNORE
		 */
	}
}
