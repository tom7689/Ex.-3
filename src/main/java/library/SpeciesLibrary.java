package library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 3sander on 14.06.17.
 */
public class SpeciesLibrary 
{
	protected HashMap<String, Species> library;

	/**
	 * Library which holds all the available Species.
	 */
	public SpeciesLibrary()
	{
		library = new HashMap<>();
		localSetup();
	}
	
	public Species getSpecies(String name)
	{
		return library.get(name);
	}

	/**
	 * initializes Species on the local system and adds them to the library
	 */
	private void localSetup()
	{
		library.put("Block", new Species("Block", "very edgy", "2o$2o", 2, 2));
		library.put("Glider", new Species("Glider", "moving", "bob$2bo$3o", 3, 3));
		library.put("Blinker", new Species("Blinker", "epilepsy inc", "o$o$o", 1, 3));
		library.put("Car", new Species("Car", "Vroom!", "bo6b$o5bob$o5bob$5obob2$8b$4b2o2b$2bo4bo$bo6b$bo5bo$b6o", 8, 10)); //8b is not from original code
		library.put("Seal", new Species("Seal", "A cute giant seal", "2b2o30b$b3ob3o26b$3bo3b2o25b$2ob2o2bo2bobo21b$o6bob4o21b$6b2obo2bo21b$o4b2o3bob2o20b$2o8bobo2bo18b$o2b5ob3o4bo17b$bo9bo4bo17b$b2obo2bo3bo3bo18b$2bo9b2o20b$9b2o2b2o4bo14b$2bo3b2obo4b3o2bo14b$3b2obob5o5bob2o12b$7b2ob2ob2ob2o2b3o11b$17bobo4bo9b$8b2o4bobo3b6o8b$13bo7b2o11b$13bo3bo16b$13bo4bo3b3o6bo2b$14b5o3b2ob2o2b2ob2o$17b2obo2bob2o2bo4b$16b2o5bo4bo2b3o$15b3obobobobo8b$22bo2bo8b$18bobo13b$19bo14b2$34b$21b3o10b$21b3o10b2$34b$20b2o12b$20b2o12b$21bo", 34 ,35));
		library.put("Meth", new Species("Meth", "a methuselah", "o10bo$b4o6bo$2b2o7bo$10bob$8bobo", 12 , 5));
		library.put("Foreandback", new Species("foreandback", "small Ossizilator", "2ob2o2b$2obobob$6bo$3ob3o$o6b$bobob2o$2b2ob2o", 7, 7));
		library.put("Butterfly", new Species("Butterfly", "Fabulous horizontal spaceship", "5o13b$o4bo7b2o3b$o11b2ob3o$bo9b2ob4o$3b2o3b2ob2o2b2ob$5bo4bo2bo4b$6bobobobo5b$7bo10b$7bo10b$6bobobobo5b$5bo4bo2bo4b$3b2o3b2ob2o2b2ob$bo9b2ob4o$o11b2ob3o$o4bo7b2o3b$5o", 18, 16));
		library.put("Whynot", new Species("Whynot", "Why not Zoidberg?", "3bo3b$3bobob$bo5b$ob5o$bo5b$3bobob$3bo",7 ,7));
		library.put("Shipmaker", new Species("Shipmaker", "Makes Ships while burning", "39b2o$38bobo$37bo3b$36bo4b$35bo5b$34bo6b$33bo7b$32bo8b$31bo9b$30bo10b$29bo11b$28bo12b$27bo13b$26bo14b$25bo15b$24bo16b$23bo17b$22bo18b$21bo19b$20bo20b$19bo21b$18bo22b$17bo23b$16bo24b$15bo25b$14bo26b$13bo27b$12bo28b$11bo29b$10bo30b$9bo31b$8bo32b$7bo33b$6bo34b$6o35b$5bo35b$5bo35b$5bo35b$5bo35b$5bo", 41, 40));
		library.put("Glidergun", new Species("Glidergun", "GG", "24bo11b$22bobo11b$12b2o6b2o12b2o$11bo3bo4b2o12b2o$2o8bo5bo3b2o14b$2o8bo3bob2o4bobo11b$10bo5bo7bo11b$11bo3bo20b$12b2o", 36, 9));
	}

	/**
	 * UNUSED:
	 * setup for the webscraping
	 */
	private void scrapeSetup()
	{		
		//webscraping
		String baseUrl = "http://conwaylife.appspot.com/";
		String categories[] = new String[12];
		categories[0] = "abc";
		categories[1] = "def";
		categories[2] = "ghi";
		categories[3] = "jkl";
		categories[4] = "mno";
		categories[5] = "pqr";
		categories[6] = "stu";
		categories[7] = "vwx";
		categories[8] = "yz";
		categories[9] = "123";
		categories[10] = "456";
		categories[11] = "789";
		
		List<String> patternNames;

		String previous = "initial";
		
		for(int i = 0; i < 12; i++)
		{
			patternNames = scrapeNames(baseUrl + "library/" + categories[i]);
			for (Iterator iterator = patternNames.iterator(); iterator.hasNext();) 
			{
				String string = (String) iterator.next();
				Species species = scrapePattern(baseUrl + "pattern/" + string);
				library.put(species.getName(), species);
				if(string.contains(previous))
				{
					break;
				}
				if(library.size() > 100)
				{
					//temporary breakout
					break;
				}
				previous = (String) iterator.next();
			}
		}	
	}

	/**
	 * helper method which loads a species pattern from given html code
	 * @param patternName
	 * @return
	 */
	private Species scrapePattern(String patternName)
	{
		Species species;
		String name = "";
		String description = "";
		String preset = "";
		String sizeXY = "";
		String[] dimensions;
		
		String source = getSource(patternName);
		
		Pattern pattern = Pattern.compile("<title>The Game of Life | (.*?)</title>");
		Matcher matcher = pattern.matcher(source);
		if (matcher.find())
		{
			name = matcher.group(1);
		}
		
		pattern = Pattern.compile("content=\"(.*?)\">");
		matcher = pattern.matcher(source);
		if (matcher.find())
		{
			name = matcher.group(1);
		}
		
		pattern = Pattern.compile("\"data\": \"(.*?)!\"");
		matcher = pattern.matcher(source);
		if (matcher.find())
		{
			name = matcher.group(1);
		}
		
		pattern = Pattern.compile("\"size\": \"(.*?)\"};");
		matcher = pattern.matcher(source);
		if (matcher.find())
		{
			sizeXY = matcher.group(1);
		}
		
		dimensions = sizeXY.split("x");
		species = new Species(name, description, preset, Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1]));
		return species;		
	}

	/**
	 * scrapes the names of the respective species
	 * @param category
	 * @return
	 */
	private List<String> scrapeNames(String category)
	{
		String source = getSource(category);
		List<String> nameList = new ArrayList<>();
		
		Pattern pattern = Pattern.compile("\"/pattern/(.*?)\"");
		Matcher matcher = pattern.matcher(source);
		while (matcher.find())
		{
			nameList.add(matcher.group(1));
		}
		
		return nameList;
	}

	/**
	 * loads the different subpages for the respective species
	 * @param inputUrl
	 * @return
	 */
	private String getSource(String inputUrl)
	{
		URL url;
		String source = null;
		
		try 
		{
			url = new URL(inputUrl);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) 
			{
			  response.append(inputLine + "\n");
			}

			in.close();
			
			source = response.toString();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return source;
	}

}
