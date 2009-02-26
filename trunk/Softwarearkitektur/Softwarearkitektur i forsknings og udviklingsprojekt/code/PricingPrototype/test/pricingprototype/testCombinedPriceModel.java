package pricingprototype;

import java.util.Calendar;
import java.util.Currency;

import junit.framework.TestCase;

public class testCombinedPriceModel extends TestCase 
{
	protected void setUp()
	{
	}
	
	public void testCreateItem()
	{
		IPricingModel model = new CombinedPricingModel();
		assertNotNull(model);
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
	}
	
	public void testUnknownTypePrice()
	{
		IPricingModel model = new CombinedPricingModel();
		assertNotNull(model);
		IDayTimeManipulator dtm = (IDayTimeManipulator) model;
		dtm.setDayTime(Calendar.THURSDAY, 17); //1 = Sunday .. 7 = Saturday
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "SomeItem", 20.0);
		assertEquals(10.0, pc.execute(item));
	}
	
	public void testKnownType()
	{
		IPricingModel model = new CombinedPricingModel();
		assertNotNull(model);
		IDayTimeManipulator dtm = (IDayTimeManipulator) model;
		dtm.setDayTime(Calendar.SUNDAY, 1); //1 = Sunday .. 7 = Saturday
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "MedAlcohol", 20.0);
		assertEquals(48.0, pc.execute(item));
	}
	public void testKnowntypeII()
	{
		IPricingModel model = new CombinedPricingModel();
		assertNotNull(model);
		IDayTimeManipulator dtm = (IDayTimeManipulator) model;
		dtm.setDayTime(Calendar.THURSDAY, 17); //1 = Sunday .. 7 = Saturday
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "HighAlcohol", 20.0);
		assertEquals(13.0, pc.execute(item));
	}
	public void testChangeModel()
	{
		IItem item = new Item("Name", Currency.getInstance("DKK"), "HighAlcohol", 20.0);
		IPricingModel model;
		double price;
		
		model = new CombinedPricingModel();
		price = model.getPrice(item);
		
		model = new FixedPriceModel();
		price = model.getPrice(item);
		
		model = new FixedPriceModel();
		price = model.getPrice(item);
		
		model = new DrinkTimePriceModel();
		price = model.getPrice(item);
	}
}
