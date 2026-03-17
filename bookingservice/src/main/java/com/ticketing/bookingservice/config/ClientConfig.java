package com.ticketing.bookingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {

    // define rest client bean
    @Bean
    public RestClient restClient() {

        return RestClient.builder().build();

    }

}
