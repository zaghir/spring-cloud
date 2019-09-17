package com.zaghir.weatherapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class WeatherApiApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherApiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherApiApplication.class, args);
		LOGGER.info("### WeatherApiApplication  is running###");
	}

}
