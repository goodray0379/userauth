package com.devmin.userauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config() extends WebMvcConfigurationSupport(){

    @Bean
    public Docket swaggerApi(){
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.devmin.userauth.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo swaggerInfo() {
        return ApiInfoBuilder()
                .title("제목")
                .version("버전")
                .description("설명")
                .license("라이센스")
                .licenseUrl("라이센스 URL 작성")
                .build();
    }
}