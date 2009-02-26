package pricingprototype;

public class PricingContext {
	protected IPricingModel strategy;
	
	public PricingContext(IPricingModel strategy)
	{
		this.strategy = strategy;
	}
	
	double execute(IItem item)
	{
		return strategy.getPrice(item);
	}
}
