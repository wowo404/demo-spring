package org.liu.mockito.support;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.liu.mockito.constants.Constants;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

public class CustomInstantDeserializer extends StdDeserializer<Instant> {

    public static final CustomInstantDeserializer INSTANCE = new CustomInstantDeserializer(Instant.class);

    protected CustomInstantDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        if (StrUtil.isBlank(p.getText())) {
            return null;
        }
        return LocalDateTime.parse(p.getText(), Constants.FORMATTER)
                .atZone(TimezoneContext.getZoneId())
                .toInstant();
    }
}
