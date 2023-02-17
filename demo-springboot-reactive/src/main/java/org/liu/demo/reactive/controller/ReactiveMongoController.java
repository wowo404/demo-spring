package org.liu.demo.reactive.controller;

import lombok.RequiredArgsConstructor;
import org.liu.demo.reactive.model.User;
import org.liu.demo.reactive.repository.mongodb.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author lzs
 * @Date 2023/2/14 10:48
 **/
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class ReactiveMongoController {
    private final UserRepository userRepository;

    @PostMapping("/")
    public Mono<User> add(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/")
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return userRepository.findById(id)
                .flatMap(user -> userRepository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/")
    public Mono<ResponseEntity<User>> updateUser(@RequestBody User user) {
        return userRepository.findById(user.getId())
                .flatMap(u -> userRepository.save(user))
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byName")
    public Flux<User> getUserByName(String name) {
        return userRepository.findUserByUsernameContaining(name);
    }
}
