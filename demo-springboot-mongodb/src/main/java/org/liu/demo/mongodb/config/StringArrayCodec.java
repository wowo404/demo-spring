package org.liu.demo.mongodb.config;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本用不上这种自定义的codec
 *
 * @author lzs
 * @Date 2024/10/30 16:00
 **/
public class StringArrayCodec implements Codec<String[]> {

    @Override
    public String[] decode(BsonReader reader, DecoderContext decoderContext) {
        if (reader.getCurrentBsonType() != BsonType.ARRAY) {
            return new String[0];
        }
        reader.readStartArray();

        List<String> list = new ArrayList<>();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            list.add(reader.readString());
        }

        reader.readEndArray();
        return list.toArray(new String[0]);
    }

    @Override
    public void encode(BsonWriter writer, String[] array, EncoderContext encoderContext) {
        if (null == array) {
            return;
        }
        writer.writeStartArray();
        for (String value : array) {
            if (null == value) {
                writer.writeNull();
            } else {
                writer.writeString(value);
            }
        }
        writer.writeEndArray();
    }

    @Override
    public Class<String[]> getEncoderClass() {
        return String[].class;
    }
}
