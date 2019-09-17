package com.zaghir.weatherapi.configuration;

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
	 * Sleuth ajout un clé a chaque requete , s'il y une requete qui demande la reponse d'une aure
	 * sleuth ajout le clé de la requete mere dans la requete fille , ca permet la tracabilité des reponses
	 *  
	 */
	@Bean
	public Sampler defaultSampler() {		
		return Sampler.ALWAYS_SAMPLE;
	}
}
