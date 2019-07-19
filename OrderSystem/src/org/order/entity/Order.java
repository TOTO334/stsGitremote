package org.order.entity;

public class Order {

	private int id;
	private String name;
	private int num;
	private float price;

	public Order() {

	}

	public Order(int id, String name, int num, float price) {
		this.id = id;
		this.name = name;
		this.num = num;
		this.price = price;
	}

	public Order(String name, int num, float price) {
		this.name = name;
		this.num = num;
		this.price = price;
	}

	public String[] toStringArray() {
		return new String[] { this.id + "", this.name, this.num + "", this.price + "" };
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "id=" + this.id + ",name=" + this.name + ",num=" + this.num + ",price=" + this.price;
	}
}
