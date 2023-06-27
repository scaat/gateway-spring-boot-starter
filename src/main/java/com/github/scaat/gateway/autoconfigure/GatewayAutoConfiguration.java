package com.github.scaat.gateway.autoconfigure;

import com.github.scaat.gateway.config.GatewayPropertiesRefresher;
import com.github.scaat.gateway.filter.MyRoutePredicateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayAutoConfiguration {
    @Bean
    GatewayPropertiesRefresher gatewayPropertiesRefresher(){
        return new GatewayPropertiesRefresher();
    }

    @Bean
    MyRoutePredicateFactory myRoutePredicateFactory(){
        return new MyRoutePredicateFactory();
    }

}
