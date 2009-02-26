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


import drinkMixer.model.drink.Drink;


/**
 * A Comparator for ordering drinks by name.
 *
 *@author <A HREF="mailto:haydenml@clarkson.edu">Marshall Hayden</A>
 */
public class DateComparator
	extends DrinkComparator
{
	public int compare(Object o1, Object o2)
	{
		int a = ((Drink)o1).getYear();
		int b = ((Drink)o2).getYear();

		if(a > b)
		{
			return 1;
		}
		
		if(a < b)
		{
			return -1;
		}
		
		return 0;
	}

	/**
	 * See <code>DrinkComparator.equals</code> for a
	 * complete description.  <code>val</code> should be of type
	 * Integer for this method, and must have an int value equal to 
	 * the year of the drink for true to be returned.
	 * This may be changed at a later date to handle regular expressions.
	 *
	 */
	public boolean equals(Drink d, Object val)
	{
		if(val instanceof Integer)
		{
			return(d.getYear() == ((Integer)val).intValue());
		}

		if(val instanceof String)
		{
			return(new Integer(d.getYear()).equals((String)val));
		}
		
		return false;
	}

	public String getName()
	{
		return "Date";
	}
}

