package com.idealo.checkout.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SpecialPriceRule implements PricingRule {

	final int percentageVal = 100;
	HashMap<String, Integer> countMap = new HashMap<>();
	HashMap<String, Double> productPriceMap = new HashMap<>();
	HashSet<String> dateDiscountSet = new HashSet<>();
	
	SimpleDateFormat sdfo = new SimpleDateFormat("dd.MM.yyyy");
	HashMap<Product, SpecialDiscountRule> rule = new HashMap<>();

	SpecialPriceRule() throws ParseException {
		SimpleDateFormat sdfo = new SimpleDateFormat("dd.MM.yyyy");

		rule.put(new Product("A", 40), new SpecialDiscountRule("A", 3, 40, 100, null, 0));
		rule.put(new Product("B", 50), new SpecialDiscountRule("B", 2, 50, 80, null, 0));
		rule.put(new Product("C", 20), new SpecialDiscountRule("C", 0, 25, 0, null, 0));
		rule.put(new Product("D", 50), new SpecialDiscountRule("D", 0, 20, 100, null, 0));
		rule.put(new Product("E", 100), new SpecialDiscountRule("E", 0, 100, 0, sdfo.parse("27.11.2020"), 10));
	}

	@Override
	public void scan(String item) {
		Date date = new Date();
		String currentDate = sdfo.format(date);

		if (countMap.containsKey(item)) {
			countMap.put(item, countMap.get(item) + 1);

			rule.entrySet().stream().forEach(productMap -> {
				if (productMap.getKey().getName().equals(item)) {

					if (productMap.getValue().getQuantity() != 0) {
						if (countMap.get(item) % productMap.getValue().getQuantity() == 0) {
							productPriceMap.put(item, productMap.getValue().getPriceAtTheQuantity()
									* (countMap.get(item) / productMap.getValue().getQuantity()));
						} else {
							productPriceMap.put(item, productPriceMap.get(item) + productMap.getValue().getPrice());
						}
					} else {
						productPriceMap.put(item, productMap.getValue().getPrice() * countMap.get(item));
						try {
							if (productMap.getValue().getDiscountDay() != null
									&& productMap.getValue().getPercent() != 0) {
								if (productMap.getValue().getDiscountDay().compareTo(sdfo.parse(currentDate)) == 0) {
									double val = (productMap.getValue().getPrice() * countMap.get(item))
											- (((productMap.getValue().getPrice() * countMap.get(item))
													* productMap.getValue().getPercent()) / percentageVal);
									productPriceMap.put(item, val);
								}
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			});
		} else {
			countMap.put(item, 1);
			rule.entrySet().stream().forEach(productMap -> {
				if (productMap.getKey().getName().equals(item)) {
					productPriceMap.put(item, productMap.getValue().getPrice());
					try {
						if (productMap.getValue().getDiscountDay() != null && productMap.getValue().getPercent() != 0) {
							if (productMap.getValue().getDiscountDay().compareTo(sdfo.parse(currentDate)) == 0) {
								double val = productMap.getValue().getPrice()
										- ((productMap.getValue().getPrice() * productMap.getValue().getPercent())
												/ percentageVal);
								productPriceMap.put(item, val);
							}
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}

				}
			});
		}
	}

	@Override
	public double total() {
		Double[] total = { (double) 0 };
//		countMap.entrySet().stream().forEach(a ->{
//			System.out.println("count key --> "+a.getKey()+" count value -->" +a.getValue());
//		});
		productPriceMap.entrySet().stream().forEach(price -> {
			//System.out.println("key -->" + price.getKey() + " value -->" + price.getValue());
			total[0] += price.getValue().doubleValue();
		});
//		System.out.println("the total value is -->" + total[0]);
//		System.out.println("------------------------------------");
		return total[0];
	}

}
