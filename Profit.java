package model;

public class Profit {
  
private int count;//количество проданного товара
  private double price;//общая стоимость(сумма) товара
  
  public Profit(int count, double price) {
		super();
		this.count = count;
		this.price = price;
	}

public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

@Override
public String toString() {
	return " Total " + count + 
			" book(s) worth: " + price;
}


}


