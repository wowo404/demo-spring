package org.liu.demo.reactive.repository.mongodb;

import org.liu.demo.reactive.model.Dog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * @Author lzs
 * @Date 2023/2/14 10:47
 **/
public interface DogRepository extends ReactiveMongoRepository<Dog, String> {
    Flux<Dog> findDogByUsernameContaining(String name);
}