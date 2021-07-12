package com.idealo.checkout.system;


import java.util.Date;

public class SpecialDiscountRule{
	String product;
	int quantity;
	double price;
	double priceAtTheQuantity;
	Date discountDay;
	double percent;
	
	SpecialDiscountRule(String product, int quantity, double price, double priceAtTheQuantity, Date discountDay, double percent){
		this.product=product;
		this.quantity=quantity;
		this.price=price;
		this.priceAtTheQuantity=priceAtTheQuantity;
		this.discountDay=discountDay;
		this.percent=percent;
	}

	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPriceAtTheQuantity() {
		return priceAtTheQuantity;
	}
	public void setPriceAtTheQuantity(double priceAtTheQuantity) {
		this.priceAtTheQuantity = priceAtTheQuantity;
	}
	public Date getDiscountDay() {
		return discountDay;
	}
	public void setDiscountDay(Date discountDay) {
		this.discountDay = discountDay;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discountDay == null) ? 0 : discountDay.hashCode());
		long temp;
		temp = Double.doubleToLongBits(percent);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(priceAtTheQuantity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + quantity;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecialDiscountRule other = (SpecialDiscountRule) obj;
		if (discountDay == null) {
			if (other.discountDay != null)
				return false;
		} else if (!discountDay.equals(other.discountDay))
			return false;
		if (Double.doubleToLongBits(percent) != Double.doubleToLongBits(other.percent))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(priceAtTheQuantity) != Double.doubleToLongBits(other.priceAtTheQuantity))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}
