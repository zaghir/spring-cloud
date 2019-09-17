package com.zaghir.cloud.currencyapi;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class TestJackson {
	
	@Test
	public void givenTheJsonNode_whenRetrievingDataFromId_thenCorrect() 
	  throws JsonParseException, IOException {
	    String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode actualObj = mapper.readTree(jsonString);
	 
	    // When
	    JsonNode jsonNode1 = actualObj.get("k1");
	    Assert.assertEquals(jsonNode1.textValue(), "v1");
	}

}
