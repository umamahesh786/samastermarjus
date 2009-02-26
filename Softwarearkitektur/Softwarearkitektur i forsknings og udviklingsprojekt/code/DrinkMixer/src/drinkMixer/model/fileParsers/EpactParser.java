/* DrinkMixer -- A database program for mixed drinks.
 * Copyright (C) 1999 Michael C. Akers, Marshall L. Hayden, Joseph G. Husband, 
 * Kevin E. Makles.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package drinkMixer.model.fileParsers;


import java.io.*;
import java.util.*;
import drinkMixer.model.*;

public class EpactParser
{

/*
	protected Hashtable drinks;
	protected String theString;
	
	public static Hashtable parseFile(String fileName)
	{
		EpactParser parser = new EpactParser(fileName);

		return parser.getDrinks();
	}
	
	protected Hashtable getDrinks()
	{
		return drinks;
	}

	protected EpactParser(String fileName)
	{
		int count = 0;
		drinks = new Hashtable();
		FileReader reader = null;
		theString = "";
		
		try
		{
			reader = new FileReader(fileName);
			int ch = 0;
			while(ch != -1)
			{
				ch = reader.read();
				if(ch != -1)
				{
					theString += (char)ch;
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	
		StringTokenizer tokenizer = new StringTokenizer(theString, "\n", true);
		String t1;
		int state = -1; //before title, before ingr. before desc.
		Drink d = null;
		
		while(tokenizer.hasMoreElements())
		{
			t1 = tokenizer.nextToken();
			if(t1.equals("\n"))
			{
				t1 = tokenizer.nextToken();
				if(t1.equals("\n"))
				{
					++state;
					state%=3;
					switch(state)
					{
						case 0:
							//System.out.println("this is a Drink: ");
							if(tokenizer.hasMoreElements())
							{
								count++;
								t1 = tokenizer.nextToken();
							}
							else
							{
								state = 3;
							}
							break;
						case 1:
							//System.out.println("these are the Ingredients: ");
							break;
						case 2:
							//System.out.println("this is the description: ");
							break;
						default:
							//System.err.println("woops!!!!!");
					}
				}
			}
			
			switch(state)
			{
				case 0:
					d = new Drink(t1);
					drinks.put(t1, d);
					break;
				case 1:
					if(!t1.equals("\n"))
					{
						d.addIngredient(new Ingredient(1.5, "unit", "name"));
					}
					break;
				case 2:
					d.setDirections(t1);
					break;
				case 3:
					break;
				default:
					//System.err.println("Really woops!!!");
			}
		}
		System.out.println(count);
	}
*/
}
