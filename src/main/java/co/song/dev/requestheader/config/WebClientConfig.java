package co.song.dev.requestheader.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "basicAuth")
    public WebClient basicAuthClient() {
        return WebClient.builder()
                .defaultHeader("Authorization", "Bearer user:password")
                .build();
    }

}
