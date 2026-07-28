package com.IcyDrae;

import java.util.List;

import com.IcyDrae.Data.Dhikr;
import com.IcyDrae.Services.AdhkarService;
import com.IcyDrae.Services.QuranService;
import com.IcyDrae.Data.Quran.Ayah;
import com.IcyDrae.Data.Quran.Surah;

public class App
{
    public static void main(String[] args) throws Exception
    {
        QuranService QuranService = new QuranService();

        Surah Surah = QuranService.getSurah(115);
        
        for (Ayah Ayah : Surah.getVerses()) {
            System.out.println(Ayah.getId() + ". " + Ayah.getText());
            System.out.println(Ayah.getId() + ". " + Ayah.getTranslation());
            System.out.println();
        }

    }
}
