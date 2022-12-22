package com.shop.shoppingcartserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ShoppingCartServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartServerApplication.class, args);
    }

}
