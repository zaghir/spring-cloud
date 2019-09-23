package com.zaghir.cloud.currencyapi.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zaghir.cloud.currencyapi.bean.ExchangeValue;
import com.zaghir.cloud.currencyapi.service.CurrencyService;

@RestController()
@RequestMapping("currency")
public class CurrencyController {
	
	@Autowired
	private Environment environment ;
	
	@Autowired
	private CurrencyService currencyService ;
		
	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);
	
	private static final String URL_CURRENCY_API = "https://free.currconv.com/api/v7";
	
	@GetMapping("/exchange/from/{from}/to/{to}")
	//@HystrixCommand(defaultFallback="fallToleranceRetrieveCurrencyExchangeValue")
	public ExchangeValue retrieveCurrencyExchangeValue(@PathVariable String from , @PathVariable String to){
		
		// https://free.currconv.com/api/v7/convert?q=USD_PHP,PHP_USD&compact=ultra&apiKey=94f293efd8f2fd1160da
		
		//.get().uri("https://free.currconv.com/api/v7/convert?q=MAD_USD,USD_MAD&compact=ultra&apiKey=94f293efd8f2fd1160da")
		//.get().uri("https://free.currconv.com/api/v7/convert?q="+from+"_"+to+","+to+"_"+from+"+&compact=ultra&apiKey=94f293efd8f2fd1160da")
		//from+"_"+to, to+"_"+from       "MAD_USD","USD_MAD"
		ExchangeValue currency = currencyService.getEchangeValueFromCach(from, to);
		LOGGER.info("CurrencyController:retrieveCurrencyExchangeValue result ==> {}",currency);
		return currency ;
	}
	
	/**
	 * @return ExchangeValue
	 * cette methode est utilsée au cas ou l'api à eu un probleme 
	 * Hystrix se charge de gerer cette requete on utilisant la methode 
	 * fallToleranceRetrieveCurrencyExchangeValue()
	 * ici on va envoyer un taux = 0 pour dire que l'api ne repond plus 
	 * c'est possible de speciéfier un notre comportement exp: utilisé d'autre apis
	 */
	public ExchangeValue fallToleranceRetrieveCurrencyExchangeValue(){
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setId(0L);
		exchangeValue.setConversionMultiple(new BigDecimal("0.0"));
		exchangeValue.setFrom("ERROR");		
		return exchangeValue;
	} 
}
