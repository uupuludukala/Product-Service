package com.coolbook.erp.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.base.Predicates.not;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String AUTHORIZATION = "Authorization";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).securitySchemes(newArrayList(apiKey()))
				.select().apis(not(RequestHandlerSelectors.basePackage("org.springframework")))
				.paths(PathSelectors.any()).build().forCodeGeneration(true);
	}

	private ApiKey apiKey() {
		return new ApiKey(AUTHORIZATION, AUTHORIZATION, "header");
	}

	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(null, null, null, null, "", ApiKeyVehicle.HEADER, AUTHORIZATION, ",");
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Product-Service-api").version("v1").description("Product-Service-api")
				.build();
	}
}
