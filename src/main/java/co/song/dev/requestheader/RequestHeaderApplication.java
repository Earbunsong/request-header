package co.song.dev.requestheader;

import co.song.dev.requestheader.service.JsonPlaceholderService;
import co.song.dev.requestheader.service.UserClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@SpringBootApplication
public class RequestHeaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequestHeaderApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(JsonPlaceholderService client){
        return args -> client.findAll(Map.of("Onepiece","Spring Framework 6"));
    }
    @Bean
    JsonPlaceholderService jsonPlaceholderService(){
        WebClient client = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultStatusHandler(httpStatusCode -> {
                    if (httpStatusCode.is5xxServerError()) {
                        throw new RuntimeException("Error from service");
                    } else if (httpStatusCode.is4xxClientError()) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request from client");
                    }
                    return false;
                }, clientResponse -> Mono.empty())
                .defaultHeader("SPRING-BOOT-VERSION","3.1.0")
                .exchangeStrategies(ExchangeStrategies.builder().codecs(c->c.defaultCodecs().enableLoggingRequestDetails(true)).build())
                .build();
        HttpServiceProxyFactory factory =HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(JsonPlaceholderService.class);
    }

    @Bean
    UserClientService clientService(){
        WebClient client = WebClient.builder()
                .baseUrl("https://fakestoreapi.com/users")
                .defaultHeader("SPRING-BOOT-VERSION","3.1.0")
                .exchangeStrategies(ExchangeStrategies.builder().codecs(c->c.defaultCodecs().enableLoggingRequestDetails(true)).build())
                .build();
        HttpServiceProxyFactory factory =HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(UserClientService.class);
    }



}
