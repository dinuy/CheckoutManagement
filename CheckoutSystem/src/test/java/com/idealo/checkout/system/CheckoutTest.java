package com.idealo.checkout.system;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

/**
 * Unit test for Checkout Management System.
 */
public class CheckoutTest {

	public double calculatePrice(String goods) throws ParseException {
		PricingRule priceRule = new SpecialPriceRule();
		Checkout checkout = new Checkout(priceRule);
		for (int i = 0; i < goods.length(); i++) {
			checkout.scan(String.valueOf(goods.charAt(i)));
		}
		return checkout.total();
	}

	@Test
	public void totals() throws ParseException {
		assertEquals(0, calculatePrice(""), 0);
		assertEquals(50, calculatePrice("CC"), 0);
		assertEquals(505, calculatePrice("ABAABEECE"), 0);
		assertEquals(240, calculatePrice("AAAAE"), 0);
		assertEquals(300, calculatePrice("EEE"), 0);
		assertEquals(40, calculatePrice("A"), 0);
		assertEquals(80, calculatePrice("AA"), 0);
		assertEquals(90, calculatePrice("AB"), 0);
		assertEquals(135, calculatePrice("CDBA"), 0);
		assertEquals(40, calculatePrice("A"), 0);
		assertEquals(90, calculatePrice("AB"), 0);
		assertEquals(135, calculatePrice("CDBA"), 0);

		assertEquals(80, calculatePrice("AA"), 0);
		assertEquals(100, calculatePrice("AAA"), 0);
		assertEquals(140, calculatePrice("AAAA"), 0);
		assertEquals(180, calculatePrice("AAAAA"), 0);
		assertEquals(200, calculatePrice("AAAAAA"), 0);

		assertEquals(150, calculatePrice("AAAB"), 0);
		assertEquals(180, calculatePrice("AAABB"), 0);
		assertEquals(200, calculatePrice("AAABBD"), 0);
		assertEquals(200, calculatePrice("DABABA"), 0);

		assertEquals(190, calculatePrice("AECC"), 0);

	}

	@Test
	public void incremental() throws ParseException {
		PricingRule priceRule = new SpecialPriceRule();
		Checkout checkout = new Checkout(priceRule);

		checkout.scan("A");
		assertEquals(40, checkout.total(), 0);
		checkout.scan("B");
		assertEquals(90, checkout.total(), 0);
		checkout.scan("A");
		assertEquals(130, checkout.total(), 0);
		checkout.scan("A");
		assertEquals(150, checkout.total(), 0);
		checkout.scan("B");
		assertEquals(180, checkout.total(), 0);
		checkout.scan("E");
		assertEquals(280, checkout.total(), 0);
		checkout.scan("E");
		assertEquals(380, checkout.total(), 0);
		checkout.scan("C");
		assertEquals(405, checkout.total(), 0);
		checkout.scan("C");
		assertEquals(430, checkout.total(), 0);

	}
}
