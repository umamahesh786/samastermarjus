package pricingprototype;

public class MultiCombinedPricingModel implements IPricingModel {
	protected IPricingModel priceModel;
	protected IPricingModel taxModel;
	protected IPricingModel fixedModel;
	
	public MultiCombinedPricingModel()
	{
		priceModel = new DrinkTimePriceModel();
		taxModel = new DrinkTaxingPriceModel();
		fixedModel = new FixedPriceModel();
	}

	public double getPrice(IItem item) {
		Item temp = new Item(item.getItemName(), 
				 			 item.getCurrency(), 
				 			 item.getItemType(), 
				 			 item.getPrice());
		temp.setPrice(priceModel.getPrice(temp));
		temp.setPrice(taxModel.getPrice(temp));
		temp.setPrice(fixedModel.getPrice(temp));
		
		return temp.getPrice();
	}

}
