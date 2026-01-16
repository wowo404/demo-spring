package org.liu.mockito.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.liu.mockito.constants.Constants;

import java.io.IOException;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public static final CustomLocalDateTimeSerializer INSTANCE = new CustomLocalDateTimeSerializer(LocalDateTime.class);

    protected CustomLocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (null == value) {
            gen.writeNull();
        } else {
            gen.writeString(value.atZone(UTC)
                    .withZoneSameInstant(TimezoneContext.getZoneId())
                    .format(Constants.FORMATTER));
        }
    }
}
