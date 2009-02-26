package pricingprototype.inheritance;

import java.util.Calendar;

import pricingprototype.IItem;
public class TimePriceClass extends FixedPriceClass {
	public double getPrice(IItem item) {
		Double price = super.getPrice(item);
		Calendar cal = Calendar.getInstance();
		int dow = cal.get(Calendar.DAY_OF_WEEK);
		int hod = cal.get(Calendar.HOUR_OF_DAY);
		switch(dow)
		{
		case Calendar.MONDAY:
		case Calendar.TUESDAY:
		case Calendar.WEDNESDAY:
			return item.getPrice(); // Normal Price
		case Calendar.THURSDAY:
			if((hod >= 16) && (hod <= 17))
				return price * 0.5; //Happy hour
			else
				return price;
		case Calendar.FRIDAY:
			if(hod <= 19)
				return price * 0.75;
			else if(hod <= 22)
				return price * 1.50;
			else
				return price * 1.75;
		case Calendar.SATURDAY:
			if(hod <= 5)
				return price * 1.75;
			else 
				return price * 2.00;
		case Calendar.SUNDAY:
			if(hod <= 5)
				return price * 2.00;
			else 
				return price;
		}
		return price;
	}
}
