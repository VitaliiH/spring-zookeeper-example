package com.github.spring.zookeeper.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A simple controller, that waves to a guest, on behalf of a person name preserved in a {@code name} variable.
 * Represents an ability of controller to of {@code @RefreshScope} as well.
 */
@RefreshScope
@RestController
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Value("${name}")
    private String name;

    @RequestMapping("/")
    public String index() throws InterruptedException {
        log.info("Greetings from {}", name);
        return "Welcome here from author - " + name + "!";
    }
}
