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

package drinkMixer.views;


import javax.swing.*;
import java.awt.*;
import drinkMixer.model.drink.comparators.*;
import drinkMixer.model.*;
import drinkMixer.model.drink.*;


/**
 * This interface should be implemented by all classes intending to be a view
 * for the DrinkMixer program.
 *
 * @author <A HREF="mailto:haydenml@clarkson.edu">Marshall Hayden</A>
 */
public interface DrinkMixerView
	extends java.util.Observer
{
	/**
	 * Returns the name of this view as a String.
	 */
	public abstract String getName();
	
	/**
	 * Informs the view that a search has returned the specified results.
	 */
	public abstract void searchReturned(java.util.List searchResults);

	public class DrinkComparatorRenderer
		extends DefaultListCellRenderer
	{
		public DrinkComparatorRenderer()
		{
		}
		
		public Component getListCellRendererComponent(JList list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus)
		{
			return super.getListCellRendererComponent(list,
					((DrinkComparator)value).getName(), index, isSelected,
					cellHasFocus);
		}
	}

	public class DrinkRenderer
		extends DefaultListCellRenderer
	{
		public DrinkRenderer()
		{
		}
		
		public Component getListCellRendererComponent(JList list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus)
		{
			return super.getListCellRendererComponent(list,
					((Drink)value).getName(), index, isSelected,
					cellHasFocus);
		}
	}
}
