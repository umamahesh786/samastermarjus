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


/**
 * The acceptable units for DrinkMixer.  If its not defined in this class,
 * dont use it, or add it to the class first!  if you think something is 
 * wrong, look at the tables at the top of the class.  thats where the
 * info comes from.
 *
 * @author <A HREF="mailto:haydenml@clarkson.edu">Marshall Hayden</A>
 */
public class Units
{

///////////////////////////////////////////////////////
//this was used to generate constants below
///////////////////////////////////////////////////////
//
//name			abbrev		standard			metric
//Dash, Splash                  1/32 oz            0.9 ml
//Teaspoon        tsp           1/8 oz             3.7 ml
//Tablespoon      tbsp          3/8 oz            11.1 ml
//Pony                          1 oz              29.5 ml
//Jigger          jgr           1 1/2 oz          44.5 ml
//Wineglass       wgls          4 oz              119  ml
//Split           splt          6 oz              177  ml
//Cup             C             8 oz              247  ml
//
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////


///////////////////////////////////////////////////////
//this is currently unused, but looks helpful
///////////////////////////////////////////////////////
//Metric        Fluid Oz       Bottles/Case
// 50  ml         1.7           120
//100  ml         3.4            48
//200  ml         6.8            48
//375  ml        12.7            24
//750  ml        25.4            12
//1.0  L         33.8            12
//1.75 L         59.2             6
///////////////////////////////////////////////////////
///////////////////////////////////////////////////////

	public static final int dash		= 1;
	public static final int teaspoon	= 4*dash;
	public static final int tablespoon	= 3*teaspoon;
	public static final int oz			= 32*dash;
	public static final int jigger		= 48; /* 1.5*oz */
	public static final int wineglass	= 4*oz;
	public static final int split		= 6*oz;
	public static final int cup			= 8*oz;
	public static final int pint		= 2*cup;
	public static final int quart		= 2*pint;
	public static final int gallon		= 4*quart;

/* OLD CRAP dash=1;tsp=4;tbsp=12;oz=32;pony=32;jigger=48;wineglass=128;
 * split=192;cup=256;miniature=54;tenth=406;pint=541;fifth=813;quart=1082;
 * magnum=1622;gallon=3789;jeroboam=3245;
 */

	/**
	 * Takes a unit name as a string and returns its integer value.
	 * The only legal strings are the names of the integers above.
	 *
	 * @param	str		The String to convert.
	 *
	 * @return	The int value of the given string.
	 *
	 * @throws	IllegalArgumentException
	 *			If the given string is not the name of a unit.
	 */
	public static int toInt(String str)
	{
		/*Breaking code conventions due to sanity reasons.*/
		if(str.equals(""))
			return 0;
		if(str.equalsIgnoreCase("dash"))
			return dash;
		if(str.equalsIgnoreCase("dashes"))
			return dash;
		if(str.equalsIgnoreCase("splash"))
			return dash;
		if(str.equalsIgnoreCase("splashes"))
			return dash;
		if(str.equalsIgnoreCase("tsp."))
			return teaspoon;
		if(str.equalsIgnoreCase("tsp"))
			return teaspoon;
		if(str.equalsIgnoreCase("teaspoon"))
			return teaspoon;
		if(str.equalsIgnoreCase("teaspoons"))
			return teaspoon;
		if(str.equalsIgnoreCase("tbsp."))
			return tablespoon;
		if(str.equalsIgnoreCase("tbsp"))
			return tablespoon;
		if(str.equalsIgnoreCase("tablespoon"))
			return tablespoon;
		if(str.equalsIgnoreCase("tablespoons"))
			return tablespoon;
		if(str.equalsIgnoreCase("oz."))
			return oz;
		if(str.equalsIgnoreCase("oz"))
			return oz;
		if(str.equalsIgnoreCase("pony"))
			return oz;
		if(str.equalsIgnoreCase("ounce"))
			return oz;
		if(str.equalsIgnoreCase("ounces"))
			return oz;
		if(str.equalsIgnoreCase("jigger"))
			return jigger;
		if(str.equalsIgnoreCase("jiggers"))
			return jigger;
		if(str.equalsIgnoreCase("jgr."))
			return jigger;	
		if(str.equalsIgnoreCase("jgr"))
			return jigger;
		if(str.equalsIgnoreCase("wineglass"))
			return wineglass;
		if(str.equalsIgnoreCase("wgls."))
			return wineglass;
		if(str.equalsIgnoreCase("wgls"))
			return wineglass;
		if(str.equalsIgnoreCase("split"))
			return split;
		if(str.equalsIgnoreCase("splits"))
			return split;
		if(str.equalsIgnoreCase("splt."))
			return split;
		if(str.equalsIgnoreCase("splt"))
			return split;
		if(str.equalsIgnoreCase("cup"))
			return cup;
		if(str.equalsIgnoreCase("cups"))
			return cup;
		if(str.equalsIgnoreCase("C"))
			return cup;
		if(str.equalsIgnoreCase("pint"))
			return pint;
		if(str.equalsIgnoreCase("pints"))
			return pint;
		if(str.equalsIgnoreCase("quart"))
			return quart;
		if(str.equalsIgnoreCase("quarts"))
			return quart;
		if(str.equalsIgnoreCase("gallon"))
			return gallon;
		if(str.equalsIgnoreCase("gallons"))
			return gallon;

		throw new IllegalArgumentException();
	}

	/**
	 * Takes an integer and returns a unit that it is equivalent to.
	 * The only legal integers are those defined above.
	 *
	 * @param	i	The integer to name.
	 *
	 * @return	The name of the defined unit as a String.
	 *
	 * @throws	IllegalArgumentException
	 *			If the given int is not defined in this class.
	 */
	public static String toString(int i)
	{
		/*Breaking code conventions due to sanity reasons.*/
		if(i == 0)
			return "";
		if(i == dash)
			return "dash";
		if(i == teaspoon)
			return "tsp";
		if(i == tablespoon)
			return "tbsp";
		if(i == oz)
			return "oz";
		if(i == jigger)
			return "jigger";
		if(i == wineglass)
			return "wineglass";
		if(i == split)
			return "split";
		if(i == cup)
			return "cup";
		if(i == pint)
			return "pint";
		if(i == quart)
			return "quart";
		if(i == gallon)
			return "gallon";

		throw new IllegalArgumentException();
	}
}
