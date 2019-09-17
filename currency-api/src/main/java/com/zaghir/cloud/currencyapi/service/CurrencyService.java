package com.zaghir.cloud.currencyapi.service;

import com.zaghir.cloud.currencyapi.bean.ExchangeValue;

public interface CurrencyService {
	
	public ExchangeValue getCurrencyExchangeValueFromApi(String from , String to);
	public ExchangeValue getEchangeValueFromCach(String from , String to);
	public void saveEchangeValueInCach(ExchangeValue exchangeValue);

}
