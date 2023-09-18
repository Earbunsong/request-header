package co.song.dev.requestheader.service;

import co.song.dev.requestheader.model.User;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserClientService {
    @GetExchange("/")
    List<User> findAll();
}
