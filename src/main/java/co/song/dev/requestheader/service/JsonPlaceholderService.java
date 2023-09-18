package co.song.dev.requestheader.service;

import co.song.dev.requestheader.model.Post;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;
import java.util.Map;

public interface JsonPlaceholderService {
    @GetExchange("/posts")
    List<Post> findAll(@RequestHeader Map<String, String> headers);
}
