package pricingprototype;

import java.util.Currency;

public interface IItem {
	String getItemName();
	String getItemType();
	double  getPrice();
	Currency getCurrency();
}
