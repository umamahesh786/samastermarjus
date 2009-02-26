package pricingprototype.inheritance;

import pricingprototype.IItem;
public class FixedPriceClass extends BaseClass {
	public double getPrice(IItem item) {
		return (item.getPrice() * 1.5);
	}
	public double getSecretPrice() {
		return 5.0;
	}
	
}
