package com.idealo.checkout.system;


public interface PricingRule {
	void scan(String item);
	double total();

}
