package org.liu.mockito.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.liu.mockito.constants.Constants;

import java.io.IOException;
import java.time.Instant;

public class CustomInstantSerializer extends StdSerializer<Instant> {

    public static final CustomInstantSerializer INSTANCE = new CustomInstantSerializer(Instant.class);

    protected CustomInstantSerializer(Class<Instant> t) {
        super(t);
    }

    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (null == value) {
            gen.writeNull();
        } else {
            gen.writeString(value.atZone(TimezoneContext.getZoneId()).format(Constants.FORMATTER));
        }
    }
}
