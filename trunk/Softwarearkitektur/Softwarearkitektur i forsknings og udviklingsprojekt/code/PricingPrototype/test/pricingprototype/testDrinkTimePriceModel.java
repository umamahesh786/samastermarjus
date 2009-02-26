package pricingprototype;

import java.util.Calendar;
import java.util.Currency;

import junit.framework.TestCase;

public class testDrinkTimePriceModel extends TestCase 
{
	protected void setUp()
	{
	}
	
	public void testCreateItem()
	{
		IPricingModel model = new DrinkTimePriceModel();
		assertNotNull(model);
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
	}
	
	public void testMondayPrice()
	{
		IPricingModel model = new DrinkTimePriceModel();
		assertNotNull(model);
		IDayTimeManipulator dtm = (IDayTimeManipulator) model;
		dtm.setDayTime(Calendar.MONDAY, 0); //1 = Sunday .. 7 = Saturday
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "SomeItem", 20.0);
		assertEquals(20.0, pc.execute(item));
	}
	
	public void testHappyHour()
	{
		IPricingModel model = new DrinkTimePriceModel();
		assertNotNull(model);
		IDayTimeManipulator dtm = (IDayTimeManipulator) model;
		dtm.setDayTime(Calendar.THURSDAY, 17); //1 = Sunday .. 7 = Saturday
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "SomeItem", 20.0);
		assertEquals(10.0, pc.execute(item));
	}
	public void testSundayMorning()
	{
		IPricingModel model = new DrinkTimePriceModel();
		assertNotNull(model);
		IDayTimeManipulator dtm = (IDayTimeManipulator) model;
		dtm.setDayTime(Calendar.SUNDAY, 1); //1 = Sunday .. 7 = Saturday
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "SomeItem", 20.0);
		assertEquals(40.0, pc.execute(item));
	}
}
