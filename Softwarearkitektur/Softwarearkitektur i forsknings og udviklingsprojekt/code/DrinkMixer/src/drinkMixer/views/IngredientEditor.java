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
import drinkMixer.model.drink.*;
import java.awt.*;
import java.awt.event.*;


/**
 *
 * @author Marshall Hayden
 */
public class IngredientEditor
	extends JPanel
{
	protected Drink drink;
	protected Ingredient ingredient;
	protected JFrame frame;
	
	protected JTextField amount;
	protected JTextField unit;
	protected JTextField name;
	
	protected JPanel fieldPanel;
	protected JPanel buttonPanel;
	
	protected ButtonListener bl;
	
	protected JButton ok;
	protected JButton cancel;

	protected JList list;
	
	public IngredientEditor(JFrame f, Drink d, JList l)
	{
		this.frame = f;
		this.drink = d;
		this.list = l;
		ingredient = null;

		initComponents();
		setPreferredSize(new Dimension(250,100));
		setSize(new Dimension(250,100));
	}
	
	public IngredientEditor(JFrame f, Ingredient ing, Drink d, JList l)
	{
		this.frame = f;
		this.drink = d;
		this.list = l;
		
		this.ingredient = ing;

		initComponents();
		setValues();
		setPreferredSize(new Dimension(250,100));
		setSize(new Dimension(250,100));
	}

	
	protected void initComponents()
	{
		amount = new JTextField();
		amount.setColumns(15);
		JPanel amountPanel = new JPanel();
		amountPanel.setLayout(new BorderLayout());
		amountPanel.add(new JLabel("Amount"), "West");
		amountPanel.add(amount, "Center");

		unit = new JTextField();
		unit.setColumns(15);
		JPanel unitPanel = new JPanel();
		unitPanel.setLayout(new BorderLayout());
		unitPanel.add(new JLabel("Unit"), "West");
		unitPanel.add(unit, "Center");

		name = new JTextField();
		name.setColumns(15);
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		namePanel.add(new JLabel("Name"), "West");
		namePanel.add(name, "Center");
		
		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(3,1));
		fieldPanel.add(amountPanel);
		fieldPanel.add(unitPanel);
		fieldPanel.add(namePanel);

		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		
		buttonPanel = new JPanel();
		buttonPanel.add(ok);
		buttonPanel.add(cancel);

		setLayout(new BorderLayout());
		add(fieldPanel, "Center");
		add(buttonPanel, "South");

		bl = new ButtonListener();
		ok.addActionListener(bl);
		cancel.addActionListener(bl);
		
	}

	protected Ingredient getIngredient()
	{
		return new Ingredient(amount.getText() +
							" " + unit.getText() +
							" " + name.getText());
	}

	protected void setValues()
	{
		try
		{
			amount.setText(ingredient.getAmount().toString());
		}
		catch(NullPointerException noe)
		{
		}

		try
		{
			unit.setText(Units.toString(ingredient.getUnit()));
		}
		catch(NullPointerException noe)
		{
		}

		name.setText(ingredient.getName());
	}

	protected class ButtonListener
		implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
        	JButton src;

			src = (JButton)e.getSource();

			if(src == ok)
			{
				if(ingredient != null)
				{
					drink.removeIngredient(ingredient);
				}
				ingredient = new Ingredient(amount.getText() +
							" " + unit.getText() +
							" " + name.getText());
				drink.addIngredient(ingredient);
				frame.setVisible(false);
				frame = null;
				list.setListData(drink.getIngredients());
			}
			else if(src == cancel)
			{
				frame.setVisible(false);
				frame = null;
				
			}
		}
	}
}
