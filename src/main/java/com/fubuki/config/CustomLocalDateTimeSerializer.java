package com.fubuki.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime
            , JsonGenerator jsonGenerator
            , SerializerProvider serializerProvider)
            throws IOException {
        //获取毫秒数
        long time = localDateTime.toInstant(ZoneOffset.of("+8"))
                .toEpochMilli();
        jsonGenerator.writeNumber(time);
    }
}
