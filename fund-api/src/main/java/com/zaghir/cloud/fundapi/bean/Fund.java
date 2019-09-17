package com.zaghir.cloud.fundapi.bean;

import java.math.BigDecimal;

public class Fund {
	
	private String name ;
	private BigDecimal price;
	private String currency ;
	private Integer port;
	
	public Fund(){
		
	}
	public Fund(String name, BigDecimal price, String currency) {
		super();
		this.name = name;
		this.price = price;
		this.currency = currency;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "Fund [name=" + name + ", price=" + price + ", currency=" + currency + ", port=" + port + "]";
	}
	
}
