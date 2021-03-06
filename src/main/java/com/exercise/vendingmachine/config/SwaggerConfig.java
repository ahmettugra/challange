package com.exercise.vendingmachine.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Challange API")
                .description("Description for Challange")
                .termsOfServiceUrl("http://tugra.ozubek.com")
                .license("Java License")
                .licenseUrl("tugra.ozubek@gmail.com").version("1.0").build();
    }

}
