package com.zaghir.cloud.zipkin.distributed.tracing.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin2.server.internal.EnableZipkinServer;



@SpringBootApplication
@EnableZipkinServer
@EnableDiscoveryClient
public class ZipkinDistributedTracingServerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZipkinDistributedTracingServerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ZipkinDistributedTracingServerApplication.class, args);
		LOGGER.info("### ZipkinDistributedTracingServerApplication  is running###");
	}

}
