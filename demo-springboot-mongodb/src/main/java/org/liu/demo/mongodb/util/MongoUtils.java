package org.liu.demo.mongodb.util;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.liu.demo.mongodb.config.StringArrayCodec;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @Author lzs
 * @Date 2022/11/30 16:13
 **/
public class MongoUtils {

    public static CodecRegistry getCodecRegistry() {
        //mongodb内置不支持bson array与java的数组的互相转换，使用List或Set就可以
        //这个自定义的Codec只是为了测试及深入了解mongodb的api，实际情况下用不上
        CodecRegistry codecRegistry = CodecRegistries.fromCodecs(new StringArrayCodec());
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("org.liu.demo.mongodb.pojo").automatic(true).build();
        return fromRegistries(codecRegistry, getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    }

}
