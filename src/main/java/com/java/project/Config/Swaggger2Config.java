package com.java.project.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * Configuration for Swagger2 documentation.
 */
@Configuration
@EnableSwagger2
public class Swaggger2Config {

    /**
     * Creates a new <code>Docket</code> which handles the structure of the Swagger2 documentation.
     *
     * @return the newly created <code>Docket</code>.
     */
    @Bean
    public Docket swagger2Configuration() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.ant("/api/v1/**"))
                .apis(RequestHandlerSelectors.basePackage("com.java.project"))
                .build()
                .apiInfo(apiDetails());
    }

    /**
     * Sets general information about the API.
     *
     * @return a <code>String</code> containing general info about the API.
     */
    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Online Test API",
                "API for Online Test Management",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Ciprian Danis", "", "ciprian.danis.a2@gmail.com"),
                "",
                "",
                Collections.emptyList()
        );
    }
}
