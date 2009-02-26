package pricingprototype;

public class CombinedPricingModel implements IPricingModel, IDayTimeManipulator {
	protected IPricingModel priceModel;
	protected IPricingModel taxModel;
	
	public CombinedPricingModel()
	{
		priceModel = new DrinkTimePriceModel();
		taxModel = new DrinkTaxingPriceModel();
	}

	public double getPrice(IItem item) {
		Item temp = new Item(item.getItemName(), 
				 item.getCurrency(), 
				 item.getItemType(), 
				 priceModel.getPrice(item));
		
		return taxModel.getPrice(temp);
	}

	@Override
	public void setDayTime(int dayOfWeek, int hourOfDay) {
		((IDayTimeManipulator)priceModel).setDayTime(dayOfWeek, hourOfDay);
	}
}
