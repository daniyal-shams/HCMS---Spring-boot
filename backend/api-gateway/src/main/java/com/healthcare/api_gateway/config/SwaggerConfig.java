package com.healthcare.api_gateway.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi patientApi() {
        return GroupedOpenApi.builder()
                .group("patient-service")
                .pathsToMatch("/patient-service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi doctorApi() {
        return GroupedOpenApi.builder()
                .group("doctor-service")
                .pathsToMatch("/doctor-service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi appointmentApi() {
        return GroupedOpenApi.builder()
                .group("appointment-service")
                .pathsToMatch("/appointment-service/**")
                .build();
    }


    @Bean
    public GroupedOpenApi nurseApi() {
        return GroupedOpenApi.builder()
                .group("nurse-service")
                .pathsToMatch("/nurse-service/**")
                .build();
    }

    @Bean
    public GroupedOpenApi reportApi() {
        return GroupedOpenApi.builder()
                .group("file-service")
                .pathsToMatch("/file-service/**")
                .build();
    }
}
