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


import java.util.Comparator;
import drinkMixer.model.drink.Drink;

/**
 * All classes used for comparing or ordering drinks should be extentions
 * of this type.
 *
 *@author <A HREF="mailto:haydenml@clarkson.edu">Marshall Hayden</A>
 */
public abstract class DrinkComparator
	implements Comparator
{
	/**
	 * Checks if specified Drink satisfies desired paramaters.
	 * If the field of the Drink specified by the Comparator class used
	 * has a value that is equivelant to the specified Object, 
	 * then true is returned. DO NOT CONFUSE WITH equals(Object o).
	 *
	 * @param	d	The Drink to check.
	 * @param	val	The value to look for.
	 * 			The type for val should be specified by each instance.
	 *
	 * @return	<code>true</code> if the Drink passes the test.
	 */
	public abstract boolean equals(Drink d, Object val);

	public abstract String getName();

	protected boolean stringsCompare(String forString, String againstString)
	{
		String lfS = forString.toLowerCase();
		String laS = againstString.toLowerCase();
		int index = laS.indexOf(lfS);
		return (index != -1);
	}
}

