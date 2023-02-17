package org.liu.demo.reactive.handler;

import lombok.RequiredArgsConstructor;
import org.liu.demo.reactive.model.Student;
import org.liu.demo.reactive.repository.mysql.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static java.lang.Long.parseLong;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @Author lzs
 * @Date 2023/2/14 17:33
 **/
@RequiredArgsConstructor
@Component
public class StudentHandler {

    private final StudentRepository studentRepository;

    public Mono<ServerResponse> getAllStudents(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(studentRepository.findAll(), Student.class);
    }

    public Mono<ServerResponse> addStudent(ServerRequest serverRequest) {
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(studentRepository.saveAll(serverRequest.bodyToMono(Student.class)), Student.class);
    }

    public Mono<ServerResponse> deleteStudent(ServerRequest serverRequest) {
        return studentRepository.findById(parseLong(serverRequest.pathVariable("id")))
                .flatMap(user -> studentRepository.delete(user).then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

}
