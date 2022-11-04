package org.liu.demo.mongodb.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author lzs
 * @Date 2022/10/28 16:35
 **/
@ToString
@Data
public class Movie {
    @MongoId
    private String id;
    private String title;//标题
    private String[] genres;//分类
    private Integer runtime;//时长
    private String rated;//分级
    private Integer year;//年份
    private String[] directors;//导演
    private String[] cast;//主要演员
    private String type;//类别
    private Date publishDate;//发布日期
    private BigDecimal boxOfficeReturnsInYear;//上映一年票房
    private String firstManActor;//第一男演员
    private String firstFemaleActor;//第一女演员
    private Long audienceCountInYear;//上映一年观影人数
    private Integer tomatometerScore;//烂番茄评分
    private Double doubanScore;//豆瓣评分
    private String[] tags;//电影内容主要标签
}
