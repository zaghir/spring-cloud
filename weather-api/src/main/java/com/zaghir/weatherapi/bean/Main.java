package com.zaghir.weatherapi.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class Main implements Serializable{
	
	private Double temp;
	private Double pressure;
	private Double humidity;
	private Double temp_min;
	private Double temp_max;
	private Double sea_level;
	private Double grnd_level;
	public Main(){
		
	}
	public Main(Double temp, Double pressure, Double humidity, Double temp_min, Double temp_max, Double sea_level,
			Double grnd_level) {
		super();
		this.temp = temp;
		this.pressure = pressure;
		this.humidity = humidity;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.sea_level = sea_level;
		this.grnd_level = grnd_level;
	}
	public Double getTemp() {
		return temp;
	}
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	public Double getPressure() {
		return pressure;
	}
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public Double getTemp_min() {
		return temp_min;
	}
	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}
	public Double getTemp_max() {
		return temp_max;
	}
	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}
	public Double getSea_level() {
		return sea_level;
	}
	public void setSea_level(Double sea_level) {
		this.sea_level = sea_level;
	}
	public Double getGrnd_level() {
		return grnd_level;
	}
	public void setGrnd_level(Double grnd_level) {
		this.grnd_level = grnd_level;
	}
	@Override
	public String toString() {
		return "Main [temp=" + temp + ", pressure=" + pressure + ", humidity=" + humidity + ", temp_min=" + temp_min
				+ ", temp_max=" + temp_max + ", sea_level=" + sea_level + ", grnd_level=" + grnd_level + "]";
	}

	
}
