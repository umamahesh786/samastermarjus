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


package drinkMixer.model.drink.comparators;


import drinkMixer.model.drink.*;


/**
 * A comparator for finding drinks with certain ingredients.
 *
 *@author <A HREF="mailto:haydenml@clarkson.edu">Marshall Hayden</A>
 */
public class IngredientComparator
	extends DrinkComparator
{
	public int compare(Object o1, Object o2)
	{
		/* can anyone think of a correct usage for this? */
		return 0;
	}

	/**
	 * See <code>DrinkComparator.equals</code> for a
	 * complete description.
	 * if <code>val</code> is:		Will evaluate to true if:
	 * 		Ingredient				d contains val.
	 *		String					d contains an Ingredient with name val.
	 *
	 */
	public boolean equals(Drink d, Object val)
	{
		java.util.Iterator i = d.getIngredients().iterator();
		
		if(val instanceof Ingredient)
		{
			while(i.hasNext())
			{
				if(((Ingredient)val).equals(i.next()))
				{
					return true;
				}
			}
			
			return false;
		}

		if(val instanceof String)
		{
			//System.out.println("foo");
			while(i.hasNext())
			{
				Ingredient ing = (Ingredient)i.next();
				String str = (String)val;
				
				//System.out.println("comparing " + ing.getName() + " to " + str);
				if(stringsCompare(str, ing.getName()))
				{
					return true;
				}
				
			}
			
			return false;
		}

		return false;
	}

	public String getName()
	{
		return "Ingredient";
	}
}

