package com.zaghir.weatherapi.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class Snow implements Serializable{
	
	private Double all;

	public Snow(){
		
	}
	public Snow(Double all) {
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
