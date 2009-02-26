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


public class SimpleStreamTokenizer
{
	protected Reader r;
	protected int delim;
	
	protected String curToken;
	protected int ch;
	protected boolean empty;
	
	public SimpleStreamTokenizer(Reader r, char delim)
	{
		this.r = r;
		this.delim = (int)delim;
		empty = false;
		curToken = "";
	}

	public boolean hasMoreTokens()
	{
		if(empty == true)
		{
			return false;
		}
		
		if(curToken.equals(""))
		{
			curToken = getToken();
		}
		return !empty;
	}

	public String nextToken()
	{
		String t;
		if(curToken.equals(""))
		{
			t = getToken();
		}
		else
		{
			t = curToken;
		}

		curToken = "";
		return t;
	}
	
	protected String getToken()
	{
		curToken = "";
		while(!empty)
		{
			ch = read();
			if(ch == -1)
			{
				empty = true;
			}
			else if(ch == delim)
			{
				if(curToken.equals(""))
				{
					return getToken();
				}
			
				return curToken;
			}
			else
			{
				Character c = new Character(((char)ch));
				curToken += c.toString();
			}
		}
		return null;
	}

	protected int read()
	{
		try
		{
			return r.read();
		}
		catch(Throwable t)
		{
			System.out.println(t);
		}
		return 0;
	}
}
