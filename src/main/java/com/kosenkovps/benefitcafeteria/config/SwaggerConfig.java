package com.kosenkovps.benefitcafeteria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        ArrayList<Response> responses = new ArrayList<>();
        responses.add(new ResponseBuilder().code("500").description("500 message").build());
        responses.add( new ResponseBuilder().code("403")
                .description("Forbidden!!!!!").build());
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.kosenkovps.benefitcafeteria.controllers"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, responses);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Benefit Cafeteria API",
                "This api will help you understand the methods provided for working with Benefit-Cafeteria app",
                "0.0.1",
                "Term of service",
                new Contact("Pavel", "www.example.com", "kosenkov.paul@gmail.com"),
                "License of APi", "API license URL", Collections.emptyList()
        );
    }
}
