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
 * A Comparator for ordering drinks by place of origin.
 *
 *@author <A HREF="mailto:haydenml@clarkson.edu">Marshall Hayden</A>
 */
public class PlaceComparator
	extends DrinkComparator
{
	public int compare(Object o1, Object o2)
	{
		return ((Drink)o1).getOriginPlace().compareTo(
												((Drink)o2).getOriginPlace());
	}

	/**
	 * See <code>DrinkComparator.equals</code> for a
	 * complete description.  <code>val</code> should be of type
	 * String for this method, and must be lexographically equal to
	 * the name of the drink for true to be returned.
	 * This may be changed at a later date to handle regular expressions.
	 *
	 */
	public boolean equals(Drink d, Object val)
	{
		//return d.getName().equals(val);
		return stringsCompare((String)val, d.getOriginPlace());
	}

	public String getName()
	{
		return "Origin";
	}
}

