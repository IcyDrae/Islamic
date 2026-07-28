package com.IcyDrae;

import com.IcyDrae.Data.NinetyNineName;
import com.IcyDrae.Services.NinetyNineNamesService;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        NinetyNineNamesService service = new NinetyNineNamesService();

        List<NinetyNineName> names = service.getAll();

        for (NinetyNineName name : names) {
            System.out.println(
                name.getArabic()
                + " - "
                + name.getTransliteration()
                + " - "
                + name.getEnglish()
            );
        }
    }
}
