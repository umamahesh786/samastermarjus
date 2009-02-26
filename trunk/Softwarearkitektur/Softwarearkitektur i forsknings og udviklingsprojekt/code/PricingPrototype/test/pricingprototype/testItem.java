package pricingprototype;

import java.util.Currency;

import junit.framework.TestCase;

public class testItem extends TestCase 
{
	protected void setUp()
	{
	}
	
	public void testCreateItem()
	{
		IItem item = new Item("Name", Currency.getInstance("DKK"), "SomeItem", 1.12);
		assertEquals("Name", item.getItemName());
		assertEquals(1.12, item.getPrice());
	}
	

}
