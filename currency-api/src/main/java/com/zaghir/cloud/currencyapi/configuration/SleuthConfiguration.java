package com.zaghir.cloud.currencyapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brave.sampler.Sampler;

@Configuration
public class SleuthConfiguration {

	/**
	 * 
	 * @return Sampler 
	 * 
	 * utilisation d'un bean de type Sampler => Al pour tracer toute les request avec Sleuth de zipkin
	 */
	@Bean
	public Sampler defaultSampler() {		
		return Sampler.ALWAYS_SAMPLE;
	}
}
