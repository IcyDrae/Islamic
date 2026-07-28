package com.IcyDrae.Services;

import com.IcyDrae.Data.NinetyNineName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class NinetyNineNamesService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<NinetyNineName> getAll() throws Exception {
        return objectMapper.readValue(
            readFile(),
            new TypeReference<List<NinetyNineName>>() {}
        );
    }

    private InputStream readFile() {
        InputStream stream =
            getClass()
            .getClassLoader()
            .getResourceAsStream("99names.json");

        if (stream == null) {
            throw new RuntimeException(
                "Could not find 99names.json"
            );
        }

        return stream;
    }
}
