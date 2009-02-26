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
import drinkMixer.*;
import drinkMixer.model.*;
import drinkMixer.model.drink.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.print.*;
import java.awt.event.*;
import drinkMixer.model.drink.comparators.*;

/**
 * This is a de-fuckization of boss's original BrowseDatabase class.
 * @author Marshall Hayden
 */
public class BrowseView
	extends JPanel
	implements DrinkMixerView, Printable
{
	protected DrinkComparator[] drinkComparators;

	protected DrinkDB model;
	protected BorderLayout myLayout;

	protected JTabbedPane tabPane;
	protected JPanel drinkTab;
	protected JPanel searchTab;

	protected SearchForm searchForm;

	/* Drink List Section */
	protected JPanel listPanel;
	protected JList drinkList;
	protected JComboBox sortComboBox;

	protected Color[] colors;

	{
		colors = new Color[6];
		colors[0] = Color.blue;
		colors[1] = Color.green;
		colors[2] = Color.cyan;
		colors[3] = Color.red;
		colors[4] = Color.magenta;
		colors[5] = Color.yellow;
	}

	protected JPanel topPanel;

	/* The Menu Bar */
	protected JMenuBar menuBar;

	/* File Menu */
	protected JMenu fileMenu;
	protected JMenuItem newMI;
	protected JMenuItem openMI;
	protected JMenuItem saveAsMI;
	protected JMenuItem saveMI;
	protected JMenuItem exitMI;

	/* Drink Menu */
	protected JMenu drinkMenu;
	protected JMenuItem addMI;
	protected JMenuItem editMI;
	protected JMenuItem printMI;
	protected JMenuItem newVerMI;
	protected JMenuItem deleteMI;

	/* Help Menu */
	protected JMenu helpMenu;
	protected JMenuItem aboutMI;

	/* The Tool Bar */
	protected JToolBar toolBar;

	protected JButton addTB;
	protected JButton editTB;
	protected JButton printTB;
	protected JButton saveTB;
	protected JButton exitTB;
	protected JButton aboutTB;
	protected JButton openTB;
	protected JButton newVerTB;
	protected JButton deleteTB;

	/* Drink Detail Section */
	protected JPanel drinkDetailPanel;
	protected JTextArea drinkTextArea;
	protected JList ingredientList;
	protected JTextArea directionsArea;

	/* Drink Image Panel */
	protected DrinkPicture imagePanel;

	public BrowseView(DrinkDB model)
	{
		this.model = model;

		myLayout = new BorderLayout();
		setLayout(myLayout);

		initComponents();

		setPreferredSize(new Dimension(630, 460));
		drinkList.setSelectedIndex(0);
		//setDoubleBuffered(true);
  	}

	protected void initComponents()
	{
		initMenuBar();
		initToolBar();

		initDrinkDetailPanel();

		imagePanel = new DrinkPicture("foo", 1);
		imagePanel.setPreferredSize(new Dimension(140,205));
		JPanel imageHolderPanel = new JPanel();
  		imageHolderPanel.setLayout(new BorderLayout());
    	imageHolderPanel.add(imagePanel, "North");
     	imageHolderPanel.add(Box.createGlue(), "Center");

		initListPanel();

		drinkTab = new JPanel();
		drinkTab.setLayout(new BorderLayout());
		drinkTab.add(imageHolderPanel, "West");
		drinkTab.add(drinkDetailPanel, "Center");

		searchForm = new SearchForm(model, this);
		searchTab = new JPanel();
		searchTab.add(new JScrollPane(searchForm));

		tabPane = new JTabbedPane();
		tabPane.add("Drink Info", drinkTab);
		tabPane.add("Complex Search", searchTab);

		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(menuBar, "North");
		topPanel.add(toolBar, "South");

		add(topPanel, "North");
		add(tabPane, "Center");
		add(listPanel, "East");
	}

	protected void initMenuBar()
	{
		fileMenu	= new JMenu("File");
		newMI		= new JMenuItem("New");
		openMI		= new JMenuItem("Open...");
		saveMI		= new JMenuItem("Save");
		saveAsMI	= new JMenuItem("Save As ...");
		exitMI		= new JMenuItem ("Exit");
		fileMenu.add(newMI);
		fileMenu.add(openMI);
		fileMenu.add(saveMI);
		fileMenu.add(saveAsMI);
		fileMenu.addSeparator();
		fileMenu.add(exitMI);

		drinkMenu	= new JMenu("Drink");
		addMI		= new JMenuItem("Add Drink...");
		editMI		= new JMenuItem("Edit Drink...");
		deleteMI	= new JMenuItem("Remove Drink");
		printMI		= new JMenuItem("Print Drink...");
		newVerMI 	= new JMenuItem("Create new version...");
		drinkMenu.add(addMI);
		drinkMenu.add(editMI);
		drinkMenu.add(deleteMI);
		drinkMenu.add(printMI);
		drinkMenu.add(newVerMI);

		helpMenu	= new JMenu("Help");
		aboutMI		= new JMenuItem("About");
		helpMenu.add(aboutMI);

		menuBar = new JMenuBar();
		menuBar.add(fileMenu);
		menuBar.add(drinkMenu);
		menuBar.add(helpMenu);

		MenuActionListener ml = new MenuActionListener();
		newMI.addActionListener(ml);
		openMI.addActionListener(ml);
		saveMI.addActionListener(ml);
		saveAsMI.addActionListener(ml);
		exitMI.addActionListener(ml);
		addMI.addActionListener(ml);
		editMI.addActionListener(ml);
		printMI.addActionListener(ml);
		aboutMI.addActionListener(ml);
		newVerMI.addActionListener(ml);
		deleteMI.addActionListener(ml);
	}

	protected void initToolBar()
	{
 		MenuActionListener ml = new MenuActionListener();

		toolBar	= new JToolBar();
		addTB 	= makeTool("new", ml);
		editTB	= makeTool("edit", ml);
		exitTB	= makeTool("exit", ml);
		saveTB	= makeTool("save", ml);
		printTB	= makeTool("print", ml);
		aboutTB	= makeTool("help", ml);
		openTB	= makeTool("open", ml);
		newVerTB = makeTool("newVer", ml);
		deleteTB = makeTool("remove", ml);

		toolBar.add(addTB);
		toolBar.addSeparator( new Dimension(3,3) );
		toolBar.add(editTB);
		toolBar.addSeparator( new Dimension(3,3) );
		toolBar.add(newVerTB);
		toolBar.addSeparator( new Dimension(3,3) );
		toolBar.add(deleteTB);
		toolBar.addSeparator( new Dimension(5,5) );
		toolBar.add(openTB);
		toolBar.addSeparator( new Dimension(3,3) );
		toolBar.add(saveTB);
		toolBar.addSeparator( new Dimension(3,3) );
		toolBar.add(printTB);
		toolBar.addSeparator( new Dimension(5,5) );
		toolBar.add(aboutTB);
		toolBar.addSeparator( new Dimension(5,5) );
		toolBar.add(exitTB);
	}

 	protected JButton makeTool(String name, MenuActionListener ml)
	{
		JButton b = new JButton(new ImageIcon("res/icons/"+name+".gif"));
		b.setToolTipText(name);
		b.setMargin(new Insets(2,2,2,2));
		b.addActionListener(ml);
    	return b;
	}

	protected class MenuActionListener
		implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
      		JMenuItem MIsrc;
        	JButton TBsrc;

			if(e.getSource() instanceof JMenuItem)
            {
            	MIsrc = (JMenuItem)e.getSource();
             	TBsrc = new JButton();
            }
            else
            {
                TBsrc = (JButton)e.getSource();
                MIsrc = new JMenuItem();
            }

			if(MIsrc == newMI)
			{
			//doNew();
			}
			else if(MIsrc == openMI || TBsrc == openTB)
			{
				doOpen();
			}
			else if(MIsrc == saveMI || TBsrc == saveTB)
			{
				doSave();
			}
			else if(MIsrc == saveAsMI)
			{
				doSaveAs();
			}
			else if(MIsrc == exitMI || TBsrc == exitTB)
			{
				doExit();
			}
			else if(MIsrc == addMI || TBsrc == addTB)
			{
				doAdd();
			}
			else if(MIsrc == editMI || TBsrc == editTB)
			{
				doEdit();
			}
			else if(MIsrc == printMI || TBsrc == printTB)
			{
				doPrint();
			}
			else if(MIsrc == aboutMI || TBsrc == aboutTB)
			{
				doAbout();
			}
			else if(MIsrc == newVerMI || TBsrc == newVerTB)
			{
				doNewVer();
			}
			else if(MIsrc == deleteMI || TBsrc == deleteTB)
			{
				doDelete();
			}
		}
	}

	protected void initDrinkDetailPanel()
	{
		drinkTextArea = new JTextArea();
		drinkTextArea.setEditable(false);
		drinkTextArea.setLineWrap(true);
		drinkTextArea.setWrapStyleWord(true);
		drinkTextArea.setBackground(Color.black);
		drinkTextArea.setForeground(Color.white);

		ingredientList = new JList();
		ingredientList.setBackground(Color.black);
		ingredientList.setCellRenderer(new IngredientCellRenderer());

		directionsArea	= new JTextArea();
		directionsArea.setEditable(false);
		directionsArea.setLineWrap(true);
		directionsArea.setWrapStyleWord(true);
		directionsArea.setBackground(Color.black);
		directionsArea.setForeground(Color.white);

		drinkDetailPanel = new JPanel();
		drinkDetailPanel.setLayout(new GridLayout(3,1));
		drinkDetailPanel.add(new JScrollPane(drinkTextArea));
		drinkDetailPanel.add(new JScrollPane(ingredientList));
		drinkDetailPanel.add(new JScrollPane(directionsArea));
	}

	protected void initListPanel()
	{
	   	drinkList =
			new JList(model.getDrinks(DrinkComparators.NAME).toArray());
		drinkList.setCellRenderer(new DrinkMixerView.DrinkRenderer());
		drinkList.addListSelectionListener(new DrinkListListener());

		drinkComparators = DrinkComparators.getComparators();
		sortComboBox = new JComboBox();
		for(int i = 0; i < drinkComparators.length; ++i)
		{
			sortComboBox.addItem(drinkComparators[i]);
		}

		sortComboBox.setRenderer(new DrinkMixerView.DrinkComparatorRenderer());
		sortComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				doChangeSort();
			}
		});

		listPanel = new JPanel();
		listPanel.setLayout(new BorderLayout());
		listPanel.add(new JScrollPane(drinkList), "Center");
		listPanel.add(sortComboBox, "South");
	}

	protected void doOpen()
	{
		String filename;
		String message;

		FileDialog dialog = new FileDialog(Frame.getFrames()[0],
			"Select a Drink Database to Open", FileDialog.LOAD);
		dialog.setVisible(true);
		filename = dialog.getFile();
		try
		{
			model = DrinkDB.loadDatabase(filename);
		}
		catch(Throwable t)
		{
			message = new String("Error opening Database!");
			JOptionPane.showMessageDialog(null,message,
				"DB Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void doSaveAs()
	{
		String filename;
		String message;
		boolean succ;

		FileDialog dialog = new FileDialog(Frame.getFrames()[0],
			"Select a Drink Database to Save", FileDialog.SAVE);
		dialog.setVisible(true);
		filename = dialog.getFile();

		if(filename == null)
		{
			return;
		}
		succ = model.saveDatabase(filename);
		if(succ == false)
		{
			message = new String("Error Saving Database!");
				JOptionPane.showMessageDialog(null,message,
					"DB Error!", JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void doPrint()
	{
		PrinterJob pjob = PrinterJob.getPrinterJob();

		if(!pjob.printDialog())
		{
			return;
		}

		pjob.defaultPage();
		pjob.setPrintable(this);

		try
		{
			pjob.print();
		}
		catch(Exception e)
		{
		}
	}

	protected void doSave()
	{
		String message;
		if(!model.saveDatabase("SavedDatabase.db"))
		{
			message = new String("Error Saving Database!");
			JOptionPane.showMessageDialog(null,message,
				"DB Error!", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			message = new String("Database Saved");
			JOptionPane.showMessageDialog(null,message,
				"Saving...", JOptionPane.INFORMATION_MESSAGE);
		}
  	}

  	protected void doEdit()
	{
		JFrame f = new JFrame("Drink Mixer: Drink editor");
		try
		{
			DrinkEditForm panel = new DrinkEditForm(f,model,
				(Drink)drinkList.getSelectedValue());
			f.getContentPane().add(panel);
			f.pack();
			f.setVisible(true);
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			JOptionPane.showMessageDialog(this,
								"There is no drink selected to edit!");
		}
	}

	protected void doAdd()
	{
	    JFrame f = new JFrame("Drink Mixer: Drink Creator");
		DrinkEditForm panel = new DrinkEditForm(f,model);
		f.getContentPane().add(panel);
		f.pack();
		f.setVisible(true);
	}

  	protected void doAbout()
	{
		Disclaimer d = new Disclaimer();
		String message = d.toString();

		JOptionPane.showMessageDialog(this, message, "About:" ,
			JOptionPane.INFORMATION_MESSAGE);
	}

	public void update(Observable o, Object arg)
	{
		int index;

		index = drinkList.getSelectedIndex();
		drinkList.setListData(model.getDrinks(DrinkComparators.NAME).toArray());
		drinkList.setSelectedIndex(index);
	}

	protected class DrinkListListener
		implements ListSelectionListener
	{
		public void valueChanged(ListSelectionEvent e)
		{
			String drinkText;
			Drink curDrink;
			String drinkString;

			curDrink = (Drink)drinkList.getSelectedValue();
			if(curDrink == null)
			{
				drinkList.setSelectedIndex(-1);
			}
			else
			{
				drinkString = curDrink.toString();
				drinkString = drinkString.substring(0,
						drinkString.indexOf("Ingredients:"));
				drinkTextArea.setText(drinkString.trim());

				ingredientList.setListData(curDrink.getIngredients());
				directionsArea.setText(curDrink.getDirections());

				String glassName = curDrink.getGlassType();
				int ingNum = curDrink.getIngredients().size();

				imagePanel.changeDrink(glassName, ingNum);
				imagePanel.repaint();
			}
		}
	}

	protected class IngredientCellRenderer
		extends DefaultListCellRenderer
	{
		public Component getListCellRendererComponent(JList list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus)
		{
			Component c = super.getListCellRendererComponent(list,
						value, index, false, false);

			c.setForeground(colors[index % 6]);
			return c;
		}

	}

	protected void doChangeSort()
	{
		DrinkComparator comparator = (DrinkComparator)
										sortComboBox.getSelectedItem();
		drinkList.setListData(new Vector(model.getDrinks(comparator)));
		try
		{
			drinkList.setSelectedIndex(0);
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
		}
	}

	protected void doExit()
	{
		int exitVal = model.exit(this);
		if(exitVal == model.SAFE_TO_EXIT)
		{
			System.exit(0);
		}
	}

	public String getName()
	{
		return "Browse Database";
	}

	public void searchReturned(java.util.List l)
	{
		drinkList.setListData(l.toArray());
		try
		{
		drinkList.setSelectedIndex(0);
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
		}
	}

	public int print(Graphics graphics, PageFormat pf, int pageIndex)
		throws PrinterException
	{
		int y = 90;
		Drink curDrink;
		String drinkStr;
		String curLine;
		int index1 = 0;
		int index2 = 0;

		if (pageIndex >= 1)
		{
			return Printable.NO_SUCH_PAGE;
		}

		curDrink = (Drink)drinkList.getSelectedValue();
		drinkStr = curDrink.toString();

		while(y + 12 < pf.getImageableY()+pf.getImageableHeight())
		{
			index2 = drinkStr.indexOf("\n" ,index1);
			if(index2 == -1)
			{
				break;
			}
			curLine = drinkStr.substring(index1, index2);

			if((index2 - index1) > 2)
			{
  				graphics.drawString(curLine,100,y);
			}
			index1 = index2 + 1;
			y = y + 12;
		}

  	  	return Printable.PAGE_EXISTS;
	}

	protected void doNewVer()
	{
		try
		{
		Drink foo = (Drink)drinkList.getSelectedValue();
		Drink bar = new Drink(foo);
		bar.setVersion(bar.getVersion() + 1);
		model.addDrink(bar);
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			JOptionPane.showMessageDialog(this,
					"There is no drink selected to make a new version of!");
		}
	}

	protected void doDelete()
	{
		int c;
		try
		{
			Drink d = (Drink)drinkList.getSelectedValue();

			c = JOptionPane.showConfirmDialog(this, 
					"Are you sure you want to remove " + d.getName() + "?", 
					"Question", JOptionPane.YES_NO_OPTION);

			if(c == JOptionPane.YES_OPTION)
			{
				model.removeDrink(d);
			}
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			JOptionPane.showMessageDialog(this,
					"There is no drink selected to delete!");
		}
	}

}
