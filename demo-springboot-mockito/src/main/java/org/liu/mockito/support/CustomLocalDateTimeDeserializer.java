package org.liu.mockito.support;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.liu.mockito.constants.Constants;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public static final CustomLocalDateTimeDeserializer INSTANCE = new CustomLocalDateTimeDeserializer(LocalDateTime.class);

    protected CustomLocalDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        if (StrUtil.isBlank(p.getText())) {
            return null;
        }
        return LocalDateTime.parse(p.getText(), Constants.FORMATTER)
                .atZone(TimezoneContext.getZoneId())
                .withZoneSameInstant(ZoneOffset.UTC)
                .toLocalDateTime();
    }
}
