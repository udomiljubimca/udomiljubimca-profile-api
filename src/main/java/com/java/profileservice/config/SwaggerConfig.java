package com.java.profileservice.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        //Adding header
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("")
                .modelRef(new ModelRef("string"))
                .parameterType("")
                .defaultValue("")
                .description("")
                .required(false)
                .build();
        List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.java.profileservice"))
                .paths(paths())
                .build()
                .globalOperationParameters(aParameters)
                .apiInfo(apiInfo());
    }

    //Here is an example where we select any api that matches one of these paths
    private Predicate<String> paths() {
        return or(
                regex("/.*"),
                regex("/profile.*"));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Profile Api")
                .description("Api for working with profiles")
                .version("1.0")
                .termsOfServiceUrl("http://terms-of-services.url")
                .license("LICENSE")
                .licenseUrl("http://url-to-license.com")
                .build();
    }
	/*
        @Bean
        public Docket api() {
            //Adding Header
            ParameterBuilder aParameterBuilder = new ParameterBuilder();
            aParameterBuilder.name("Authorization")
                    .modelRef(new ModelRef("string"))
                    .parameterType("header")
                    .defaultValue("Bearer + token")
                    .required(true)
                    .build();
            List<Parameter> aParameters = new ArrayList<>();
            aParameters.add(aParameterBuilder.build());
            return new Docket(DocumentationType.SWAGGER_2).select()
                    .apis(RequestHandlerSelectors
                            .any())
                    .paths(PathSelectors.any())
                    .build().
                            pathMapping("")
                    .globalOperationParameters(aParameters);
        }
	 **/
}
