package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car audi = new Car(false, 30000, "Q5", new Equipment(true, 4),
                new String[]{"Petr Ivanov", "Andrey Kireenkov"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(audi));

        final String personJson =
                "{"
                        + "\"sedan\":false,"
                        + "\"cost\":30000,"
                        + "\"model\":\"Q5\","
                        + "\"equipment\":"
                        + "{"
                        + "\"conditioner\":\"true\","
                        + "\"winterTires\":\"4\""
                        + "},"
                        + "\"owners\":"
                        + "[\"Petr Ivanov\",\"Andrey Kireenkov\"]"
                        + "}";
        final Car carMod = gson.fromJson(personJson, Car.class);
        System.out.println(carMod);
    }
}