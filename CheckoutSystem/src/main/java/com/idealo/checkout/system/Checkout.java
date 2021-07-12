package com.idealo.checkout.system;

public class Checkout {
	PricingRule rule;

	Checkout(PricingRule rule) {
		if (rule instanceof SpecialPriceRule)
			this.rule = rule;
		else
			this.rule = rule;
	}

	public void scan(String item) {
		rule.scan(item);
	}

	public double total() {
		return rule.total();
	}

}
