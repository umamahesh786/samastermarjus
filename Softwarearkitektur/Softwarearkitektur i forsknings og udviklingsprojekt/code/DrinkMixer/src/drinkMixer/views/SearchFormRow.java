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


import drinkMixer.event.*;
import drinkMixer.model.*;
import drinkMixer.model.drink.*;
import drinkMixer.model.drink.comparators.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SearchFormRow
	extends JPanel
{
	protected DrinkDB model;
	protected JComboBox field;
	protected JComboBox compareMethod;
	protected JComboBox values;
	protected JComboBox andOr;
	protected Collection listeners;
	
	public SearchFormRow(DrinkDB model)
	{
		this.model = model;

		listeners = new Vector();

		field = new JComboBox(DrinkComparators.getComparators());
		field.setRenderer(new DrinkMixerView.DrinkComparatorRenderer());
		field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				doChangeValues();
			}
		});
		
		
		compareMethod = new JComboBox();
		compareMethod.addItem("contains");
		//compareMethod.addItem("!=");
		//compareMethod.addItem("like");
		//compareMethod.addItem("not like");
		
		values = new JComboBox();
		values.setEditable(true);

		andOr = new JComboBox();
		andOr.addItem(" ");
		andOr.addItem("and");
		andOr.addItem("or");
		andOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				fireAndOrChanged((String)
						((JComboBox)e.getSource()).getSelectedItem());
			}
		});

		this.add(field);
		this.add(compareMethod);
		this.add(values);
		this.add(andOr);

		doChangeValues();
	}

	protected void fireAndOrChanged(String selectedItem)
	{
		SearchFormRowEvent e = new SearchFormRowEvent(andOr);

		SearchFormRowListener l;
		Iterator i = listeners.iterator();
		
		while(i.hasNext())
		{
			l = (SearchFormRowListener)i.next();
			l.andOrChanged(e);
		}
	}

	public void addSearchFormRowListener(SearchFormRowListener l)
	{
		listeners.add(l);
	}

	public void removeSearchFormRowListener(SearchFormRowListener l)
	{
		listeners.remove(l);
	}
	
	protected void doChangeValues()
	{
		DrinkComparator d = (DrinkComparator)field.getSelectedItem();
		values.removeAllItems();
		
		Iterator i = null;
		if(d instanceof SeasonComparator)
		{
			i = model.seasonNames.iterator();
		}
		else if(d instanceof TempComparator)
		{
			i = model.tempNames.iterator();
		}
		else if(d instanceof GlassComparator)
		{
			i = model.glassNames.iterator();
		}
		else if(d instanceof IngredientComparator)
		{
			i = model.ingredientNames.iterator();
		}
		else if(d instanceof NameComparator)
		{
			i = model.nameNames.iterator();
		}
		else if(d instanceof DateComparator)
		{
			i = model.dateNames.iterator();
		}
		else if(d instanceof TypeComparator)
		{
			i = model.typeNames.iterator();
		}
		else if(d instanceof PlaceComparator)
		{
			i = model.placeNames.iterator();
		}
		
		
		if(i != null)
		{
			while(i.hasNext())
			{
				//System.out.print(".");
				values.addItem(i.next());
			}
		}
	}

	public java.util.List getResult()
	{
		DrinkComparator d = (DrinkComparator)field.getSelectedItem();
		String value = null;
		try
		{
			value = values.getSelectedItem().toString();
		}
		catch(NullPointerException npe)
		{
			return new Vector();
		}
		if(d == null)
		{
			return new Vector();
		}

		return model.searchFor(d, value);
	}

	public String getAndOrValue()
	{
		return (String)andOr.getSelectedItem();
	}
}
