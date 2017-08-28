package io.einharjar.cognitive_wrapper.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class Mapper {

    private static final Mapper mapper = new Mapper();
    private ObjectMapper jsonMapper;

    private Mapper() {
        this.jsonMapper = new ObjectMapper();
    }

    public static Mapper getInstance() {
        return mapper;
    }

    public <T> T read(String s, Class<T> clazz) throws IOException {
        return jsonMapper.readValue(s, clazz);
    }

    public String write(Object s) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(s);
    }
}
