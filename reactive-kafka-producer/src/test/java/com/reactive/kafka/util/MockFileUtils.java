package com.reactive.kafka.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Slf4j
public class MockFileUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public static <T> T getResource(String resource, Class<T> clazz) {
        final InputStream inStream = MockFileUtils.class.getClassLoader().getResourceAsStream(resource);
        try {
            if (String.class.equals(clazz)) {
                return (T) new BufferedReader(new InputStreamReader(inStream)).lines().collect(Collectors.joining("\n"));
            }
            return MAPPER.readValue(inStream, clazz);
        } catch (IOException e) {
            log.error("Can't serialize: ", e);
        }
        return null;
    }

}
