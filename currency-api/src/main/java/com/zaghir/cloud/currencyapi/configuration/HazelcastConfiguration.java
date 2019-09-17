package com.zaghir.cloud.currencyapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.DiscoveryConfig;
import com.hazelcast.config.DiscoveryStrategyConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.config.NetworkConfig;

//@Configuration
public class HazelcastConfiguration {

	@Bean
	public Config hazelcastConfig() {
		// configurer notre node pour qu'il communique avec Management Center de
		// Hazelcast
		ManagementCenterConfig mcfg = new ManagementCenterConfig();
		mcfg.setEnabled(true);
		mcfg.setUrl("http://localhost:5703/hazelcast-mancenter");
		
		MapConfig mapConfig = new MapConfig()
				.setName("map-curruency-api")
				.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
				.setEvictionPolicy(EvictionPolicy.LRU)
				.setTimeToLiveSeconds(20);
		
		Config config = new Config();
		config.getNetworkConfig()
			.setPort(10000)
			.setPortAutoIncrement(false);
		config.setProperty("hazelcast.discovery.enabled", "true");
        NetworkConfig networkConfig = new NetworkConfig();
        DiscoveryStrategyConfig discoveryStrategyConfig = new DiscoveryStrategyConfig("com.hazelcast.spi.discovery.multicast.MulticastDiscoveryStrategy");
        DiscoveryConfig discoveryConfig = new DiscoveryConfig();
        discoveryConfig.addDiscoveryStrategyConfig(discoveryStrategyConfig);
        networkConfig.getJoin().getDiscoveryConfig().addDiscoveryStrategyConfig(discoveryStrategyConfig);
        networkConfig.getJoin().getMulticastConfig().setEnabled(false);
		
		config.setManagementCenterConfig(mcfg);
		config.setInstanceName("hazelcast-instance-currency-api");
		config.addMapConfig(mapConfig);
		return config;
	}
	
	@Bean
	public ClientConfig hazelcastConfigClient(){
//		ClientConfig clientConfig = new ClientConfig();
//		
//
//		DiscoveryStrategyConfig discoveryStrategyConfig = new DiscoveryStrategyConfig("com.hazelcast.spi.discovery.multicast.MulticastDiscoveryStrategy");
//		discoveryStrategyConfig.addProperty("group", "localhost");
//		discoveryStrategyConfig.addProperty("port", "10000");
//		
//		clientConfig.getNetworkConfig()
//			.getDiscoveryConfig()
//			.addDiscoveryStrategyConfig(discoveryStrategyConfig);
		String hazelcastGroup = "test";
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setProperty("hazelcast.discovery.enabled", "true");
		clientConfig.getGroupConfig().setName(hazelcastGroup).setPassword(hazelcastGroup);

		DiscoveryStrategyConfig discoveryStrategyConfig = new DiscoveryStrategyConfig(
		        "com.hazelcast.spi.discovery.multicast.MulticastDiscoveryStrategy");
		clientConfig.getNetworkConfig().getDiscoveryConfig().addDiscoveryStrategyConfig(discoveryStrategyConfig);	
		return clientConfig;
		
	}
}
