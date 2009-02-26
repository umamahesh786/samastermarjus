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
import java.awt.*;
import java.util.*;
import drinkMixer.views.*;


public class PictureDataFileParser
{
	protected String fileName;
	protected Hashtable drinkShapes;
	
	protected PictureDataFileParser(String fileName)
	{
		this.fileName = fileName;
		drinkShapes = new Hashtable();
		parseFile();
	}
	
	public static Hashtable getDrinkShapes(String fileName)
	{
		PictureDataFileParser parser = new PictureDataFileParser(fileName);
		return parser.drinkShapes;
	}
	
	protected void parseFile()
	{
		BufferedReader file = null;
		String line;
		StringTokenizer tokens = null;
		DrinkPicture.DrinkShape ds;
		int x,y;
		String name;
		
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
				try
				{
					line = file.readLine();
					if(line.equals(""))
					{
						line = file.readLine();
					}
					tokens = new StringTokenizer(line, ":");
				}
				catch(IOException ioe)
				{
					System.out.println(ioe);
				}
				
				name = tokens.nextToken();
				ds = new DrinkPicture.DrinkShape();
				x = Integer.parseInt(tokens.nextToken());
				y = Integer.parseInt(tokens.nextToken());
				ds.offset = new Point(x,y);

				ds.shape = new Point[Integer.parseInt(tokens.nextToken())];
				for(int i = 0; i < ds.shape.length; ++i)
				{
					x = Integer.parseInt(tokens.nextToken());
					y = Integer.parseInt(tokens.nextToken());
					ds.shape[i] = new Point(x,y);
				}

				drinkShapes.put(name,ds);
			}
		}
		catch(IOException ioe)
		{
			System.out.println(ioe);
		}
	}
}
