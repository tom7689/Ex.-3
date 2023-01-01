package library;

import game.Cell;
import game.Controller;

public class PreLoader
{
	Controller cont;
	SpeciesLibrary lib;

	public PreLoader(Controller parent)
	{
		cont = parent;
		lib = new SpeciesLibrary();
	}

	public void loadPreset(int posX, int posY, String name)
	{

		Species preset = lib.getSpecies(name);
		boolean[][] pattern = preset.getPattern();
		
		for(int y = 0; y < preset.getSizeY(); y++)
		{
			for(int x = 0; x < preset.getSizeX(); x++)
			{
				if(pattern[y][x])
				{
					cont.presetCell((x + posX) % cont.survivalMatrix[0].length, (y + posY) % cont.survivalMatrix.length);
				}
				else
				{
					Cell cellAtPos = cont.survivalMatrix[(y + posY) % cont.survivalMatrix.length][(x + posX) % cont.survivalMatrix[0].length];
					cellAtPos.setNextStatus(0);
					cellAtPos.setColorStatus(null);
					cellAtPos.updateStatus();
				}
			}
		}
	}

}
