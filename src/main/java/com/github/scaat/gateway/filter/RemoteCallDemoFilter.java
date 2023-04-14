package com.github.scaat.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

public class RemoteCallDemoFilter extends AbstractGatewayFilterFactory<RemoteCallDemoFilter.Config> {

    @Autowired
    private WebClient webClient;

    public RemoteCallDemoFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {

        return ((exchange, chain) -> {
            MultiValueMap<String, String> headers = exchange.getRequest().getHeaders();
            String sleep;
            if (headers.size() == 0 || !headers.containsKey("x-sleep")) {
                sleep = "0";
            } else {
                sleep = headers.get("x-sleep").get(0);
            }
            return webClient.get().uri("http://httpbin.org/delay/" + sleep).retrieve().bodyToMono
                    (String.class).flatMap(s -> {
                System.out.println(s);
                return chain.filter(exchange);
            });
        });


    }
    public static class Config {
        //Put the configuration properties for your filter here
    }
}
