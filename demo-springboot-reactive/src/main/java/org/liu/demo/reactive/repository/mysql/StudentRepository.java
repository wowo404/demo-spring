package org.liu.demo.reactive.repository.mysql;

import org.liu.demo.reactive.model.Student;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lzs
 * @Date 2023/2/14 17:32
 **/
@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student, Long> {
}