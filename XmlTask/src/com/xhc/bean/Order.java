package com.xhc.bean;

public class Order {
	private double total;
	private int sums;
	public Order(double total, int sums) {
		this.total = total;
		this.sums = sums;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getSums() {
		return sums;
	}
	public void setSums(int sums) {
		this.sums = sums;
	}
	
}
