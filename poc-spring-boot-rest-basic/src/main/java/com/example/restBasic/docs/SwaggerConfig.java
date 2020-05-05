package com.example.restBasic.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket detalheApi() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        docket
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.restBasic.resources"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(
                        Collections.singletonList(
                                new ParameterBuilder()
                                        .name("Authorization")
                                        .description("Bearer token")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(true)
                                        .build()
                        ))
                .apiInfo(this.informacoesApi().build());

        return docket;
    }

    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Api-restBasic");
        apiInfoBuilder.description("Api para realização de um CRUD.");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: Deve ser usada para estudos.");
        apiInfoBuilder.license("Licença - Open Source");
        apiInfoBuilder.licenseUrl("https://www.linkedin.com/in/pedro-henrique-polonea-paula-silva-539041a4/");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder;

    }
    private Contact contato() {

        return new Contact(
                "Pedro Polonea",
                "",
                "");
    }
}

