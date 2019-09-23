package com.zaghir.weatherapi.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @ToString @NoArgsConstructor
public class Pload implements Serializable{
	private Integer id ;
	private String name ;
	private Coord coord ;
	private Main main ;
	private Integer dt ;
	private Wind wind ;
	private Sys sys;
	private Clouds clouds ;
	//private Weather weather ;
	private Rain rain ;
	private Snow snow ;

}
