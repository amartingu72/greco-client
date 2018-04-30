package com.alnura.greco.client.app;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.alnura.greco.client")
public class SwaggerConfig extends WebMvcConfigurationSupport {
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.alnura.greco.client"))              
          .paths(PathSelectors.any())   
          .build()
          .apiInfo(apiInfo());                                           
    }
	private ApiInfo apiInfo() {
	     return new ApiInfo(
	       "Interface REST Alnura API", 
	       "Servicios REST para Alnura", 
	       "v1", 
	       "Terms of service", 
	       new springfox.documentation.service.Contact("Alberto Martín", "www.alnura.es", "alberto.martin@alnura.es"), 
	       "License of API","url de licencia",
	       Collections.emptyList());
	}
	
	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	
}
