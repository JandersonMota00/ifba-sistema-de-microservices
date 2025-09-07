package com.apigatewayservico.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("usuario-servico", r -> r.path("/usuarios/**").uri("http://localhost:8081"))
                .route("pedido-servico", r -> r.path("/pedidos/**").uri("http://localhost:8082"))
                .route("pagamento-servico", r -> r.path("/pagamentos/**").uri("http://localhost:8083"))
                .build();
    }

}
