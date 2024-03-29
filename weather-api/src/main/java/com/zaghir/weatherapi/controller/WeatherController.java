package com.zaghir.weatherapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zaghir.weatherapi.bean.ResultApiWeather;
import com.zaghir.weatherapi.configuration.ConfigurationWeatherApi;

import reactor.core.publisher.Flux;

@RestController
public class WeatherController {
	
	@Autowired
	private ConfigurationWeatherApi configurationWeatherApi;
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherController.class);
	
	private static final String URL_WEATHER_API="http://api.openweathermap.org/data/2.5";
	
	
//	@GetMapping("/weather")
//	public ResultApiWeather getWeather(){
//		ResultApiWeather r = new  ResultApiWeather();
//		r.setCoord(new Coord(12.2 ,16.2));
//		r.setWeather(new ArrayList<Weather>());
//		r.setBase("stations");
//		r.setWind(new Wind(1.5,300.0));
//		return r ;
//	}
	@GetMapping("/weather/city/{city}")
	//@HystrixCommand(defaultFallback="fallToleranceGetWeather")
	public ResultApiWeather getWeather(@PathVariable String city){	
		//http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=1970d4a97a00c1ed84ae8bb36a712c20
		//api.openweathermap.org/data/2.5/find?q=casablanca&units=metric&lang=fr&APPID=1970d4a97a00c1ed84ae8bb36a712c20
//		WebClient webClient = WebClient.create("api.openweathermap.org");
//		webClient.head().attribute("APPID", "1970d4a97a00c1ed84ae8bb36a712c20");
//		Flux<ResultApiWeather> result = webClient.get()
//	            .uri("/data/2.5/find?q=casablanca&units=metric&lang=fr")
//	            .accept(MediaType.APPLICATION_JSON)
//	            .retrieve().bodyToFlux(ResultApiWeather.class);
		WebClient.Builder webClientBuilder = WebClient.builder();
		ResultApiWeather weather = null ;
		try{
			weather = webClientBuilder.baseUrl(URL_WEATHER_API).build()
					.get()				
					.uri(uriBuilder -> uriBuilder
						    .path("/find")
						    .queryParam("q", city)
						    .queryParam("units", "metric")
						    .queryParam("lang", "fr")
						    .queryParam("APPID",  "1970d4a97a00c1ed84ae8bb36a712c20")
						    .build())
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(ResultApiWeather.class)				
					.block();

		}catch(Exception ex){
			LOGGER.error(" getWeather() ==>  {}" , ex);
		}
		

		LOGGER.info("ConfigurationWeatherApi:getWeather() result ==> {}",weather);
		return weather ;

	}
	
	public ResultApiWeather fallToleranceGetWeather(){
		ResultApiWeather result = new ResultApiWeather();
		LOGGER.error("ConfigurationWeatherApi fallToleranceGetWeather() error ==> {}"  );
		result.setName("error");
		return result ;
	}
	@GetMapping("/weather/apiKey")
	public String getApiKey(){
		return configurationWeatherApi.getKey();
	}
	
	@GetMapping("/weather/fault-tolerance-example")
	@HystrixCommand(defaultFallback="fallToleranceGetApiKey")
	public ResultApiWeather retriveConfiguration() {
		throw new RuntimeException();		
	}

	/**
	 * methode utilisée en mode degrader 
	 * hystrix fait un resoutage vers cette methode et bloc l'utilisation de la methode en probleme
	 * @return
	 */
	public String fallToleranceGetApiKey(){
		return "00000000000000000000000000";
	}
}
