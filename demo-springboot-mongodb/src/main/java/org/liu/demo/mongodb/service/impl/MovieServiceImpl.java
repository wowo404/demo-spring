package org.liu.demo.mongodb.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.liu.demo.mongodb.pojo.Movie;
import org.liu.demo.mongodb.repository.MovieRepository;
import org.liu.demo.mongodb.service.MovieService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author lzs
 * @Date 2022/10/28 17:38
 **/
@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final List<String> ratedList = new ArrayList<>(Arrays.asList("G", "PG", "PG-13", "R", "NC-17"));
    private final List<String> typeList = new ArrayList<>(Arrays.asList("movie", "song", "sex"));

    @Override
    public String batchInsert(Integer size) {
        List<Movie> movies = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            Movie movie = new Movie();
            movie.setTitle(RandomUtil.randomString(4));
            movie.setGenres(new String[]{RandomUtil.randomString(2), RandomUtil.randomString(3)});
            movie.setRuntime(RandomUtil.randomInt(80, 300));
            movie.setRated(RandomUtil.randomEle(ratedList));
            movie.setYear(RandomUtil.randomInt(1960, 2022));
            movie.setDirectors(new String[]{RandomUtil.randomString(2), RandomUtil.randomString(4)});
            movie.setCast(new String[]{RandomUtil.randomString(2), RandomUtil.randomString(4)});
            movie.setType(RandomUtil.randomEle(typeList));
            movie.setPublishDate(RandomUtil.randomDate(new Date(), DateField.DAY_OF_YEAR, -60, 0));
            movie.setBoxOfficeReturnsInYear(RandomUtil.randomBigDecimal(new BigDecimal("10000"), new BigDecimal("10000000000")));
            movie.setFirstManActor(RandomUtil.randomString(3));
            movie.setFirstFemaleActor(RandomUtil.randomString(5));
            movie.setAudienceCountInYear(RandomUtil.randomLong(10L, 10000000000L));
            movie.setTomatometerScore(RandomUtil.randomInt(1, 99));
            movie.setDoubanScore(RandomUtil.randomDouble(0.1, 9.9));
            movie.setTags(new String[]{RandomUtil.randomString(2), RandomUtil.randomString(3)});

            movies.add(movie);
        }
        movieRepository.saveAll(movies);
        return "success";
    }

    @Override
    public List<Movie> queryAll(String title, Double doubanScore, String firstManActor) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDoubanScore(doubanScore);
        movie.setFirstManActor(firstManActor);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatcher::contains)
                .withMatcher("firstManActor", ExampleMatcher.GenericPropertyMatchers.contains());

        return movieRepository.findAll(Example.of(movie, exampleMatcher), Sort.by(Sort.Order.asc("runTime"), Sort.Order.desc("year")));
    }

    @Override
    public List<Movie> queryByNaming(String title, Double doubanScore) {
        Sort sort = Sort.by(Sort.Order.asc("runTime"), Sort.Order.desc("year"));
        return movieRepository.findByTitleContainingAndDoubanScoreGreaterThanEqual(title, doubanScore, sort);
    }

    @Override
    public List<Movie> queryByNativeJson(String title, Double doubanScore) {
        return movieRepository.findByNativeJson(title, doubanScore);
    }

    @Override
    public Page<Movie> pageList(int page, int size, String title, Double doubanScore) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return movieRepository.pageByNativeJson(title, doubanScore, pageRequest);
    }

    @Override
    public void update(Movie movie) {
        //save方法：id存在时，会做更新，但会更新所有的字段
        movieRepository.save(movie);
    }

    @Override
    public Movie findById(String id) {
        return movieRepository.findById(id).orElse(null);
    }
}
