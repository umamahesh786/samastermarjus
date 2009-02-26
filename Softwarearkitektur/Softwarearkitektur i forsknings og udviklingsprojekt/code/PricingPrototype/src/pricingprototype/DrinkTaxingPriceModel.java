package pricingprototype;

public class DrinkTaxingPriceModel implements IPricingModel {
	public double getPrice(IItem item) {
		if(item.getItemType().equals("Softdrink")) return (item.getPrice() * 1.00);
		if(item.getItemType().equals("LowAlcohol")) return (item.getPrice() * 1.10);
		if(item.getItemType().equals("MedAlcohol")) return (item.getPrice() * 1.20);
		if(item.getItemType().equals("HighAlcohol")) return (item.getPrice() * 1.30);
		return item.getPrice();
	}
}
