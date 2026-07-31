package com.IcyDrae.Services;

import java.io.InputStream;
import com.IcyDrae.Data.Quran.Surah;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class QuranService {
    private final List<Surah> surahs;

    public QuranService() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            this.surahs = objectMapper.readValue(
                readFile(),
                new TypeReference<List<Surah>>() {}
            );
        }
        catch (Exception e) {
            throw new RuntimeException(
                "Failed to load Quran",
                e
            );
        }
    }

    public List<Surah> getSurahs() throws Exception {
        return this.surahs;
    }


    public Surah getSurah(int number) {
        return this.surahs.stream()
                .filter(surah -> surah.getId() == number)
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
