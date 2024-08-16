package org.liu.demo.mongodb.util;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 * @Author lzs
 * @Date 2022/11/30 16:13
 **/
public class MongoUtils {

    public static CodecRegistry getCodecRegistry() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register("org.liu.demo.mongodb.pojo").automatic(true).build();
        return fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    }

}
