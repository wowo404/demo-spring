package org.liu.demo.reactive.config;

import org.liu.demo.reactive.handler.PersonHandler;
import org.liu.demo.reactive.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @Author lzs
 * @Date 2023/2/14 14:15
 **/
@EnableMongoRepositories
@EnableR2dbcRepositories
@Configuration
public class RouterConfiguration {
    @Bean
    RouterFunction<ServerResponse> personRouter(PersonHandler personHandler) {
        return RouterFunctions
                .nest(RequestPredicates.path("/person"),
                        RouterFunctions.route(RequestPredicates.POST("/"), personHandler::addPerson)
                                .andRoute(RequestPredicates.GET("/"), personHandler::getAllPerson)
                                .andRoute(RequestPredicates.DELETE("/{id}"), personHandler::deletePerson));
    }

    @Bean
    RouterFunction<ServerResponse> studentRouter(StudentHandler studentHandler) {
        return RouterFunctions
                .nest(RequestPredicates.path("/student"),
                        RouterFunctions.route(RequestPredicates.POST("/"), studentHandler::addStudent)
                                .andRoute(RequestPredicates.GET("/"), studentHandler::getAllStudents)
                                .andRoute(RequestPredicates.DELETE("/{id}"), studentHandler::deleteStudent));
    }
}