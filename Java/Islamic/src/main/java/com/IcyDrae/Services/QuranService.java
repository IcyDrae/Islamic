package com.IcyDrae.Services;

import java.io.InputStream;
import com.IcyDrae.Data.Quran.Surah;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class QuranService {
    public QuranService() {

    }

    public Surah getSurah(int Number) throws Exception {
        ObjectMapper ObjectMapper = new ObjectMapper();

        List<Surah> Surahs = ObjectMapper.readValue(
            this.readFile(),
            new TypeReference<List<Surah>>() {}
        );

        return Surahs.stream()
                .filter(Surah -> Surah.getId() == Number)
                .findFirst()
                .orElse(null);
    }

    private InputStream readFile() throws RuntimeException {
        InputStream stream = getClass()
                .getClassLoader()
                .getResourceAsStream("quran_en.json");

        if (stream == null) {
            throw new RuntimeException("Could not find quran_en.json");
        }

        return stream;
    }
}
