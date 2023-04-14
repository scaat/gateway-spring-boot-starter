package com.github.scaat.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient() {

        //配置固定大小连接池
        ConnectionProvider provider = ConnectionProvider
                .builder("tax-core")
                // 等待超时时间
                .pendingAcquireTimeout(Duration.ofSeconds(45))
                // 最大连接数
                .maxConnections(2)
                // 等待队列大小
                .pendingAcquireMaxCount(5)
                .build();

        HttpClient httpClient = HttpClient.create(provider);
        // 使用Reactor
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
