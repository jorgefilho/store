package com.store.checkout.api.configuration;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.fasterxml.classmate.TypeResolver;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Value("${swagger.config.apiInfo.title}")
	private String title;
	
	@Value("${swagger.config.apiInfo.description}")
	private String description;
	
	@Value("${swagger.config.apiInfo.version}")
	private String version;
	
	@Value("${swagger.config.apiInfo.termsOfServiceUrl}")
	private String termsOfServiceUrl;
	
	@Value("${swagger.config.apiInfo.contact}")
	private String contact;
	
	@Value("${swagger.config.apiInfo.license}")
	private String license;
	
	@Value("${swagger.config.apiInfo.licenseUrl}")
	private String licenseUrl;
	
	@Bean
	public Docket petApi() {
		final ApiInfo apiInfo = new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl);
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo).select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any()).build()
			.pathMapping("/")
			.directModelSubstitute(LocalDate.class, String.class)
			.genericModelSubstitutes(ResponseEntity.class)
			.alternateTypeRules(getAlternateTypeRules())
			.useDefaultResponseMessages(false)
			.globalResponseMessage(RequestMethod.GET, newArrayList(getResponseMessage()))
			.securitySchemes(newArrayList(apiKey()))
			.securityContexts(newArrayList(securityContext()))
			.enableUrlTemplating(false);
	}

	private AlternateTypeRule getAlternateTypeRules() {
		return newRule(typeResolver.resolve(DeferredResult.class,
				typeResolver.resolve(ResponseEntity.class,
				WildcardType.class)), typeResolver.resolve(WildcardType.class));
	}

	private ResponseMessage getResponseMessage() {
		return new ResponseMessageBuilder()
				.code(500)
				.message("500 message")
				.responseModel(new ModelRef("Error")).build();
	}

	@Autowired
	private TypeResolver typeResolver;

	private ApiKey apiKey() {
		return new ApiKey("mykey", "api_key", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex("/anyPath.*")).build();
	}

	List<SecurityReference> defaultAuth() {
		final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return newArrayList(new SecurityReference("mykey", authorizationScopes));
	}

	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration("test-app-client-id", "test-app-realm", "test-app", "apiKey");
	}

	@Bean
	UiConfiguration uiConfig() {
		return new UiConfiguration("validatorUrl");
	}

}
