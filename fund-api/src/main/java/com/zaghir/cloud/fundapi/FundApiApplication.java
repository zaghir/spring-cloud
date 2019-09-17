package com.zaghir.cloud.fundapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
/**  
 * @author yzaghir
 * activation de FeignClient() en  parametre le package a scanné  
 * il traite les classe avec l'annotation @FeignClient(name="")
 * 
 * activation de Hystrix pour la gestion tolerance des pannes
 */
@EnableFeignClients("com.zaghir.cloud.fundapi")
@EnableDiscoveryClient
@EnableHystrix
public class FundApiApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(FundApiApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(FundApiApplication.class, args);
		LOGGER.info("### FundApiApplication  is running###");
	}

}
