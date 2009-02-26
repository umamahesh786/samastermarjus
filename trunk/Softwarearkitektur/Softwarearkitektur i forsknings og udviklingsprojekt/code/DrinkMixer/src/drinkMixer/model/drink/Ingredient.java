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


import drinkMixer.model.Rational;


/**
 * The Ingredient class stores information about the individual ingredients 
 * that make up a drink.
 */
public class Ingredient
	implements java.io.Serializable
{
	
	protected int unit;
	protected Rational amount;
	protected String name;
	
	/**
	 * Constructs an Ingredient with amount set to <code>amount</code>, 
	 * unit set to <code>unit</code> and name set to <code>name</code>.
	 */
	public Ingredient(Rational amount, int unit,  String name)
	{
		this.unit = unit;
		this.amount = amount;
		this.name = name;
	}

	/**
	 * Takes a String and makes a new Ingredient out of it.
	 * The string must be of the form: w n/d unit name, where
	 * w is a whole number (optional if its only a fraction),
	 * n and d make up a fraction (optional if its only a whole
	 * amount, unit is the unit of the ingredient, and name is 
	 * the name of the ingredient.<p>
	 *
	 * If the string is not in that form the behavior will be 
	 * <i>very unpredictable</i>.
	 *
	 * @param String the new ingredient in a string.
	 */
	public Ingredient(String ing)
	{
		int firstSlash;
		int firstSpace;
		int startOfText = 0;
		int secondSpace;
		int spaceAfterUnit;
			
		int w = 0;
		int n = 0;
		int d = 0;
		
		String whole = null;
		String numer = null;
		String denom = null;
		String unitStr = null;
		
		ing = ing.trim();

		/* if the first character is a digit */
		if(Character.isDigit(ing.charAt(0)))
		{
			firstSlash = ing.indexOf("/");
			firstSpace = ing.indexOf(" ");
			
			if(firstSpace == -1)
			{
			}
			else
			{
				/* its just a whole # */
				if(firstSlash == -1)
 				{
					whole = new String(ing.substring(0, firstSpace));
		 			startOfText = firstSpace + 1;
					numer = new String("0");
					denom = new String("1");
				}
				
				/* its a compound # */
				else if(firstSpace < firstSlash)
				{
					whole = new String(ing.substring(0, firstSpace));
					
					numer = new String(ing.substring(
									firstSpace + 1, firstSlash));

					secondSpace = ing.indexOf(" ", firstSpace + 1);

					denom = new String(ing.substring(
										firstSlash + 1, secondSpace));

					startOfText = secondSpace + 1;
				}

				/* its just a fraction */
				else if(firstSlash < firstSpace)
				{
					numer = new String(ing.substring(
										0, firstSlash));
						
					denom = new String(ing.substring(
										firstSlash + 1, firstSpace));

					startOfText = firstSpace + 1;
				}
			}
		}
		else //the first char is not a digit
		{
			startOfText = 0;
		}
		
		//ok, startOfText should be at the start of the unit.
			
		spaceAfterUnit = ing.indexOf(" ", startOfText);
	
		if(spaceAfterUnit != -1)
		{
			unitStr = new String(ing.substring(
								startOfText, spaceAfterUnit));
			name = new String(ing.substring(spaceAfterUnit));
		}
		else
		{
			unitStr = "";
			name = new String(ing.substring(startOfText));
		}

		try
		{
			unit = Units.toInt(unitStr);
		}
		catch(IllegalArgumentException iae)
		{
			name = new String(ing.substring(startOfText));
		}

		
		
	
		if(whole == null)
		{
			whole = "0";
			if(numer == null)
			{
				numer = "0";
				denom = "1";
			}
		}
		try
		{
			w = Integer.parseInt(whole);
			n = Integer.parseInt(numer);
			d = Integer.parseInt(denom);
		}
		catch(NumberFormatException nfe)
		{
			System.out.println(nfe);
		}

		if(w > 0)
		{
			n += w * d;
		}

		amount = new Rational(n,d);

		StringBuffer sb = new StringBuffer(name);

		while(sb.charAt(0) == ' ')
		{
			sb.deleteCharAt(0);
		}

		while(sb.charAt(sb.length() - 1) == ' ')
		{
			sb.deleteCharAt(sb.length() - 1);
		}
		
		name = sb.toString();
	}

	
	/**
	 * Converts the ingredient to a string.
	 * 
	 * @return The ingredient as a string.
	 */
	public String toString()
	{
		if(amount.toString().equals("NaN"))
		{
			if(unit == 0)
			{
				return name;
			}
			else
			{
				return new String(unit + " " + name).trim();
			}
		}
		else
		{
			if(unit == 0)
			{
				return new String(amount + " " + name).trim();
			}
			return new String(amount + " " + Units.toString(unit) + " " 
																+ name).trim();
		}
	}
	
	/**
	 * Returns the amount of the ingredient.
	 */
	public Rational getAmount()
	{
		return amount;
	}

	/**
	 * Returns the unit of the ingredient.
	 */
	public int getUnit()
	{
		return unit;
	}
	
	/**
	 * Returns the name of the ingredient.
	 */
	public String getName()
	{
		return name;
	}

	public boolean equals(Object o)
	{
		if(o instanceof Ingredient)
		{
			Ingredient i = (Ingredient)o;
			if(name.equals(i.name)
				&& unit == i.unit
				&& amount.equals(i.amount))
			{
				return true;
			}
		}

		return false;
	}

}
