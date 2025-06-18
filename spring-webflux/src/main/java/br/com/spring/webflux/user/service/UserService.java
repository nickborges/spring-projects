package br.com.spring.webflux.user.service;

import br.com.spring.webflux.user.domain.User;
import br.com.spring.webflux.user.domain.UserMapper;
import br.com.spring.webflux.user.domain.UserRequest;
import br.com.spring.webflux.user.domain.UserResponse;
import br.com.spring.webflux.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<UserResponse> saveUser(UserRequest request) {
        User user = userMapper.toUser(request);
        Mono<User> customerMono = userRepository.save(user).log();
        return customerMono.map(userMapper::toUserResponse);
    }

    public Flux<UserResponse> retrieveUsers() {
        return userRepository.findAll().log().map(userMapper::toUserResponse);
    }
}
