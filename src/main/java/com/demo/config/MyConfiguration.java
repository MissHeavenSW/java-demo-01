package com.demo.config;

import com.nhsoft.provider.data.annotation.config.DataModelScan;
import com.nhsoft.provider.data.annotation.config.EnableDataController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.demo")
@ComponentScan("com.demo")
@DataModelScan("com.demo.domain")
public class MyConfiguration {

    @Configuration
    @EnableDataController(debugMode = true)
    @Profile("!production")
    public static class DebugConfiguration {

    }

    @Configuration
    @EnableDataController
    @Profile("production")
    public static class ProductionConfiguration {

    }

}
