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

package drinkMixer.model;


import java.lang.Integer;

/**
 * The rational class is designed to store rational numbers.
 */
public class Rational
	extends Number
	implements Comparable
{
	/**
	 * @serial foo
	 */
	protected int numerator;


	/**
	 * @serial foo
	 */
	protected int denominator;
	
	/**
	 * Contructs a rational with a numerator of <code>n</code> and a 
	 * denominator of <code>d</code>.
	 */
	public Rational(int n, int d)
	{
		numerator = n;
		denominator = d;
	}
	
	/**
	 * Returns the value of the specified rational as a <code>byte</code>.
	 */
	public byte byteValue()
	{
		return (byte)(numerator/denominator);
	}

	/**
	 * Returns the value of the specified rational as a <code>int</code>.
	 */
	public int intValue()
	{
		return (int)(numerator/denominator);
	}

	/**
	 * Returns the value of the specified rational as a <code>double</code>.
	 */
	public double doubleValue()
	{
		return (double)(numerator/denominator);
	}
	
	/**
	 * Returns the value of the specified rational as a <code>float</code>.
	 */
	public float floatValue()
	{
		return (float)(numerator/denominator);
	}

	/**
	 * Returns the value of the specified rational as a <code>long</code>.
	 */
	public long longValue()
	{
		return (long)(numerator/denominator);
	}

	/**
	 * Returns the value of the specified rational as a <code>short</code>.
	 */
	public short shortValue()
	{
		return (short)(numerator/denominator);
	}
	/**
	 * Returns a string representation of the rational. The output varies 
	 * depending on the value of the rational being printed.
	 */
	public String toString()
	{
		int w = 0;
		int n = 0;
		int d = 0;
		
		String s = new String();
		
		if(numerator > denominator)
		{
			w = (int)(numerator/denominator);
			n = numerator%denominator;
			d = denominator;

			if(n == 0){
				s = Integer.toString(w);
			}
			else
			{
				s = Integer.toString(w)
					+ " "
					+ Integer.toString(n)
					+ "/"
					+ Integer.toString(d);
			}
		}
		else if(numerator < denominator)
		{
			if(numerator == 0)
			{
			}
			else
			{
				s = numerator + "/" + denominator;
			}
		}
		else if(numerator == denominator)
		{
			if(denominator == 0)
			{
				s = "NaN";
			}
			else
			{
				s = "1";
			}
		}
		return s;
	}
	
	/**
	 * Compares this object with the specified object for order.
	 *
	 * Cant you figure this our for yourself :)!
	 */
	public int compareTo(Object o)
	{
		if(!(o instanceof Number))
		{
			throw new ClassCastException();
		}
		else if(o instanceof Rational)
		{
			Double a = new Double(this.doubleValue());
			Double b = new Double(((Rational)o).doubleValue());

			return a.compareTo(b);
		}
		else
		{
			Double d = new Double(this.doubleValue());
			return d.compareTo((Double) o);
		}
	}

	public boolean equals(Object o)
	{
		if(!(o instanceof Number))
		{
			return false;
		}
	
		return this.compareTo(o) == 0;
	}
	
	/**
	 * Adds the specified rational with <code>val</code>.
	 */
	public Rational add(Rational val)
	{
		int d = this.denominator * val.denominator;
		int n = ((this.numerator * val.denominator)
				+ (this.denominator * val.numerator));
	
		Rational ans = new Rational(n, d);
		ans.reduce();
		return ans;
	}

	/**
	 * Subtracts the specified rational with <code>val</code>.
	 */
	public Rational subtract(Rational val)
	{
		int d = this.denominator * val.denominator;
		int n = ((this.numerator * val.denominator)
				- (this.denominator * val.numerator));
	
		Rational ans = new Rational(n, d);
		ans.reduce();
		return ans;
	}
	
	/**
	 * Multiplies the specified rational with <code>val</code>.
	 */
	public Rational multiply(Rational val)
	{
		int d = this.denominator * val.denominator;
		int n = this.numerator * val.numerator;
		
		Rational ans = new Rational(n, d);
		ans.reduce();
		return ans;

	}

	/**
	 * Divides the specified rational with <code>val</code>.
	 */
	public Rational divide(Rational val)
	{
		int d = this.denominator * val.numerator;
		int n = this.numerator * val.denominator;
		
		Rational ans = new Rational(n, d);
		ans.reduce();
		return ans;
	}
	
	protected void reduce()
	{
		int a, b, t;
		
		if(this.numerator > this.denominator)
		{
			a = this.numerator;
			b = this.denominator;
		}
		else
		{
			b = this.numerator;
			a = this.denominator;
		}
		
		while(b != 0)
		{
			t = a;
			a = b;
			b %=a;
		}

		this.numerator /= a;
		this.denominator /= a;
	}
}
