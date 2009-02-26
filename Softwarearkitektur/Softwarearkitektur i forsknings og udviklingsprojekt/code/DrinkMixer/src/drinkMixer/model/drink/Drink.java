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

package drinkMixer.model.drink;


import java.io.*;
import java.util.*;
import drinkMixer.event.*;

/**
 * Used to store information about a drink.
 *
 * @author Mike Akers.
 */
public class Drink
	implements Serializable, Cloneable
{	
	protected String name = "";
	protected Vector ingredients;
	protected String directions = "";
	protected String originPlace = "";
	protected GregorianCalendar originDate;
	protected String glassType = "";
	protected int version = 0;
	protected String temperature = "";
	protected String season = "";
	protected String type= ""; 
	transient protected Vector listeners;

	private void readObject(ObjectInputStream stream)
		throws IOException, ClassNotFoundException
	{
		stream.defaultReadObject();
		listeners = new Vector();
	}
	
	{
		listeners = new Vector();
	}
	
	/**
	 * Constructs an empty Drink.
	 */
	public Drink()
	{
		ingredients = new Vector();
	}

	/**
	 * Constructs a drink with the name <code>name</code>.
	 *
	 * @param name the name of the new drink.
	 */
	public Drink(String name)
	{
		this.name = name;
		ingredients = new Vector();
	}
	
	/**
	 * Constructs a drink with the name set to <code>name</code> 
	 * and the ingredients set to <code>ingredients</code>.
	 *
	 * @param name The name of the new drink.
	 * @param ingredients The ingredients.
	 */
	public Drink(String name, Vector ingredients)
	{
		this.name = name;
		this.ingredients = ingredients;
	}
	/**
	 * Constructs a drink with the name set to <code>name</code> 
	 * and the ingredients set to <code>ingredients</code>.
	 *
	 * @param name The name of the new drink.
	 * @param ingredients The ingredients.
	 * @param directions The directions.
	 * @param originPlace The place of origin.
	 * @param originDate The date of origin.
	 * @param glasstype The glasstype.
	 * @param version The drinks version.
	 */
	public Drink(String name, Vector ingredients, String directions,
		String originPlace, GregorianCalendar originDate, 
		String glassType, int version)
	{
		this.name = name;
		Iterator i = ingredients.iterator();
		while(i.hasNext())
		{
			if(!(i.next() instanceof Ingredient))
			{
				throw new BossRetardedException();
			}
		}
		this.ingredients = ingredients;
		this.directions = directions;
		this.originPlace = originPlace;
		this.originDate = originDate;
		this.glassType = glassType;
		this.version = version;
	}

	/**
	 * Constructs a drink with the name set to <code>name</code> 
	 * and the ingredients set to <code>ingredients</code>.
	 *
	 * @param name The name of the new drink.
	 * @param ingredients The ingredients.
	 * @param directions The directions.
	 * @param originPlace The place of origin.
	 * @param originDate The date of origin.
	 * @param glasstype The glasstype.
	 * @param version The drinks version.
	 * @param temp The prefered serving temperature.
	 * @param season The season of the drink.
	 */

	public Drink(String name, Vector ingredients, String directions,
		String originPlace, GregorianCalendar originDate, 
		String glassType, int version, String temp, String season)
	{
		this(name, ingredients, directions, originPlace, originDate,
			glassType, version);

		this.temperature = temp;
		this.season = season;
	}

	/**
	 * Constructs a drink with the name set to <code>name</code> 
	 * and the ingredients set to <code>ingredients</code>.
	 *
	 * @param name The name of the new drink.
	 * @param ingredients The ingredients.
	 * @param directions The directions.
	 * @param originPlace The place of origin.
	 * @param originDate The date of origin.
	 * @param glasstype The glasstype.
	 * @param version The drinks version.
	 * @param temp The prefered serving temperature.
	 * @param season The season of the drink.
	 * @param type The type of the drink.
	 */

	public Drink(String name, Vector ingredients, String directions,
		String originPlace, GregorianCalendar originDate, 
		String glassType, int version, String temp, String season, String type)
	{
		this(name, ingredients, directions, originPlace, originDate,
			glassType, version);

		this.temperature = temp;
		this.season = season;
		this.type = type;
	}

	public Drink(Drink d)
	{
		ingredients = new Vector();
		this.name = new String(d.name);
	
		Iterator i = d.ingredients.iterator();

		while(i.hasNext())
		{
			ingredients.add(new Ingredient(i.next().toString()));
		}
			
		this.originPlace = new String(d.originPlace);
		this.originDate = new GregorianCalendar(d.getYear(), 1,1);
		this.glassType = new String(d.glassType);
		this.version = d.version;
		this.temperature = new String(d.temperature);
		this.season = new String(d.season);
		this.type = new String(d.type);
		this.directions = new String(d.directions);
	}

	/**
	 * Converts the drink into a string.
	 * If any of the varialbes are null, they aren't added to the string.
	 *
	 * @return The drink as a string.
	 */
	public String toString()
	{	
		String s = new String();
		
		try
		{
			s += "Name: " + name + "\n";
		}
		catch(NullPointerException npe)
		{
			System.out.println("Warning: Drink without name!" + npe);
		}

		if(!type.equals(""))
		{
			s += "Type: " + type + "\n";
		}
		
		if(!glassType.equals(""))
		{
			s += "Glass type: " + glassType + "\n";
		}
		
		if(!originPlace.equals(""));
		{
				s += "Place of origin: " + originPlace + "\n";
		}

		if(originDate != null)
		{
			s += "Year of origin: " 
				+ originDate.get(Calendar.YEAR)
				+ "\n";
		}
		
		if(!temperature.equals(""))
		{
			s += "Serving Temeperature: " + temperature + "\n";
		}

		if(!season.equals(""))
		{
			s += "Season: " + season + "\n";
		}

		if(version != 0)
		{
			s += "Version: " + version + "\n";
		}

		s += "\nIngredients:\n";

		try
		{
			Enumeration ingredientList = ingredients.elements();	
			Ingredient i;
			while(ingredientList.hasMoreElements())
			{
				i = (Ingredient)ingredientList.nextElement();
				s += "\t" + i.toString();
				s +="\n";
			}
		}
		catch(NullPointerException npe)
		{
			System.out.println("Warning: Drink without ingredients!" + npe);
		}

		try
		{
			s += "\nDirections:\n" + directions + "\n";
		}
		catch(NullPointerException npe)
		{
			System.out.println("Warning: Dirink without Directions!" + npe);
		}
	
		return s;
	}

	/**
	 * Adds an ingredient to the Ingredient vector.
	 *
	 * @param newIngredient The new ingredient to be added.
	 */
	public void addIngredient(Ingredient newIngredient)
	{
		ingredients.addElement(newIngredient);
		fireDrinkChanged();
	}

	/**
	 * Removes an ingredient from the Ingredints vector.
	 *
	 * @param ing The ingredient to remove.
	 */
	public void removeIngredient(Ingredient ing)
	{
		ingredients.remove(ing);
		fireDrinkChanged();

	}

	public void setIngredients(Collection newIngredients)
	{
		ingredients = new Vector(newIngredients);
	}
	
	/**
	 * Sets the name of the drink
	 * @param name The new name of the drink
	 */
	public void setName(String name)
	{
		this.name = name;
		fireDrinkChanged();
	}

	/**
	 * Sets the directions string to a new value.
	 *
	 * @param dirs The new directions.
	 */
	public void setDirections(String dirs)
	{
		directions = dirs;
		fireDrinkChanged();
	}
	
	/**
	 * Sets the place of origin to a new value.
	 *
	 * @param place the new place of origin.
	 */
	public void setOriginPlace(String place)
	{
		originPlace = place;
		fireDrinkChanged();
	}

	/**
	 * Sets the date of origin to a new value.
	 *
	 * @param date The new date.
	 */
	public void setOriginDate(GregorianCalendar date)
	{
		originDate = date;
		fireDrinkChanged();
	}
	
	/**
	 * Sets the type of glass to a new value.
	 *
	 * @param glass The new glass type.
	 */
	public void setGlassType(String glass)
	{
		glassType = glass;
		fireDrinkChanged();
	}
	
	/**
	 * Sets the version number to a new value.
	 *
	 * @param ver The new version.
	 */
	public void setVersion(int ver)
	{
		version = ver;
		fireDrinkChanged();
	}

	/**
	 * Sets the temperature of the drink.
	 *
	 * @param temp The new temperature.
	 */
	public void setTemp(String temp)
	{
		temperature = temp;
		fireDrinkChanged();
	}
	
	/**
	 * Sets the season of the drink.
	 *
	 * @param season The new season.
	 */
	public void setSeason(String season)
	{
		this.season = season;
		fireDrinkChanged();
	}

	/**
	 * Sets the season of the drink.
	 *
	 * @param season The new season.
	 */
	public void setType(String type)
	{
		this.type = type;
		fireDrinkChanged();
	}
	
	/**
	 * Returns the name of the drink
	 *
	 * @return The name of the drink
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the ingedients of the drink
	 * @return a vector of ingredents.
	 */	
	public Vector getIngredients()
	{
		return ingredients;
	}

	/**
	 * Returns the directions for the drink
	 * @return a String containing the directions.
	 */
	public String getDirections()
	{
		return directions;
	}

	/**
	 * Returns the place of origin for the drink
	 * @return a String containing the place of origin.
	 */
	public String getOriginPlace()
	{
		return originPlace;
	}

	/**
	 * Returns the date of origin of the drink. 
	 * @return a GregorianCalendar containing the origin date of the drink.
	 */	
	public GregorianCalendar getOriginDate()
	{
		return originDate;
	}

	/**
	 * Returns the year of origin of the drink.
	 * You should really be using the <code>getOriginDate</code> method and 
	 * doing a <code>get(Calendar.YEAR)</code> on the result.
	 *
	 * @return an int containing the year of origin of the Drink.
	 */	
	public int getYear()
	{
		try
		{
			return originDate.get(Calendar.YEAR);
		}
		catch(NullPointerException npe)
		{
			return -1;
		}
	}

	/**
	 * Returns the season of the drink
	 * @return a String containing the season of the drink.
	 */		
	public String getSeason()
	{
		return season;
	}

	/**
	 * Returns the prefered teperature of the drink
	 * @return a String containing the temperature of the drink.
	 */	
	public String getTemp()
	{
		return temperature;
	}

	/**
	 * Returns the glasstype of the drink
	 * @return a String containing the glass type.
	 */	
	public String getGlassType()
	{
		return glassType;
	}

	/**
	 * Returns the version of the drink
	 * @return an int containing the version.
	 */	
	public int getVersion()
	{
		return version;
	}

	/**
	 * Returns the type of the drink
	 * @return The type of the drink.
	 */	
	public String getType()
	{
		return type;
	}

	/**
	 * Adds a DrinkListener.
	 *
	 * @param dl The DrinkListener to add.
	 */
	public void addDrinkListener(DrinkListener dl)
	{
		listeners.add(dl);
	}

	/**
	 * Removes a DrinkListener.
	 *
	 * @param dl The DrinkListener to remove
	 */
	public void removeDrinkListener(DrinkListener dl)
	{
		listeners.remove(dl);
	}
		
	protected void fireDrinkChanged()
	{
		DrinkEvent de = new DrinkEvent(this);
		Iterator i = listeners.iterator();
		DrinkListener l;
		
		while(i.hasNext())
		{
			l = (DrinkListener)i.next();
			l.drinkChanged(de);
		}
	}

	/**
	 * Thrown whenever boss is retarded. 
	 */
	public class BossRetardedException
		extends IllegalArgumentException
	{
	}
}
