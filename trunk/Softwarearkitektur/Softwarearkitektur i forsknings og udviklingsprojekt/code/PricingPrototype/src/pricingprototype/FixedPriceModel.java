package pricingprototype;

public class FixedPriceModel implements IPricingModel {
	public double getPrice(IItem item) {
		return (item.getPrice() * 1.50);
	}
}
