package com.in28minutos.learn_spring_boot;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "currency-service")
@Component
public class CurrencySeriveController {
    private String url;
    private String username;
    private String key;

}
