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


import java.util.*;

/**
 *
 */
public final class DrinkComparators
{
	public static final DrinkComparator NAME	= new NameComparator();
	public static final DrinkComparator GLASS	= new GlassComparator();
	public static final DrinkComparator SEASON	= new SeasonComparator();
	public static final DrinkComparator TEMP 	= new TempComparator();
	public static final DrinkComparator DATE	= new DateComparator();
	public static final DrinkComparator ING		= new IngredientComparator();
	public static final DrinkComparator PLACE	= new PlaceComparator();
	public static final DrinkComparator TYPE	= new TypeComparator();

	
	public static final DrinkComparator[] getComparators()
	{
		DrinkComparator[] array = new DrinkComparator[8];

		array[0] = NAME;
		array[1] = GLASS;
		array[2] = SEASON;
		array[3] = TEMP;
		array[4] = DATE;
		array[5] = ING;
		array[6] = PLACE;
		array[7] = TYPE;
		
		return array;
	}
}
