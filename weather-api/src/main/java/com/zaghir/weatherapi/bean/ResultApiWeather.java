package com.zaghir.weatherapi.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class ResultApiWeather {
		
	
	//private List<Weather> weather = new ArrayList<Weather>();
	private String base;
	
	private Integer visibility;	
	private Integer timezone;
	private Integer id;
	private String name;
	
	private String message;
	private Integer cod;
	private Integer count ;
	private List<Pload> list = new ArrayList<Pload>();	
	
	
}
