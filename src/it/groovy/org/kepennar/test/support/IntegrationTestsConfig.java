package org.kepennar.test.support;

import static org.mockito.Mockito.mock;

import org.kepennar.aproc.Application;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest("server.port=0, spring.profiles.active=test")
public class IntegrationTestsConfig {

    @Primary @Bean
    RestTemplate mockRestTemplate() { return mock(RestTemplate.class); }
}