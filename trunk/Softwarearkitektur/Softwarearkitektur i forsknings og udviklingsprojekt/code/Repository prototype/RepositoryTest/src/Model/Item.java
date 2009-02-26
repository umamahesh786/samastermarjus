package Model;

import java.util.Currency;
import java.util.UUID;

public class Item implements DomainObject {

	private int quantity=0;
	private String displayName;
	private UUID id;
	private String itemCategory="";
	private double price = Double.NaN;
	private Currency currency;
	
	public Item(String displayName, int quantity) {
		this(true);
		this.setDisplayName(displayName);
		this.setQuantity(quantity);
	}
	
	public Item(String displayName, int quantity, Boolean generateId) {
		this(generateId);
		this.setDisplayName(displayName);
		this.setQuantity(quantity);
			
	}
	
	public Item(Boolean generateId)
	{
		if(generateId)
		{
			this.id = UUID.randomUUID();
		}
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	public  Item(UUID id)
	{
		this.id = id;
	}

	
	
	
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	@Override
	public UUID getIdentifier() {
		
		return this.id;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * @param itemCategory the itemCategory to set
	 */
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	/**
	 * @return the itemCategory
	 */
	public String getItemCategory() {
		return this.itemCategory;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}
	
	
}
