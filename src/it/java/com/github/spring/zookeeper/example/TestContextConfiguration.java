package com.github.spring.zookeeper.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class may hold any test-specific bean declaration.
 */
@Configuration
public class TestContextConfiguration {

    @Bean
    public ZookeeperValueRefreshEventWrapper testEventListenerWrapper() {
        return new ZookeeperValueRefreshEventWrapper();
    }

}
