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
import drinkMixer.model.*;
import drinkMixer.model.drink.*;
//import javax.swing.tree.*;
//import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Yet another de-fuckization brought to you by marshall.
 * @author Marshall Hayden
 */
public class DrinkEditForm
	extends JPanel
{
	protected boolean newDrink;
	protected DrinkDB db;
	protected Drink drink;
	protected JFrame frame;
	
	protected DrinkField glass;
	protected DrinkField name;
	protected DrinkField year;
	protected DrinkField origin;
	protected DrinkField season;
	protected DrinkField temp;
	protected DrinkField type;
	protected DrinkField version;

	protected IngredientEditForm ingredientForm;
	protected JTextArea directionsArea;

	protected JButton commitButton;
	protected JButton cancelButton;
	protected String commitString;
	
	protected JPanel topPanel;
	protected JPanel fieldPanel;
	protected JPanel buttonPanel;

	public DrinkEditForm(JFrame frame, DrinkDB db)
	{
		this.db = db;
		drink = new Drink();
		this.frame = frame;
		commitString = "Add Drink.";
		newDrink = true;
		initComponents();
	}

	public DrinkEditForm(JFrame frame, DrinkDB db, Drink d)
	{
		this.db = db;
		drink = d;
		this.frame = frame;
		commitString = "Commit Changes.";
		newDrink = false;
		initComponents();
	}
	
	protected void initComponents()
	{
		glass = new DrinkField("Glass Type");
		name = new DrinkField("Drink Name");
		year = new DrinkField("Year of origin");
		origin = new DrinkField("Place of origin");
		season = new DrinkField("Season");
		temp = new DrinkField("Temperature");
		type = new DrinkField("Drink Type");
		version = new DrinkField("Version");

		directionsArea = new JTextArea();
		directionsArea.setBorder(BorderFactory.createEtchedBorder());
		directionsArea.setLineWrap(true);
		directionsArea.setWrapStyleWord(true);

		ingredientForm = new IngredientEditForm(new Drink(drink));
		ingredientForm.setBorder(BorderFactory.createEtchedBorder());

		fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridLayout(0,1));
		fieldPanel.add(name);
		fieldPanel.add(type);
		fieldPanel.add(glass);
		fieldPanel.add(season);
		fieldPanel.add(temp);
		fieldPanel.add(origin);
		fieldPanel.add(year);
		fieldPanel.add(version);

		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0,1));
		topPanel.add(fieldPanel);
		topPanel.add(ingredientForm);
		topPanel.add(directionsArea);

		if(drink != null)
		{
			glass.setValue(drink.getGlassType());
			name.setValue(drink.getName());
			year.setValue(new Integer(drink.getYear()).toString());
			origin.setValue(drink.getOriginPlace());
			season.setValue(drink.getSeason());
			temp.setValue(drink.getTemp());
			type.setValue(drink.getType());
			version.setValue(new Integer(drink.getVersion()).toString());
			directionsArea.setText(drink.getDirections());
		}

		commitButton = new JButton(commitString);
		commitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				doCommit(true);
			}
		});

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				doCommit(false);
			}
		});
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(commitButton, "West");
		buttonPanel.add(cancelButton, "East");

		setLayout(new BorderLayout());
		add(topPanel, "Center");
		add(buttonPanel, "South");
	}
	
	public static class DrinkField
		extends JPanel
	{
		static protected Dimension dim;
		
		static
		{
			dim = new Dimension(0,0);
		}
		
		protected JLabel label;
		protected JTextField value;

		public DrinkField(String label)
		{
			this.label = new JLabel(label);
			value = new JTextField();

			if(this.label.getPreferredSize().width+10 > dim.width)
			{
				dim.width = this.label.getPreferredSize().width+10;
			}

			this.label.setPreferredSize(dim);
			this.value.setPreferredSize(new Dimension(225,18));
			
			JPanel v = new JPanel();
			v.setLayout(new BoxLayout(v, BoxLayout.Y_AXIS));
			v.add(this.value);

			JPanel l = new JPanel();
			l.setLayout(new BorderLayout());
			l.add(this.label, "Center");

			this.setLayout(new BorderLayout());
			this.add(l, "West");
			this.add(v, "Center");
		}

		public void setValue(String value)
		{
			this.value.setText(value);
		}

		public String getValue()
		{
			return value.getText();
		}
	}

	protected void doCommit(boolean commitChanges)
	{
		if(drink == null)
		{
			drink = new Drink();
		}
		
		if(commitChanges)
		{
			drink.setName(name.getValue());
			drink.setGlassType(glass.getValue());
			drink.setOriginDate(new GregorianCalendar(
								new Integer(year.getValue()).intValue()
											, 1, 1));
			drink.setOriginPlace(origin.getValue());
			drink.setSeason(season.getValue());
			drink.setTemp(temp.getValue());
			drink.setType(type.getValue());
			drink.setVersion(new Integer(version.getValue()).intValue());
			drink.setIngredients(ingredientForm.getIngredients());
			drink.setDirections(directionsArea.getText());
		}
		
		if(newDrink)
		{
			db.addDrink(drink);
		}
		frame.setVisible(false);
		frame = null;
	}
} 
