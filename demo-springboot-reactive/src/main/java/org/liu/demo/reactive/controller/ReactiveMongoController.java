package org.liu.demo.reactive.controller;

import lombok.RequiredArgsConstructor;
import org.liu.demo.reactive.model.Dog;
import org.liu.demo.reactive.repository.mongodb.DogRepository;
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
    private final DogRepository dogRepository;

    @PostMapping("/")
    public Mono<Dog> add(@RequestBody Dog dog) {
        return dogRepository.save(dog);
    }

    @GetMapping("/")
    public Flux<Dog> getAll() {
        return dogRepository.findAll();
    }

    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dog> streamGetAll() {
        return dogRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return dogRepository.findById(id)
                .flatMap(dog -> dogRepository.delete(dog).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/")
    public Mono<ResponseEntity<Dog>> updateUser(@RequestBody Dog dog) {
        return dogRepository.findById(dog.getId())
                .flatMap(u -> dogRepository.save(dog))
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/byName")
    public Flux<Dog> getUserByName(String name) {
        return dogRepository.findDogByUsernameContaining(name);
    }
}
