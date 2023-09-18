package co.song.dev.requestheader.controller;

import co.song.dev.requestheader.model.User;
import co.song.dev.requestheader.service.UserClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")

public class UserRestController {
    private final UserClientService clientService;
    public UserRestController (UserClientService userClientService){
        this.clientService = userClientService;
    }

    @GetMapping()
    public List<User> findAll(){
        return clientService.findAll();
    }
}
