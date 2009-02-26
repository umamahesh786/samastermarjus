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


import drinkMixer.model.*;
import drinkMixer.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SearchForm
	extends JPanel
	implements SearchFormRowListener
{
	protected static final int ROWS = 5;
	protected Vector rows;
	protected int usedRows;
	protected DrinkDB model;
	protected DrinkMixerView view;
	protected JButton button;
	
	public SearchForm(DrinkDB model, DrinkMixerView view)
	{
		Dimension d = new SearchFormRow(model).getPreferredSize();
		d.height *= ROWS+1;
		d.width += 50;
		setPreferredSize(d);
		
		this.model = model;
		this.view = view;
		
		button = new JButton("Search!");
		
		rows = new Vector(ROWS);
		usedRows = 0;
		addRow();

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				doSearch();
			}
		});
	
	}

	protected void doSearch()
	{
		java.util.List total;
		java.util.List part;
		String andOr;
		
		andOr = ((SearchFormRow)rows.get(0)).getAndOrValue();
		total = ((SearchFormRow)rows.get(0)).getResult();
		
		for(int x = 1; x < usedRows; ++x)
		{
			part = ((SearchFormRow)rows.get(x)).getResult();
			if(andOr.equals("and"))
			{
				total = sloppyIntersect(total, part);
			}
			else
			{
				total = sloppyUnion(total, part);
			}
			andOr = ((SearchFormRow)rows.get(x)).getAndOrValue();
		}
		//System.out.println(total.size());
		view.searchReturned(total);
	}

	protected void addRow()
	{
		remove(button);
		rows.add(new SearchFormRow(model));
		add((SearchFormRow)rows.get(usedRows));
		((SearchFormRow)rows.get(usedRows)).addSearchFormRowListener(this);
		usedRows++;
		add(button);
		validate();
	}

	protected void removeRow()
	{
		remove(button);
		usedRows--;
		remove((SearchFormRow)rows.remove(usedRows));
		add(button);
		setVisible(false);
		setVisible(true);
	}

	public void andOrChanged(SearchFormRowEvent e)
	{
		JComboBox andOr = (JComboBox)e.getSource();
		int index = rows.indexOf(andOr.getParent());
		
		//System.out.println(index + "::"+usedRows);
		//System.out.println((String)andOr.getSelectedItem());
		if(index == -1)
		{
			System.out.println("woops! the andor is not in the form!");
			return;
		}
		
		if(index == (usedRows-1)) /*last row*/
		{
			if(!andOr.getSelectedItem().equals(" "))
			{
				addRow();
			}
		}
		else
		{
			if(andOr.getSelectedItem().equals(" "))
			{
				while(index < (usedRows-1))
				{
					removeRow();
				}
			}
		}
	}

	protected java.util.List sloppyIntersect(java.util.List l1,
											java.util.List l2)
	{
		java.util.List total = new Vector();
		Iterator i = l1.iterator();
		Object o;
		while(i.hasNext())
		{
			o = i.next();
			if(l2.contains(o))
			{
				total.add(o);
			}
		}
		i = l2.iterator();
		while(i.hasNext())
		{
			o = i.next();
			if(l1.contains(o))
			{
				if(!total.contains(o))
				{
					total.add(o);
				}
			}
		}
		return total;
	}

	protected java.util.List sloppyUnion(java.util.List ol1,
										java.util.List ol2)
	{
		java.util.List l1,l2;
		l1 = new Vector(ol1);
		l2 = new Vector(ol2);
		java.util.List total = new Vector(l1);
		Iterator i = l2.iterator();
		Object o;
		while(i.hasNext())
		{
			o = i.next();
			if(!total.contains(o))
			{
				total.add(o);
			}
		}
		return total;
	}

		
}
