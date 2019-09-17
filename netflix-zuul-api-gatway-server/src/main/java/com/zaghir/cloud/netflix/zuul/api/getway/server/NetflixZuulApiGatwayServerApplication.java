package com.zaghir.cloud.netflix.zuul.api.getway.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//activer le proxy de la gatway (zuul)
@EnableZuulProxy
//s'enregister chez eureka 
@EnableDiscoveryClient
@SpringBootApplication
public class NetflixZuulApiGatwayServerApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NetflixZuulApiGatwayServerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(NetflixZuulApiGatwayServerApplication.class, args);
		LOGGER.info("### NetflixZuulApiGatwayServerApplication  is running###");
	}
	
	/**
	 * pour lancer l'api gatway il faut que 
	 * 1- eureka soit lancer en premier 
	 * 2- lancer les apis qui vont s'inscire dans l'annuaire de eureka
	 * 3- lancer la gatway zuul 
	 *  
	 * pour verifier, il faut avoir le pather suivant pour l 'url 
	 * 		http://localhost:{port-gatway}/{application-name}/{uri}
	 * l'url de l'api est caché dans lea gatway par le nom de l'application , il n y a pa de port ou adresse
	 * exemple 
	 * 	http://localhost:8200/fund/detailsByFeign/name/EUR/currency/MAD
	 * 	avec api gatway 
	 * 	http://localhost:8765/fund-api/fund/details/name/EUR/currency/MAD
	 * 
	 *  http://localhost:8765                : adresse d'api gatway
	 *  /fund-api                            : nom application
	 *  /fund/details/name/EUR/currency/MAD  : path
	 */

}
