package com.zaghir.weatherapi.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class Rain implements Serializable {

	private Double h1;
	private Double h3;
	
	public Rain() {
	}
	
	public Rain(Double h1, Double h3) {
		super();
		this.h1 = h1;
		this.h3 = h3;
	}
	
	public Double getH1() {
		return h1;
	}
	public void setH1(Double h1) {
		this.h1 = h1;
	}
	public Double getH3() {
		return h3;
	}
	public void setH3(Double h3) {
		this.h3 = h3;
	}

	@Override
	public String toString() {
		return "Rain [h1=" + h1 + ", h3=" + h3 + "]";
	}
	
}
