package org.liu.mongo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Data
public class MongoSaveModel implements Serializable {

    private String id;
    private Short serial;
    private String title;
    private Integer age;
    private Float score;
    private Double salary;
    private Boolean ok;
    private Date createTime;
    private BigDecimal amount;
//    private BigInteger galaxyNumber;
    private List<InnerModel> innerModels;
//    private Integer[] workIds;

    @Getter
    @Setter
    public static class InnerModel{
        private Integer id;
        private String name;
    }

}
