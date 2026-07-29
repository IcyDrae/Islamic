package com.IcyDrae.Services;

import com.IcyDrae.Data.NinetyNineNames;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;

public class NinetyNineNamesService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<NinetyNineNames> getAll() throws Exception {
        return objectMapper.readValue(
            readFile(),
            new TypeReference<List<NinetyNineNames>>() {}
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
