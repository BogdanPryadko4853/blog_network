package com.example.blog_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket redditCloneApi() { 
        System.out.println("✅ SwaggerConfig.redditCloneApi()");
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        System.out.println("✅ SwaggerConfig.getApiInfo()");
        return new ApiInfoBuilder()
            .title("Blogging site in Spring")
            .version("0.1")
            .description("API documentation for Blogging Site in Spring")
            .contact(new Contact("Bogdan Pryadko", "github.com/BogdanPryadko4853", ""))
            .build();
    }
}
