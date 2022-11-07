package org.liu.demo.mongodb.util;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @Author lzs
 * @Date 2022/11/7 11:21
 **/
public class CollectionNameUtils {

    public static String getSuffix(){
        return DateUtil.format(new Date(), "yyyyMM");
    }

}
