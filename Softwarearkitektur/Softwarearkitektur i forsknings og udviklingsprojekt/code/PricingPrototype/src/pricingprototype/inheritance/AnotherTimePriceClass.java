package pricingprototype.inheritance;

import java.util.Calendar;

import pricingprototype.IItem;
public class AnotherTimePriceClass extends FixedPriceClass {
	public double getPrice(IItem item) {
		Double price = super.getPrice(item);
		Calendar cal = Calendar.getInstance();
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		switch(dow)
		{
		case Calendar.MONDAY:
		case Calendar.TUESDAY:
		case Calendar.WEDNESDAY:
			return item.getPrice(); // Normal Price
		case Calendar.THURSDAY:
		case Calendar.FRIDAY:
		case Calendar.SATURDAY:
		case Calendar.SUNDAY:
			return price * 2.00;
		}
		return price;
	}
}
