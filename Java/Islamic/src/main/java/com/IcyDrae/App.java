package com.IcyDrae;

import java.util.List;

import com.IcyDrae.Data.Dhikr;
import com.IcyDrae.Services.AdhkarService;

public class App 
{
    public static void main(String[] args) throws Exception
    {
        AdhkarService AdhkarService = new AdhkarService();

        Dhikr RandomDhikr = AdhkarService.getRandom();

        System.out.println(RandomDhikr.getText());
    }
}
