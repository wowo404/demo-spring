package org.liu.demo.reactive.service;

import lombok.RequiredArgsConstructor;
import org.liu.demo.reactive.repository.mysql.UserRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @Author lzs
 * @Date 2023/5/26 11:29
 **/
@RequiredArgsConstructor
@Service
public class UserService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
