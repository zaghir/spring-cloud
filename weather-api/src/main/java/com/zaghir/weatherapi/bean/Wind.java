package com.zaghir.weatherapi.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class Wind implements Serializable{
	private Double speed ;
	private Double deg ;
	
	public Wind(){
		
	}
	
	public Wind(Double speed, Double deg) {
		super();
		this.speed = speed;
		this.deg = deg;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getDeg() {
		return deg;
	}

	public void setDeg(Double deg) {
		this.deg = deg;
	}

	@Override
	public String toString() {
		return "Wind [speed=" + speed + ", deg=" + deg + "]";
	}
	
	
}
