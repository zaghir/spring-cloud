package com.zaghir.weatherapi.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class Clouds implements Serializable{
	
	private Double all;

	public Clouds(){
		
	}
	public Clouds(Double all) {
		super();
		this.all = all;
	}

	public Double getAll() {
		return all;
	}

	public void setAll(Double all) {
		this.all = all;
	}
	
	@Override
	public String toString() {
		return "Clouds [all=" + all + "]";
	}
	

}
