package org.liu.demo.reactive.handler;

import org.liu.demo.reactive.model.Person;
import org.liu.demo.reactive.repository.mongodb.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @Author lzs
 * @Date 2023/2/14 14:13
 **/
@Component
public class PersonHandler {
    @Autowired
    private PersonRepository personRepository;

    public Mono<ServerResponse> addPerson(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(personRepository.saveAll(serverRequest.bodyToMono(Person.class)), Person.class);
    }

    public Mono<ServerResponse> deletePerson(ServerRequest serverRequest) {
        return personRepository.findById(serverRequest.pathVariable("id"))
                .flatMap(p -> personRepository.delete(p).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getAllPerson(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(personRepository.findAll(), Person.class);
    }
}
