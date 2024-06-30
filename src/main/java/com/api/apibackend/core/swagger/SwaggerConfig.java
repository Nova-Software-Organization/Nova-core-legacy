// /**
//  * ----------------------------------------------------------------------------
//  * Autor: Kaue de Matos
//  * Empresa: Nova Software
//  * Propriedade da Empresa: Todos os direitos reservados
//  * ----------------------------------------------------------------------------
//  */
// package com.api.apibackend.core.swagger;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.bind.annotation.RestController;

// import springfox.documentation.builders.ApiInfoBuilder;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import  com.api.apibackend.shared.Enum.SwaggerConstants;

// @Configuration
// public class SwaggerConfig {
//     @Autowired
//     private SwaggerConstants swaggerConstants;

//     @Bean
//     public Docket api() {
//         return new Docket(DocumentationType.SWAGGER_2).host(swaggerConstants.getHostAddress())
//                 .pathMapping(swaggerConstants.getHostPath())
//                 .apiInfo(apiInfo())
//                 .select()
//                 .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                 .paths(PathSelectors.any())
//                 .build();
//     }

//     private ApiInfo apiInfo() {
//         return new ApiInfoBuilder()
//                 .title(swaggerConstants.getApiName())
//                 .description(swaggerConstants.getApiName())
//                 .version(swaggerConstants.getApiVersion())
//                 .build();
//     }
// }
