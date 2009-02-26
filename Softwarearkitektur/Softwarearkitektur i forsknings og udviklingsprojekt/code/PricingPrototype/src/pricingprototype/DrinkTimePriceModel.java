package pricingprototype;

import java.util.Calendar;
public class DrinkTimePriceModel implements IPricingModel, IDayTimeManipulator {
	protected int dayOfWeek;
	protected int hourOfDay;
	
	public DrinkTimePriceModel()
	{
		dayOfWeek = -1;
		hourOfDay = -1;
	}
	
	public double getPrice(IItem item) {
		Calendar cal = Calendar.getInstance();
		int dow;
		if (dayOfWeek >= 0)
			dow = dayOfWeek;
		else
		    dow = cal.get(Calendar.DAY_OF_WEEK);
		int hod;
		if(hourOfDay >= 0)
			hod = hourOfDay;
		else 
			hod = cal.get(Calendar.HOUR_OF_DAY);
		
		switch(dow)
		{
		case Calendar.MONDAY:
		case Calendar.TUESDAY:
		case Calendar.WEDNESDAY:
			return item.getPrice(); // Normal Price
		case Calendar.THURSDAY:
			if((hod >= 16) && (hod <= 17))
				return item.getPrice() * 0.5; //Happy hour
			else
				return item.getPrice();
		case Calendar.FRIDAY:
			if(hod <= 19)
				return item.getPrice() * 0.75;
			else if(hod <= 22)
				return item.getPrice() * 1.50;
			else
				return item.getPrice() * 1.75;
		case Calendar.SATURDAY:
			if(hod <= 5)
				return item.getPrice() * 1.75;
			else 
				return item.getPrice() * 2.00;
		case Calendar.SUNDAY:
			if(hod <= 5)
				return item.getPrice() * 2.00;
			else 
				return item.getPrice();
		}
		return item.getPrice();
	}

	public void setDayTime(int dayOfWeek, int hourOfDay) {
		this.dayOfWeek = dayOfWeek;
		this.hourOfDay = hourOfDay;
	}
}
