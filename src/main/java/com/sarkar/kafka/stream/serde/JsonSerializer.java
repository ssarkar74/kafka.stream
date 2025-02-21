package com.sarkar.kafka.stream.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JsonSerializer<T> implements Serializer<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    public JsonSerializer() {
    }


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //Nothing to confgure
    }

    @Override
    public byte[] serialize(String topic, T data) {
        if(data == null){
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON Message", e);
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
