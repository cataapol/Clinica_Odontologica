package com.backend.ClinicaOdontologica.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class JsonPrinter {

    public static String toString(Object t) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        return gson.toJson(t).trim().replace("\n", "").replace("\t", "");
    }

    //
}
