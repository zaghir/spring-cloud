package com.zaghir.weatherapi.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class Weather implements Serializable{
	
	private Integer id ;
	private String name ;
	private String description;
	private String icon ;
	
	
	

}
