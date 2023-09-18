package co.song.dev.requestheader.config;

import co.song.dev.requestheader.service.JsonPlaceholderService;
import co.song.dev.requestheader.service.UserClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration

public class HttpInterfaceClientConfig {

    private JsonPlaceholderService jsonPlaceholderService;
    private UserClientService clientService;

    public JsonPlaceholderService getJsonPlaceholderService() {
        return jsonPlaceholderService;
    }

    public UserClientService getClientService() {
        return clientService;
    }

    @Bean
    public HttpServiceProxyFactory factory() {
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(WebClient.builder().build()))
                .build();

        jsonPlaceholderService = factory.createClient(JsonPlaceholderService.class);
        clientService = factory.createClient(UserClientService.class);

        return factory;
    }
}
