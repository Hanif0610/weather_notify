package com.weather.notify.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .consumes(getConsumeContentTypes())
            .produces(getProduceContentTypes())
            .apiInfo(this.metaInfo())
            .host("localhost:8000")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.weather.notify.controller"))
            .paths(PathSelectors.any())
            .build()
    }

    private fun metaInfo(): ApiInfo {
        return ApiInfo(
            "Weather_notify API Docs",
            "project API Docs",
            "1.0.0",
            "Terms of Service URL",
            "Contact Name",
            "License",
            "License URL"
        )
    }

    private fun getConsumeContentTypes(): Set<String> {
        val consumes: MutableSet<String> = HashSet()
        consumes.apply {
            add("application/json;charset=UTF-8")
            add("application/x-www-form-urlencoded")
        }
        return consumes
    }

    private fun getProduceContentTypes(): Set<String> {
        val produces: MutableSet<String> = HashSet()
        produces.add("application/json;charset=UTF-8")
        return produces
    }
}