package org.pussinboots.ttui.cms.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"org.pussinboots.ttui.cms.controller"})
public class SwaggerConfig {

   @Bean  
    public Docket platformApi() {  
        return new Docket(DocumentationType.SWAGGER_2)  
        		.groupName("ttui-cms-web")
                .apiInfo(apiInfo())  
                .forCodeGeneration(true);
    }  

    private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("ttui-cms-web RESTful APIs")
                .description("Copyright © 2017, XingXing.Chen, ttui. All Rights Reserved.")
                .contact(new Contact("ChenXingXing", "https://git.oschina.net/ttui_/ttui", "chenxingxing1994@foxmail.com"))
                .license("Apache License Version 2.0")
                .termsOfServiceUrl("https://git.oschina.net/ttui_/ttui")
                .version("3.0.0-SNAPSHOT")  
                .build();  
    }  
}
