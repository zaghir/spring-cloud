package com.zaghir.cloud.currencyapi.service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.zaghir.cloud.currencyapi.bean.ExchangeValue;

@Service
public class CurrencyServiceImpl implements CurrencyService{
	
	private static final String URL_CURRENCY_API = "https://free.currconv.com/api/v7";
		
	private static Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
	
	@Autowired
	private Environment environment ;
	
	@Autowired
	@Qualifier("clientCachApiCurrency")
	private HazelcastInstance cachApiCurrencyClient;
		
	public ExchangeValue getCurrencyExchangeValueFromApi(String from , String to){
		
	// https://free.currconv.com/api/v7/convert?q=USD_PHP,PHP_USD&compact=ultra&apiKey=94f293efd8f2fd1160da
		
		//.get().uri("https://free.currconv.com/api/v7/convert?q=MAD_USD,USD_MAD&compact=ultra&apiKey=94f293efd8f2fd1160da")
		//.get().uri("https://free.currconv.com/api/v7/convert?q="+from+"_"+to+","+to+"_"+from+"+&compact=ultra&apiKey=94f293efd8f2fd1160da")
		//from+"_"+to, to+"_"+from       "MAD_USD","USD_MAD"
		WebClient.Builder webClientBuilder = WebClient.builder();
		ExchangeValue currency = webClientBuilder.baseUrl(URL_CURRENCY_API).build()
				.get()				
				.uri(uriBuilder -> uriBuilder
					    .path("/convert")
					    .queryParam("q", String.join(",", from+"_"+to, to+"_"+from))
					    .queryParam("compact", "ultra")
					    .queryParam("apiKey",  "94f293efd8f2fd1160da")
					    .build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.map( str -> {
					// on passe par un converter de string vers ExchangeValue
					LOGGER.info("result"+str);
					ObjectMapper mapper = new ObjectMapper();
				    JsonNode actualObj;
				    ExchangeValue exchangeValue = new ExchangeValue();
					try {
						
						actualObj = mapper.readTree(str);
						JsonNode jsonNode1 = actualObj.get(from+"_"+to);
					
						exchangeValue.setFrom(from);
						exchangeValue.setTo(to);
						exchangeValue.setConversionMultiple(BigDecimal.valueOf(jsonNode1.doubleValue()));
						exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));						
					} catch (Exception e) {
						LOGGER.error("CurrencyService:getCurrencyExchangeValueFromApi()  error ==> {}" ,e.getMessage()); 					
					}
					return exchangeValue;
				}).block();
		LOGGER.info("CurrencyService:getCurrencyExchangeValueFromApi result ==> {}",currency);
		return currency ;
	}
	
	public ExchangeValue getEchangeValueFromCach(String from , String to){
			
        IMap<String, ExchangeValue> map = cachApiCurrencyClient.getMap(environment.getProperty("cach.maps.currency-api"));
        for (Entry<String, ExchangeValue> entry : map.entrySet()) {
        	LOGGER.info("from cach ----> key => {} valeu => {}" , entry.getKey() , entry.getValue() );
        }
		LOGGER.info("size-----------------------> {}" , map.size() );
		ExchangeValue echangeValue = map.get(from+"_"+to);
		//ExchangeValue echangeValue=null;
		if(null == echangeValue){
			/* On appelle l'api externe pour recupere le taux de change qui n 'existe pas dans le cach*/
			echangeValue = getCurrencyExchangeValueFromApi( from ,  to);
			
			/* on registe l'objet dans le cach  */
			saveEchangeValueInCach(echangeValue);
		}else{
			LOGGER.info("CurrencyServiceImpl getEchangeValueFromCach()  data from cach ====> {} " , echangeValue); 
		}
		return echangeValue ;
	}
	
	public void saveEchangeValueInCach(ExchangeValue exchangeValue){
        IMap<String , ExchangeValue> map = cachApiCurrencyClient.getMap(environment.getProperty("cach.maps.currency-api"));
        map.put(exchangeValue.getFrom()+"_"+exchangeValue.getTo(), exchangeValue);
        LOGGER.info("CurrencyServiceImpl saveEchangeValueInCach() ==> nouvelle valeur {}" ,exchangeValue);
	}

}
