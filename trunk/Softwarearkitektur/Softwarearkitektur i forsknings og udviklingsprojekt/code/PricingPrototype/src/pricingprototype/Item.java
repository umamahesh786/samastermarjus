package pricingprototype;

import java.util.Currency;

public class Item implements IItem {
	protected String itemName;
	protected Currency currency;
	protected String itemType;
	protected double price;
	
	public Item(String itemName, Currency currency, String itemType, double price)
	{
		this.itemName = itemName;
		this.currency = currency;
		this.itemType = itemType;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
