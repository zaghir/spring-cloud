package com.zaghir.cloud.currencyapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class CurrencyApiApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyApiApplication.class); 
	public static void main(String[] args) {
		SpringApplication.run(CurrencyApiApplication.class, args);
		LOGGER.info("### CurrencyApiApplication  is running###");
	}

}
