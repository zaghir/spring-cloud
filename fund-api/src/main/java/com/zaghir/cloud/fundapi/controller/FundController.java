package com.zaghir.cloud.fundapi.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zaghir.cloud.fundapi.bean.ExchangeValue;
import com.zaghir.cloud.fundapi.bean.Fund;
import com.zaghir.cloud.fundapi.service.CurrencyExchangeServiceProxy;

@RestController
@RequestMapping("fund")
public class FundController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FundController.class);
	private static final String URL_CURRENCY_API = "http://localhost:8100";
	
	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeServiceProxy ; 

	@GetMapping("/details/name/{name}/currency/{currency}")
	@HystrixCommand(defaultFallback="fallToleranceRetrieveFund")
	public Fund retrieveFund(@PathVariable String name, @PathVariable String currency) {
		Fund fund = new Fund(name, null, currency);
		/*
		 * les fonds sont toujours en USD si le currency est diffrent de USD en
		 * utilise l'api currency pour la conversion
		 */
		BigDecimal currencyCal = BigDecimal.valueOf(1);
		if (currency != null && !currency.equals("USD")) {			
			WebClient.Builder webClientBuilder = WebClient.builder();
			ExchangeValue exchangeValue = webClientBuilder.baseUrl(URL_CURRENCY_API).build()
					.get()
					.uri(uriBuilder -> uriBuilder							
							.path("/currency/exchange/from/USD/to/{currency}")
							.build(currency))
					.accept(MediaType.APPLICATION_JSON)
					.retrieve()
					.bodyToMono(ExchangeValue.class)
					.block();
			
			
			currencyCal = exchangeValue.getConversionMultiple();
			fund.setPort(exchangeValue.getPort());
			fund.setPrice(BigDecimal.valueOf(10 * currencyCal.doubleValue()));
		}		

		LOGGER.info("CurrencyExchangeServiceProxy:retrieveFund result ==> {}",fund);
		return fund;
	}
	
	@GetMapping("/detailsByFeign/name/{name}/currency/{currency}")
	@HystrixCommand(defaultFallback="fallToleranceRetrieveFund")
	public Fund retrieveFundProxyFeign(@PathVariable String name, @PathVariable String currency) {
		Fund fund = new Fund(name, null, currency);
		/*
		 * les fonds sont toujours en USD si le currency est diffrent de USD en
		 * utilise l'api currency pour la conversion
		 */
		BigDecimal currencyCal = BigDecimal.valueOf(1);
		if (currency != null && !currency.equals("USD")) {						
			ExchangeValue exchangeValue = 
					currencyExchangeServiceProxy.retrieveCurrencyExchangeValue("USD", currency);			
			
			currencyCal = exchangeValue.getConversionMultiple();
			fund.setPrice(BigDecimal.valueOf(10 * currencyCal.doubleValue()));
			fund.setPort(exchangeValue.getPort());
		}		

		LOGGER.info("CurrencyExchangeServiceProxy:retrieveFund result ==> {}",fund);
		return fund;
	}
	
	
	public Fund fallToleranceRetrieveFund(){
		Fund fund = new Fund("ERROR",new BigDecimal("0.0"),"ERROR");
		return fund;
	}

}
