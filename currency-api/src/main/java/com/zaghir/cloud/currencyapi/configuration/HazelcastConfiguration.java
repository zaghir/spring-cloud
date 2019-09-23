package com.zaghir.cloud.currencyapi.configuration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.zaghir.cloud.currencyapi.bean.ExchangeValue;

@Configuration
public class HazelcastConfiguration {
	
	private static final String MAP_CACH_CURRENCY_API = "map-curruency-api";
	
	@Autowired
	private Environment env ;

	@Bean(name="serverCachApiCurrency")
	public HazelcastInstance serverCachApiCurrency() {
		// configurer notre node pour qu'il communique avec Management Center de
		// Hazelcast
//		ManagementCenterConfig mcfg = new ManagementCenterConfig();
//		mcfg.setEnabled(true);
//		mcfg.setUrl("http://localhost:5703/hazelcast-mancenter");
//		
//		MapConfig mapConfig = new MapConfig()
//				.setName("map-curruency-api")
//				.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
//				.setEvictionPolicy(EvictionPolicy.LRU)
//				.setTimeToLiveSeconds(20);
//		
//		Config config = new Config();
//		config.getNetworkConfig()
//			.setPort(10000)
//			.setPortAutoIncrement(false);
//		config.setProperty("hazelcast.discovery.enabled", "true");
//        NetworkConfig networkConfig = new NetworkConfig();
//        DiscoveryStrategyConfig discoveryStrategyConfig = new DiscoveryStrategyConfig("com.hazelcast.spi.discovery.multicast.MulticastDiscoveryStrategy");
//        DiscoveryConfig discoveryConfig = new DiscoveryConfig();
//        discoveryConfig.addDiscoveryStrategyConfig(discoveryStrategyConfig);
//        networkConfig.getJoin().getDiscoveryConfig().addDiscoveryStrategyConfig(discoveryStrategyConfig);
//        networkConfig.getJoin().getMulticastConfig().setEnabled(false);
//		
//		config.setManagementCenterConfig(mcfg);
//		config.setInstanceName("hazelcast-e-currency-api");
//		config.addMapConfig(mapConfig);
		
		/** configuer le management center pour le monitoring*/
		ManagementCenterConfig mcfg = new ManagementCenterConfig();
		mcfg.setEnabled(true);
		mcfg.setUrl(env.getProperty("cach.managementCenter.url"));
		
		Config cfg = new Config();
		cfg.setManagementCenterConfig(mcfg);
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		Map<String, ExchangeValue> mapApiCurrency = instance.getMap(env.getProperty("cach.maps.currency-api"));
		return instance;
	}
	
	@Bean(name="clientCachApiCurrency")
	@DependsOn({"serverCachApiCurrency"})
	public HazelcastInstance clientCachApiCurrency(){
		/**
		 *  - On a utiliser l'annotation @DependsOn car notre client a besoin d'ecouter une instance server
		 *  de hazelcast c'est pour ca on cree une dependance au moment de la creation des bean par spring 
		 *  et on lui demander de creer cle bean clientCachApiCurrency apres la creation de serverCachApiCurrency
		 */
		ClientConfig clientConfig = new ClientConfig();
	    HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );
	    return client ;
	}
	
	
	
//	@Bean
//	public ClientConfig hazelcastConfigClient(){
//		ClientConfig clientConfig = new ClientConfig();
////		
//
//		DiscoveryStrategyConfig discoveryStrategyConfig = new DiscoveryStrategyConfig("com.hazelcast.spi.discovery.multicast.MulticastDiscoveryStrategy");
//		discoveryStrategyConfig.addProperty("group", "localhost");
//		discoveryStrategyConfig.addProperty("port", "10000");
//		
//		clientConfig.getNetworkConfig()
//			.getDiscoveryConfig()
//			.addDiscoveryStrategyConfig(discoveryStrategyConfig);
//		String hazelcastGroup = "test";
//		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.setProperty("hazelcast.discovery.enabled", "true");
//		
//
//		DiscoveryStrategyConfig discoveryStrategyConfig = new DiscoveryStrategyConfig(
//		        "com.hazelcast.spi.discovery.multicast.MulticastDiscoveryStrategy");
//		clientConfig.getNetworkConfig().getDiscoveryConfig().addDiscoveryStrategyConfig(discoveryStrategyConfig);
		
		

//		return clientConfig;
		
//	}
}
