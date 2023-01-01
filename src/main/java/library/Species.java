package library;

/**
 * Created by 3sander on 14.06.17.
 */
public class Species 
{
	private String name;
	private String description;
	private boolean pattern[][];
	private int x;
	private int y;

	/**
	 * A Species is a special Pattern on the Grid which has unique behaviours
	 * @param n Name
	 * @param d Description
	 * @param p PatternString
	 */
	public Species(String n, String d, String p, int sizeX, int sizeY)
	{
		name = n;
		description = d;
		x = sizeX;
		y = sizeY;
		
		String lines[] = p.split("\\$");
		
		pattern = new boolean[y][x];
		
		for (int i = 0; i < y; i++) 
		{
			pattern[i] = convertLine(lines[i]);
		}
	}

	/**
	 * returns the horizontal size of the Species
	 * @return sizeX
	 */
	public int getSizeX()
	{
		return x;
	}

	/**
	 * returns the vertical size of the Species
	 * @return sizeY
	 */
	public int getSizeY()
	{
		return y;
	}

	/**
	 * returns the name of the Species
	 * @return Name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * returns the description
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * pattern as boolean array
	 * @return pattern-array
	 */
	public boolean[][] getPattern()
	{
		return pattern;
	}


	/**
	 * helper method for converting the GOL pattern into a boolean array
	 * @param line
	 * @return
	 */
	private boolean[] convertLine(String line)
	{
		boolean convertedLine[];
		convertedLine = new boolean[x];
		
		int prefix = 0;
		int arrayIndex = 0;
		
		for(int i = 0; i < line.length(); i++)
		{
			if (line.charAt(i) == 'o')
			{
				for(int x = 0; x <Math.max(prefix, 1); x++)
				{
					convertedLine[arrayIndex] = true;
					arrayIndex++;
				}
				prefix = 0;
			}
			else if(line.charAt(i) == 'b')
			{
				arrayIndex = arrayIndex + Math.max(prefix, 1);
				prefix = 0;
			}
			else
			{
				prefix = prefix * 10 + (int)(line.charAt(i) - '0');
			}
		}
		
		return convertedLine;
	}
	
}
