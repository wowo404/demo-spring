package org.liu.demo.mongodb.service;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.CountResp;
import org.liu.demo.mongodb.pojo.Movie;
import org.liu.demo.mongodb.util.BeanValueUtils;
import org.liu.demo.mongodb.util.MongoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/12/2 10:11
 **/
@SpringBootTest
public class MoviesServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void save() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Movie movie = new Movie();
        BeanValueUtils.autoSetValue(movie);
        mongoTemplate.save(movie, "movies");
    }

    @Test
    void query(){
        List<Movie> movies = mongoTemplate.findAll(Movie.class, "movies");
        System.out.println(movies);
    }

    @Test
    void test() {
        List<Bson> bsons = Arrays.asList(
                Aggregates.match(Filters.gt("runtime", 110)),
                Aggregates.group("$type", Accumulators.sum("count", 1))
        );
        AggregateIterable<CountResp> iterable = mongoTemplate.getCollection("movies")
                .withCodecRegistry(MongoUtils.getCodecRegistry())
                .aggregate(bsons, CountResp.class);
        MongoCursor<CountResp> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            CountResp countResp = iterator.next();
            System.out.println(countResp);
        }
    }

}
