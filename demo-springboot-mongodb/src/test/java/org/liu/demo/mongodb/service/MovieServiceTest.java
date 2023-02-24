package org.liu.demo.mongodb.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.convert.Convert;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.BsonArray;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.liu.demo.mongodb.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void batchInsert() {
        for (int i = 0; i < 1000; i++) {
            movieService.batchInsert(50000);
        }
    }

    @Test
    void query() {
        List<Movie> movies = movieService.queryAll("t", 7.656357981625741, "a");
        System.out.println(movies);
    }

    @Test
    void queryByNaming() {
        List<Movie> movies = movieService.queryByNaming("t", 7.0);
        System.out.println(movies);
    }

    @Test
    void queryByNativeJson() {
        List<Movie> movies = movieService.queryByNativeJson("am", 6.0);
        System.out.println(movies.size());
        System.out.println(movies);
    }

    @Test
    void queryPage() {
        Page<Movie> pageList = movieService.pageList(1, 10, "am", 9.0);
        System.out.println(pageList);
    }

    @Test
    void queryArray() {
        FindIterable<Document> findIterable = mongoTemplate.getCollection("movie")
                .find(Filters.eq("_id", new ObjectId("635f24c5ab5c763fd439dac7")));
        for (Document document : findIterable) {
            Object genres = document.get("genres");
            String[] list = Convert.toStrArray(genres);
            System.out.println(Arrays.toString(list));
        }
    }

    //使用spring data的Repository做update，调用的CrudRepository接口是save
    @Test
    void update() {
        //这种情况会清空其他字段，只保留当前set了的字段
        Movie movie = new Movie();
        movie.setId("635f24c5ab5c763fd439dac8");
        movie.setTitle("from update");
        movieService.update(movie);
    }

    @Test
    void update1() {
        Movie movie = movieService.findById("635f24c5ab5c763fd439dac8");

        Movie update = new Movie();
        update.setId("635f24c5ab5c763fd439dac8");
        update.setTitle("repositoryApiUpdate1");
        //拷贝属性，排除null
        BeanUtil.copyProperties(update, movie, CopyOptions.create().ignoreNullValue());

        movieService.update(movie);
    }

    //使用mongo原生api做更新
    @Test
    void update2() {
        Bson filter = Filters.eq("_id", new ObjectId("635f24c5ab5c763fd439dac8"));
        Bson update = Updates.combine(Updates.set("title", "originalApi"),
                Updates.set("runtime", 120),
                Updates.addEachToSet("genres", Arrays.asList("sex", "violence")));
        mongoTemplate.getCollection("movie").updateOne(filter, update);
    }

    //Updates with runCommand
    @Test
    void update3() {
        BsonArray updates = BsonArray.parse("[{q:{_id:ObjectId('635f24c5ab5c763fd439dac8')},u:[{$set:{runtimeMinute:{$multiply:['$runtime',60]}}}]}]");

        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("update", "movie");
        dbObject.put("updates", updates);
        Document document = mongoTemplate.getDb().runCommand(dbObject);
        System.out.println(document);
    }

}