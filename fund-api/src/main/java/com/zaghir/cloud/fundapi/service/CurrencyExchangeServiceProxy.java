package com.zaghir.cloud.fundapi.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.zaghir.cloud.fundapi.bean.ExchangeValue;


/**
 * l'utilisation de load balancer Ribbon permet de se passer de paramettre url = dans le proxy feign
 * ribbon peut communiquer avec plusieurs instance d'une api, ca permet de resoudre le probleme
 * de monté en charge ou le non disponibilié d'une instance a cause d un crache 
 * c'est le load balancer qui determier ou va router les flux des requettes
 * */
//@FeignClient(name="currency-api" ,url="localhost:8100")
/**
 * l'utilisation de l'api gatway change l'utilisation de proxy Feign en desactive cette implementation*/
//@FeignClient(name="currency-api")

/**
 * on va dire au proxy feign d utiliser la gatway au lient du nom de l'application */
@FeignClient(name="netflix-zuul-api-gatway-server")
@RibbonClient("currency-api")
public interface CurrencyExchangeServiceProxy {
	
	//@GetMapping("/currency/exchange/from/{from}/to/{to}")
	/**
	 * on a configurer une getway avec Zuul du coup on passe par la getway pour utiliser 
	 * d'autre apis */
	@GetMapping("/currency-api/currency/exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveCurrencyExchangeValue(@PathVariable("from") String from , @PathVariable("to") String to);
	

	
}
