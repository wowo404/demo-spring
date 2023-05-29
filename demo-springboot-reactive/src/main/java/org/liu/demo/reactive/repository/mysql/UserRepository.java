package org.liu.demo.reactive.repository.mysql;

import org.liu.demo.reactive.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @Author lzs
 * @Date 2023/5/26 11:26
 **/
@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<UserDetails> findUserByUsername(String username);
}