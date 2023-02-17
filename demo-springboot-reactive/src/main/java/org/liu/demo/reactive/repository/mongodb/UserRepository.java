package org.liu.demo.reactive.repository.mongodb;

import org.liu.demo.reactive.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import reactor.core.publisher.Flux;

/**
 * @Author lzs
 * @Date 2023/2/14 10:47
 **/
@EnableMongoRepositories
public interface UserRepository extends ReactiveMongoRepository<User,String> {
    Flux<User> findUserByUsernameContaining(String name);
}