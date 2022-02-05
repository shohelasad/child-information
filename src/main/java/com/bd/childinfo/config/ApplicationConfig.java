package com.bd.childinfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bd.childinfo.StartupHouseKeeper;

/**
 * @author Bazlur Rahman Rokon
 * @since 8/24/15.
 */
@Configuration
public class ApplicationConfig {

    @Bean
    public StartupHouseKeeper startupHouseKeeper() {

        return new StartupHouseKeeper();
    }
}
