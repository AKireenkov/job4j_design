package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public final String pathNames = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public final String pathSurnames = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public final String pathPatrons = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public final String separator = " ";
    public final Integer newUsers = 1000;

    public static List<String> names;
    public static List<String> surnames;
    public static List<String> patrons;
    private static List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < newUsers; i++) {
            users.add(new User(
                    surnames.get(random.nextInt(surnames.size()))
                            .concat(separator)
                            .concat(names.get(random.nextInt(names.size())))
                            .concat(separator)
                            .concat(patrons.get(random.nextInt(patrons.size())))));
        }
    }

    private void readAll() {
        try {
            names = read(pathNames);
            surnames = read(pathSurnames);
            patrons = read(pathPatrons);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public static List<User> getUsers() {
        return users;
    }
}