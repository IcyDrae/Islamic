package com.IcyDrae.Data.Quran;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Surah {
    private int id;
    private String name;
    private String transliteration;
    private String translation;
    private String type;
    @JsonProperty("total_verses")
    private int totalVerses;
    private List<Ayah> verses;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransliteration() {
        return this.transliteration;
    }

    public void setTransliteration(String transliteration) {
        this.transliteration = transliteration;
    }

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalVerses() {
        return this.totalVerses;
    }

    public void setTotalVerses(int totalVerses) {
        this.totalVerses = totalVerses;
    }

    public List<Ayah> getVerses() {
        return this.verses;
    }

    public void setVerses(List<Ayah> verses) {
        this.verses = verses;
    }

    @Override
    public String toString() {
        return id + " - " + this.transliteration;
    }
}
