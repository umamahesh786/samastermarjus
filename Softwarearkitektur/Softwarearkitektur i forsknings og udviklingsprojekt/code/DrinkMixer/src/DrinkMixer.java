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


import java.awt.event.*;
import drinkMixer.model.*;
import drinkMixer.model.fileParsers.*;
import drinkMixer.views.*;
import javax.swing.*;
import drinkMixer.*;


public class DrinkMixer
{
	protected DrinkDB db;
	protected DrinkMixerView view;

	public static void main(String args[])
	{
		DrinkMixer d = new DrinkMixer();
	}
	
	public DrinkMixer()
	{
		System.out.println(new Disclaimer());
		db = null;
		try
		{
			db = DrinkDB.loadDatabase("SavedDatabase.db"); 
		}	
		catch(Throwable t)
		{
			FileParser parser = new JoeFileParser("res/drinks.txt");
			while(!parser.isDone());

			db = null;
			db = DrinkDB.createDatabase(parser.getDrinks()); 
		}

		JFrame f = new JFrame("Drink Mixer");
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				doExit();
			}
		});
	
		view = new BrowseView(db);
		db.addObserver(view);

		f.getContentPane().add((JPanel)view);
		f.pack();
		f.setVisible(true);
	}

	protected void doExit()
	{
		int exitVal = db.exit((JComponent)view);
		if(exitVal == db.SAFE_TO_EXIT)
		{
			System.exit(0);
		}
	}

}
