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


import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import drinkMixer.model.*;
import drinkMixer.model.drink.*;


public class IngredientEditForm
	extends JPanel
{
	protected Drink drink;
	
	protected JList ingredientList;

	protected JButton addButton;
	protected JButton removeButton;
	protected JButton editButton;

	protected JPanel buttonPanel;

	protected IngredientEditor ingredientEditor;
	
	public IngredientEditForm(Drink drink)
	{
		this.drink = drink;

		initComponents();
		initValues();
	}

	protected void initComponents()
	{
		ingredientList = new JList();
		
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		removeButton = new JButton("Delete");

		ButtonListener bl = new ButtonListener();
		addButton.addActionListener(bl);
		editButton.addActionListener(bl);
		removeButton.addActionListener(bl);

		buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(editButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(removeButton);
		
		this.setLayout(new BorderLayout());
		this.add(new JLabel("Ingredients:"), "North");
		this.add(ingredientList, "Center");
		this.add(buttonPanel, "South");
	}

	protected void initValues()
	{
		if(drink != null)
		{
			ingredientList.setListData(drink.getIngredients());
			ingredientList.setSelectedIndex(0);
		}
	}

	public Collection getIngredients()
	{
		return drink.getIngredients();
	}

	protected class ButtonListener
		implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
        	JButton src;

			src = (JButton)e.getSource();

			if(src == addButton)
			{
				doAdd();
			}
			else if(src == editButton)
			{
				doEdit();
				
			}
			else if(src == removeButton)
			{
				doRemove();
			}
		}
	}
	
	protected void doRemove()
	{
		drink.removeIngredient(
						(Ingredient)ingredientList.getSelectedValue());
		ingredientList.repaint();
	}

	protected void doEdit()
	{
		if(ingredientList.isSelectionEmpty())
		{
			doAdd();
		}
		else
		{
			JFrame f = new JFrame("Drink Mixer: Ingredient editor");
			IngredientEditor panel = new IngredientEditor(f,
				(Ingredient)ingredientList.getSelectedValue(), 
				drink, ingredientList);
			f.getContentPane().add(panel);
			f.pack();
			f.setVisible(true);
		}
	}

	protected void doAdd()
	{
		JFrame f = new JFrame("Drink Mixer: Ingredient editor");
		IngredientEditor panel = new IngredientEditor(f, drink, ingredientList);
		f.getContentPane().add(panel);
		f.pack();
		f.setVisible(true);
	}
}
