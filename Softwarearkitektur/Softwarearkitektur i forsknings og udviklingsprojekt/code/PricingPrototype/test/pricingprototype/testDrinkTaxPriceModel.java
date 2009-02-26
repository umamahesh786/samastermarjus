package pricingprototype;

import java.util.Currency;

import junit.framework.TestCase;

public class testDrinkTaxPriceModel extends TestCase 
{
	protected void setUp()
	{
	}
	
	public void testCreateItem()
	{
		IPricingModel model = new DrinkTaxingPriceModel();
		assertNotNull(model);
		PricingContext pc = new PricingContext(model);
		assertNotNull(pc);
	}
	
	public void testUnknownTypePrice()
	{
		PricingContext pc = new PricingContext(new DrinkTaxingPriceModel());
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "SomeItem", 20.0);
		assertEquals(20.0, pc.execute(item));
	}
	
	public void testKnownType()
	{
		PricingContext pc = new PricingContext(new DrinkTaxingPriceModel());
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "MedAlcohol", 20.0);
		assertEquals(24.0, pc.execute(item));
	}
	public void testKnowntypeII()
	{
		PricingContext pc = new PricingContext(new DrinkTaxingPriceModel());
		assertNotNull(pc);
		IItem item = new Item("Name", Currency.getInstance("DKK"), "HighAlcohol", 20.0);
		assertEquals(26.0, pc.execute(item));
	}
}
