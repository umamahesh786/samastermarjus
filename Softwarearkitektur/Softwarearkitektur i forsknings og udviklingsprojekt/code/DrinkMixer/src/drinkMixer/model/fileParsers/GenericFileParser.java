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
import drinkMixer.model.drink.*;
import drinkMixer.model.*;


public class GenericFileParser 
	implements FileParser
{
	protected boolean isDone;
	protected String fileName;
	protected LinkedList drinks;
	
	public GenericFileParser(String file)
	{
		this.fileName = file;
		isDone = false;
		drinks = new LinkedList();
		ParseThread t = new ParseThread();
		
		t.start();

	}

	public Collection getDrinks()
	{
		return drinks;
	}
	
	public boolean isDone()
	{
		return isDone;
	}

	class ParseThread extends Thread
	{
		public ParseThread()
		{
		}

		public void run()
		{
			Drink theDrink;
			String aIngredient;
			StringTokenizer lineTokenizer = null;
			StringTokenizer ingredientsTokenizer = null;
			GregorianCalendar newDate;
			BufferedReader file = null;
			
			try
			{
				file = new BufferedReader(new FileReader(fileName));
			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println(fnfe);
			}
			
			try
			{
				while(file.ready())
				{
					try
					{
					lineTokenizer = new StringTokenizer(file.readLine(), ":");
					}
					catch(IOException ioe)
					{
						System.out.println(ioe);
					}
						
					theDrink = new Drink();
				
					String bar = new String((String)lineTokenizer.nextElement());	
					theDrink.setName(bar);
					
				
					ingredientsTokenizer = new StringTokenizer(new String(
										(String)lineTokenizer.nextElement()), ",");

					try
					{
						while(ingredientsTokenizer.hasMoreElements())
						{
							Ingredient newIngredient = new Ingredient(ingredientsTokenizer.nextToken());
							theDrink.addIngredient(newIngredient);
						}
					}
					catch(NoSuchElementException nsee)
					{
						System.out.println(nsee);
					}
			
				
					String baz = new String((String)lineTokenizer.nextElement());
					theDrink.setDirections(baz);
				
					theDrink.setOriginPlace(new String((String)lineTokenizer.nextElement()));
				
				
					String str = new String((String)lineTokenizer.nextElement());
					newDate = new GregorianCalendar(Integer.parseInt(str),1,1);
					theDrink.setOriginDate(newDate);
				
					theDrink.setGlassType(new String((String)lineTokenizer.nextElement()));
					
					theDrink.setVersion(Integer.parseInt(new String(
								(String)lineTokenizer.nextElement())));

	
					drinks.add(theDrink);

				}
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
			}
			catch(NoSuchElementException nsee)
			{
			//	System.out.println(nsee);

			}

			isDone = true;
			throw new ThreadDeath();

		}
	}
}
