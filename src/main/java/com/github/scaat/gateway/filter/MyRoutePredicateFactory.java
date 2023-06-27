package com.github.scaat.gateway.filter;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory() {
        super(Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("token");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        // grab configuration from Config object
        return exchange -> {
            //grab the request
            ServerHttpRequest request = exchange.getRequest();
            //take information from the request to see if it
            //matches configuration.
//            request.getHeaders(config.)
//            exchange.getRequest().getQueryParams().getFirst(config.token);
            String token = exchange.getRequest().getHeaders().getFirst("hello-token");
            System.out.println("token:" + token + ", config token:" + config.getToken() + "token == config.getToken()?" + config.getToken().equals(token));

            return config.getToken().equals(token);
        };
    }

    public static class Config {
        //Put the configuration properties for your filter here
        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

}

