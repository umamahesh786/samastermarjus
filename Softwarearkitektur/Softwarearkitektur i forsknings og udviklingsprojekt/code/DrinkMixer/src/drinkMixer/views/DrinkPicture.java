package drinkMixer.views;


import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.Applet;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import drinkMixer.model.fileParsers.*;


public class DrinkPicture
	extends JPanel
{
	protected Graphics2D g;
	protected DrinkShape shape;
	protected Image fooImage;
	protected int[] ys;
	public Color[] ingColors;
	static protected Hashtable table;

	static
	{
		table = PictureDataFileParser.getDrinkShapes("res/pictures.txt");
	}

	public DrinkPicture(String glassType, int n)
	{
		fooImage = Toolkit.getDefaultToolkit().getImage("res/niceDrinks.gif");

		ys = new int[n + 1];
		shape = (DrinkShape)table.get(glassType);
		if(shape == null)
		{
			shape = (DrinkShape)table.get("Highball");
		}

		findYs(n);
		
		ingColors = new Color[6];
		ingColors[0] = new Color(0f, 0f, 1f, .6f);
		ingColors[1] = new Color(0f, 1f, 0f, .6f);
		ingColors[2] = new Color(0f, 1f, 1f, .6f); 
		ingColors[3] = new Color(1f, 0f, 0f, .6f); 
		ingColors[4] = new Color(1f, 0f, 1f, .6f); 
		ingColors[5] = new Color(1f, 1f, 0f, .6f);
	}

	public void paint(Graphics gr)
	{
		Dimension dim = getSize();

		g = (Graphics2D) gr;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
							RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(new Color(0f, 0f, 0f, .5f));
		g.fill(new Rectangle2D.Double(0, 0, 140, 205));

		g.drawImage(fooImage, -shape.offset.x, -shape.offset.y, this);

        GeneralPath clipPolygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 4);

		clipPolygon.moveTo(shape.shape[0].x - shape.offset.x,
											shape.shape[0].y - shape.offset.y);

		for(int i = 1; i < shape.shape.length; i++)
		{
        	clipPolygon.lineTo(shape.shape[i].x - shape.offset.x,
										shape.shape[i].y - shape.offset.y);
		}
        clipPolygon.closePath();

		g.setClip(clipPolygon);
		
		for(int i = 0; i < ys.length - 1; i++)
		{
			g.setColor(ingColors[i % 6]);
			g.fillRect(0, ys[i], 140, (ys[i + 1] - ys[i]));
		}
	}

	protected void findYs(int n)
	{
		int max = 0;
		int min = 1000000;
		int d;

		for(int i = 0; i < shape.shape.length; i++)
		{
			if(shape.shape[i].y < min)
			{
				min = shape.shape[i].y;
			}
		}

		for(int i = 0; i < shape.shape.length; i++)
		{
			if(shape.shape[i].y > max)
			{
				max = shape.shape[i].y;
			}
		}

		d =  max - min;


		ys[0] = 0;

		for(int i = 1; i <= n; i++)
		{
			ys[i] = ((d/n) * i) + min - shape.offset.y;
		}
	}

	public void changeDrink(String glassType, int n)
	{
		ys = new int[n + 1];
		shape = (DrinkShape)table.get(glassType);
		if(shape == null)
		{
			shape = (DrinkShape)table.get("Highball");
		}

		findYs(n);

	}

	static public class DrinkShape
	{
		public Point[] shape;
		public Point offset;
	}


}
