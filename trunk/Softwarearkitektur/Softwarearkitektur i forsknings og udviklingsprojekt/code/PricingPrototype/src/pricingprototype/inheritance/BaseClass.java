package pricingprototype.inheritance;

import pricingprototype.IItem;
public class BaseClass {
	public double getPrice(IItem item) {
		return (item.getPrice());
	}
}
