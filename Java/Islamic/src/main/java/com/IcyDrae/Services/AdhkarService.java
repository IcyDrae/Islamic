package com.IcyDrae.Services;

import com.IcyDrae.Data.Dhikr;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AdhkarService {
    private final ObjectMapper ObjectMapper = new ObjectMapper();

    public AdhkarService() {

    }

    public List<Dhikr> getAll() throws Exception {
        InputStream File = this.readFile();

        return ObjectMapper.readValue(
                File,
                new TypeReference<List<Dhikr>>() {}
        );
    }

    public Dhikr getRandom() throws Exception {
        List<Dhikr> Adhkar = this.getAll();

        if (Adhkar.isEmpty()) {
            return null;
        }

        int Index = ThreadLocalRandom.current().nextInt(Adhkar.size());

        return Adhkar.get(Index);
    }

    private InputStream readFile() throws RuntimeException {
        InputStream stream = getClass()
                .getClassLoader()
                .getResourceAsStream("dhikr.json");

        if (stream == null) {
            throw new RuntimeException("Could not find dhikr.json");
        }

        return stream;
    }
}
