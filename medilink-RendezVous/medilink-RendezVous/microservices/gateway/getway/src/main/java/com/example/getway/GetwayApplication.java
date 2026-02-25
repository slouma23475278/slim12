package com.example.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetwayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route("rendezvous", r -> r.path("/rendezvous/**").uri("lb://RENDEZ-VOUS"))
				.route("consultation", r -> r.path("/consultation/**").uri("lb://CONSULTATION"))
				.route("notification", r -> r.path("/notification/**").uri("lb://NOTIFICATION"))
				.route("ordenance", r -> r.path("/ordenance/**").uri("lb://ORDENANCE"))
				.route("paiement", r -> r.path("/paiement/**").uri("lb://PAIEMENT"))
				.route("user", r -> r.path("/user/**").uri("lb://USER"))
				.build();
	}
}
