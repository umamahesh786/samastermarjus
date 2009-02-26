package pricingprototype;

import java.util.Currency;

public interface ICurrencyConverter {
	public float convert(float amount, Currency from, Currency to);
}
