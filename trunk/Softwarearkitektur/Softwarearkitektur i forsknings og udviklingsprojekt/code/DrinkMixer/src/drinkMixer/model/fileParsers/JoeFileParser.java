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

package drinkMixer.model.fileParsers;


import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import drinkMixer.model.drink.*;
import drinkMixer.model.*;

public class JoeFileParser
	implements FileParser
{
	protected boolean isDone;
	protected String fileName;
	protected LinkedList drinks;

	/**
	 * Creates a news JoeFileParser which spins off a thread which parses the 
	 * file.
	 *
	 * @param file The file to parse
	 */
	public JoeFileParser(String file)
	{
		this.fileName = file;
		isDone = false;
		drinks = new LinkedList();
		ParseThread t = new ParseThread();
		t.start();
	}

	/**
	 * Returns the parsed Drinks.
	 * 
	 * @return A LinkedList of the parsed Drinks
	 */
	public Collection getDrinks()
	{
		return drinks;
	}
	
	/**
	 * Used to tell if the parseing thread has finished.
	 *
	 * @return Whether the pare thread has finished or not.
	 */
	public boolean isDone()
	{
		return isDone;
	}

	class ParseThread 
		extends Thread
	{
		protected JFrame progressFrame;
		protected JProgressBar progressBar;
		protected JLabel progressLabel;
		protected int currentProgressValue;
		
		public ParseThread()
		{
			initProgressFrame();
		}

		public void start()
		{
			super.start();
		}

		public void run()
		{
			int len;
			int count = 0;
			Drink theDrink;
			String aIngredient;
			String temp;
			StringTokenizer lineTokenizer = null;
			StringTokenizer ingredientsTokenizer = null;
			GregorianCalendar newDate;
			BufferedReader file = null;

			try
			{
				file = new BufferedReader(new FileReader(fileName));
			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println(fnfe);
			}
			
			try
			{
				while(file.ready())
				{
					theDrink = new Drink();
					
					try
					{
						temp = file.readLine();
						if(temp.equals(""))
						{
							temp = file.readLine();
						}
						lineTokenizer = new StringTokenizer(temp, ":");
					}
					catch(IOException ioe)
					{
						System.out.println(ioe);
					}
					
					//set the name

					String name = new String(
								(String)lineTokenizer.nextElement());
					progressBar.setValue(++currentProgressValue);
					progressLabel.setText(name);
					
					theDrink.setName(name);
					
					ingredientsTokenizer = new StringTokenizer(new String(
									(String)lineTokenizer.nextElement()), ",");

					//set the ingredients
					try
					{
						while(ingredientsTokenizer.hasMoreElements())
						{
							Ingredient newIngredient = new Ingredient(
											ingredientsTokenizer.nextToken());
							theDrink.addIngredient(newIngredient);
						}
					}
					catch(NoSuchElementException nsee)
					{
						System.out.println(nsee);
					}
					
					//set the type
					theDrink.setType(new String(
										(String)lineTokenizer.nextElement()));
			
					//set the season
					theDrink.setSeason(new String(
										(String)lineTokenizer.nextElement()));
				
					//set the temp
					theDrink.setTemp(new String(
										(String)lineTokenizer.nextElement()));
				
					//set the glassType 
					theDrink.setGlassType(new String(
										(String)lineTokenizer.nextElement()));

					//set the directions
					theDrink.setDirections(new String(
										(String)lineTokenizer.nextElement()));

					drinks.add(theDrink);

				}
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
			}
			catch(NoSuchElementException nsee)
			{
				System.out.println(nsee);

			}

			isDone = true;

			progressFrame.setVisible(false);
			progressFrame = null;
			throw new ThreadDeath();

		}

		protected void initProgressFrame()
		{
			String temp;
			int count = 0;
			BufferedReader file = null;

			try
			{
				file = new BufferedReader(new FileReader(fileName));

				while(file.ready())
				{
					temp = file.readLine();
					if(!(temp.equals("")))
					{
						count++;
					}
				}
			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println(fnfe);
			}
			catch(IOException ioe)
			{
				System.out.println(ioe);
			}
			
			WindowListener l = new WindowAdapter() {
	 		   public void windowClosing(WindowEvent e) {System.exit(0);}
			};
			
			progressFrame = new JFrame("DrinkMixer");
			progressFrame.addWindowListener(l);

			JPanel progressPanel = new JPanel(){
				public Insets getInsets() {
					return new Insets(40,30,20,30);
	    		}
			};;
			
			progressPanel.setLayout(new BoxLayout(progressPanel, 
														BoxLayout.Y_AXIS));
			progressFrame.getContentPane().add(progressPanel, 
														BorderLayout.CENTER);
			Dimension d = new Dimension(400, 20);
			
			progressLabel = new JLabel("Loading, please wait...");
			progressLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			progressLabel.setMaximumSize(d);
			progressLabel.setPreferredSize(d);
			progressPanel.add(progressLabel);
			progressPanel.add(Box.createRigidArea(new Dimension(1,20)));

			progressBar = new JProgressBar(0, count);
			progressBar.setStringPainted(true);
			progressLabel.setLabelFor(progressBar);
			progressBar.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			progressPanel.add(progressBar);

			progressFrame.setSize(400, 200);
			Dimension screenSize = Toolkit.getDefaultToolkit().
														getScreenSize();
			progressFrame.setLocation(screenSize.width/2 - 400/2,
									  screenSize.height/2 - 200/2);
			progressFrame.setVisible(true);

        	progressFrame.setCursor(Cursor.getPredefinedCursor(
														Cursor.WAIT_CURSOR));
		}
	}
}
