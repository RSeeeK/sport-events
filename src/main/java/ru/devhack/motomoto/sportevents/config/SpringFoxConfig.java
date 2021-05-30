package ru.devhack.motomoto.sportevents.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

import java.util.List;

import static java.util.Collections.singletonList;

/**
 * SWAGGER configuration
 *
 * @author AR
 */
@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("(?!/error.*).*"))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(securityScheme())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sport events")
                .description("")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .version("1.0.0")
                .contact(new Contact("Vasya","", ""))
                .build();
    }

    private List<SecurityScheme> securityScheme() {
        return singletonList(new ApiKey("Authorization", "Authorization", "header"));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder
                .builder()
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false).build();
    }

    private List<SecurityContext> securityContexts() {
        return singletonList(
                SecurityContext
                        .builder()
                        .securityReferences(defaultAuth())
                        .build()
        );
    }

    private List<SecurityReference> defaultAuth() {
        return singletonList(
                new SecurityReference(
                        "Authorization",
                        new AuthorizationScope[]{new AuthorizationScope("global", "accessEverything")}
                )
        );
    }
}
