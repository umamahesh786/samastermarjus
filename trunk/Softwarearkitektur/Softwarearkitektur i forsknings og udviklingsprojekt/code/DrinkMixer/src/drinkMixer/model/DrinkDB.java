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

package drinkMixer.model;


import drinkMixer.model.drink.comparators.*;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import drinkMixer.model.drink.*;
import drinkMixer.event.*;


/**
 * Database class for DrinkMixer.  This is the main class for the DrinkMixer
 * database. all external access to the DB is done throught this class.
 * why dont we use sql or something? because then the user would have to have
 * some database connectivity, which wont always be the case (ex. palm pilot)
 *
 * @author <A HREF="mailto:haydenml@clarkson.edu">Marshall Hayden</A>
 */
public class DrinkDB
	extends Observable //for use as a model
	implements DrinkListener
{	
	public static final int SAFE_TO_EXIT 		= (1<<0);
	public static final int NOT_SAFE_TO_EXIT	= (1<<1);

	/* the number of sorted drink lists to store */
	protected static final int CACHE_SIZE = 4;
	
	/* points to the LAST USED spot */
	protected int sortPtr;
	protected int searchPtr;
	
	protected boolean saveToDate;
	
	protected String fileName;
	
	/* the cached lists */
	protected CachedList[] cachedSorts;

	/* the cached searches */
	protected CachedList[] cachedSearches;

	/* the drinks */
	protected List drinks;
	
	/* list of all names used at time of db init */
	public Vector glassNames;
	public Vector seasonNames;
	public Vector ingredientNames;
	public Vector nameNames;
	public Vector dateNames;
	public Vector tempNames;
	public Vector typeNames;
	public Vector placeNames;
	
	/**
	 * Creates a DrinkDB.  The static methods <code>createDatabase</code>
	 * and <code>loadDatabase</code> should be used for creating and loading
	 * databases.
	 *
	 * @param	c	A collection of drinks to instantiate the database with.
	 */
	protected DrinkDB(Collection c)
	{
		drinks = new LinkedList(c);

		cachedSorts = new CachedList[CACHE_SIZE];
		cachedSearches = new CachedList[CACHE_SIZE];
		
		clearCache();
		
		Iterator i = drinks.iterator();
		
		while(i.hasNext())
		{
			((Drink)i.next()).addDrinkListener(this);
		}
		
		fillNameLists();
	}

	protected void fillNameLists()
	{
		glassNames = new Vector();
		seasonNames = new Vector();
		ingredientNames = new Vector();
		nameNames = new Vector();
		dateNames = new Vector();
		tempNames = new Vector();
		typeNames = new Vector();
		placeNames = new Vector();

		
		String s;
		Drink d;
		Iterator i = drinks.iterator();
		while(i.hasNext())
		{
			d = (Drink)i.next();
			
			//s = d.getName();
			//nameNames.add(s);
			
			s = d.getGlassType();
			if(!glassNames.contains(s))
			{
				glassNames.add(s);
			}
			
			s = d.getSeason();
			if(!seasonNames.contains(s.toLowerCase()))
			{
				seasonNames.add(s.toLowerCase());
			}
			
			s = d.getTemp();
			if(!tempNames.contains(s.toLowerCase()))
			{
				tempNames.add(s.toLowerCase());
			}
			
			s = d.getType();
			if(!typeNames.contains(s.toLowerCase()))
			{
				typeNames.add(s.toLowerCase());
			}

			//Iterator j = d.getIngredients().iterator();
			//while(j.hasNext())
			//{
			//	s = ((Ingredient)j.next()).getName();
			//	if(!ingredientNames.contains(s.toLowerCase()))
			//	{
			//		ingredientNames.add(s.toLowerCase());
			//	}
			//}

		}
		
		ingredientNames.add("Vodka");
		ingredientNames.add("Gin");
		ingredientNames.add("Scotch");
		ingredientNames.add("Whiskey");
		ingredientNames.add("Brandy");
		ingredientNames.add("Tequila");
		nameNames.add("");
		dateNames.add("");
		placeNames.add("");
	}

	/**
	 * Clears the cached lists.  Use to clear the cache if
	 * the database has been altered.
	 */
	protected void clearCache()
	{
		for(sortPtr = 0; sortPtr < CACHE_SIZE; ++sortPtr)
		{
			cachedSorts[sortPtr] = null;
			cachedSearches[sortPtr] = null;
		}
		
		sortPtr = 0;
		searchPtr = 0;
	}

	/**
	 * Returns a refrence to an active database.
	 *
	 * @param	data	A collection of drinks that should be added to the
	 *					new database.
	 *
	 * @return	A refrence to an active database.
	 */
	public static DrinkDB createDatabase(Collection data)
	{
		DrinkDB db = new DrinkDB(data);

		db.saveToDate = false;
		db.fileName = "SavedDatabase.db";

		return db;
	}

	/**
	 * Saves the database to the specified file.  If <code>fileName</code>
	 * already exists on the system, it will be over written.
	 *
	 * @param	fileName	The file to save to.
	 *
	 * @return	<code>true</code> if saving succeded.
	 */
	public boolean saveDatabase(String fileName)
	{
		this.fileName = fileName;
		
		FileOutputStream ostream = null;
		ObjectOutputStream p = null;
		
		try
		{
			ostream = new FileOutputStream(fileName);
			p = new ObjectOutputStream(ostream);
			p.writeObject(drinks);
			p.flush();
			ostream.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println(fnfe);
			return false;
		}
		catch(IOException ioe)
		{
			System.out.println(ioe);
			return false;
		}
		
		saveToDate = true;
		return true;
	}
	
	/**
	 * Loads a <code>DrinkDB</code> from the specified file.
	 *
	 * @param	fileName	The file to load from.
	 *
	 * @throws	DatabaseRetrievalFailedException
	 *			If there is an error retrieving the database.
	 *
	 * @return	The newly loaded <code>DrinkDB</code>.
	 */
	public static DrinkDB loadDatabase(String fileName)
		throws DatabaseRetrievalFailedException
	{
		FileInputStream istream = null;
		ObjectInputStream p = null;
		List list = null;
		
		try
		{
			istream = new FileInputStream(fileName);
			p = new ObjectInputStream(istream);
			list = (List)p.readObject();
			istream.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println(fnfe);
			throw new DatabaseRetrievalFailedException();
		}
		catch(StreamCorruptedException sce)
		{
			System.out.println(sce);
			throw new DatabaseRetrievalFailedException();
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println(cnfe);
			throw new DatabaseRetrievalFailedException();
		}
		catch(OptionalDataException ode)
		{
			System.out.println(ode);
			throw new DatabaseRetrievalFailedException();
		}
		catch(IOException ioe)
		{
			System.out.println(ioe);
			throw new DatabaseRetrievalFailedException();
		}
		
		DrinkDB db = new DrinkDB(list);
		db.saveToDate = true;
		db.fileName = fileName;
		
		return db;
	}
	
	/**
	 * Closes the database.  If the database has been altered since the last
	 * time it was saved, the user will be prompted for confirmation.
	 *
	 * @param	location	A place for a confirmation dialog.
	 *
	 * @return	One of <code>SAFE_TO_EXIT</code> or
	 *			<code>NOT_SAFE_TO_EXIT</code>
	 */
	public int exit(java.awt.Component location)
	{
		int dialogVal;
		
		if(!saveToDate)
		{
			dialogVal = JOptionPane.showConfirmDialog(location,
							"Save changes to " + fileName + "?");
			
			switch(dialogVal)
			{
				case JOptionPane.YES_OPTION:
					saveDatabase(fileName);
					/* FALLS THROUGH */

				case JOptionPane.NO_OPTION:
					cachedSorts = null;
					cachedSearches = null;
					drinks = null;
					return SAFE_TO_EXIT;
				
				case JOptionPane.CANCEL_OPTION:
					/* do nothing */
					/* FALLS THROUGH */
				
				default:
					return NOT_SAFE_TO_EXIT;
			}
		}
		
		return SAFE_TO_EXIT;
	}

	/**
	 * Returns an unordered List of drinks.
	 *
	 * @return A {@link java.util.List} of {@link Drink}s
	 */
	public List getDrinks()
	{
		return drinks;
	}

	/**
	 * Returns a sorted List of drinks.  The returned List will
	 * be ordered based on the ordering imposed by the comparator specified.
	 *
	 * @param	comparator	Specifies the method of ordering.
	 *
	 * @return	A sorted List of Drinks.
	 *
	 * @see		Drink
	 * @see		drinkMixer.model.drink.comparators.DrinkComparator
	 */
	public List getDrinks(DrinkComparator comparator)
	{
		for(int x = 0; x < CACHE_SIZE; ++x)
		{
			if(cachedSorts[x] != null)
			{
				if(cachedSorts[x].description.equals(comparator))
				{
					return cachedSorts[x].list;
				}
			}
		}
		
		sortPtr++;
		sortPtr %= CACHE_SIZE;

		cachedSorts[sortPtr] = new CachedList(comparator,
												new LinkedList(drinks));

		Collections.sort(cachedSorts[sortPtr].list,comparator);
		
		return cachedSorts[sortPtr].list;
	}

	/**
	 * Finds drinks containing the specified value in the specified field.
	 * The List will be empty if no matching drinks are found.  a search can
	 * be performed on any field for which there is a comparator in the
	 * package drinkMixer.model.drink.comparators.
	 *
	 * @param	fieldSpecifier	The field to search in.
	 * @param	value			The value to look for in the specified field.
	 *
	 * @return	A List containing the results.
	 *
	 * @see Drink
	 */
	public List searchFor(DrinkComparator fieldSpecifier, Object value)
	{
		//for(int x = 0; x < CACHE_SIZE; ++x)
		//{
		//	if(cachedSearches[x] != null)
		//	{
		//		if(cachedSearches[x].description.equals(fieldSpecifier))
		//		{
		//			return cachedSearches[x].list;
		//		}
		//	}
		//}
		
		List results = new LinkedList();
		Iterator i = drinks.iterator();
		Drink d;
		
		while(i.hasNext())
		{
			d = (Drink)i.next();
			if(fieldSpecifier.equals(d, value))
			{
				results.add(d);
			}
		}
		
		//searchPtr++;
		//searchPtr %= CACHE_SIZE;
		
		//cachedSearches[searchPtr] = new CachedList(fieldSpecifier, results);
		//System.out.println("in DB searchFor is returning List of length:");
		//System.out.println(results.size());
		return results;
	}

    /**
	 * Adds the specified Drink to the database.
	 *
	 * @param	drink	The Drink to add.
	 */
	public void addDrink(Drink drink)
	{
		drink.addDrinkListener(this);
		drinks.add(drink);
		fireDBChanged();
	}

	/**
	 * Removes the specified drink from the database.
	 *
	 * @param drink The Drink to remove.
	 */
	public void removeDrink(Drink drink)
	{
		drink.removeDrinkListener(this);
		drinks.remove(drink);
		fireDBChanged();
	}
	
	public void drinkChanged(DrinkEvent e)
	{
		fireDBChanged();
	}

	protected void fireDBChanged()
	{
		clearCache();
		setChanged();
		notifyObservers();
		saveToDate = false;
	}
	
	/**
	 * Signals that there was an error retrieving the database.
	 */
	public static class DatabaseRetrievalFailedException
		extends Exception
	{
	}
}
