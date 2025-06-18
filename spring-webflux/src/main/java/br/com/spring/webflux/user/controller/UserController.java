package br.com.spring.webflux.user.controller;

import br.com.spring.webflux.user.domain.UserRequest;
import br.com.spring.webflux.user.domain.UserResponse;
import br.com.spring.webflux.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserResponse> retrieveUsers() {
        return userService.retrieveUsers()
                .onBackpressureBuffer(10, BufferOverflowStrategy.DROP_OLDEST)
                //.delayElements(Duration.ofMillis(100))
                .log();
    }

    @PostMapping(path = "/create")
    public Mono<ResponseEntity> saveUser(@RequestBody UserRequest request)
    {
        return Mono.just(ResponseEntity.status(201).body(userService.saveUser(request).map(userResponse -> userResponse)));
    }
}
