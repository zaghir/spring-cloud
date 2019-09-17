package com.zaghir.cloud.config.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SpringCloudConfigServerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringCloudConfigServerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
		LOGGER.info("### SpringCloudConfigServerApplication  is running###");
	}

}
