package com.zaghir.weatherapi.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class Sys implements Serializable{
	
	private String type ;
	private Integer id ;
	private String message ;
	private String country;
	private Timestamp sunrise ;
	private Timestamp sunset ;
	
	public Sys(){
		
	}
	
	public Sys(String type, Integer id, String message, String country, Timestamp sunrise, Timestamp sunset) {
		super();
		this.type = type;
		this.id = id;
		this.message = message;
		this.country = country;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getSunrise() {
		return sunrise;
	}

	public void setSunrise(Timestamp sunrise) {
		this.sunrise = sunrise;
	}

	public Timestamp getSunset() {
		return sunset;
	}

	public void setSunset(Timestamp sunset) {
		this.sunset = sunset;
	}

	@Override
	public String toString() {
		return "Sys [type=" + type + ", id=" + id + ", message=" + message + ", country=" + country + ", sunrise="
				+ sunrise + ", sunset=" + sunset + "]";
	}
	
	
	
}
