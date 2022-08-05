package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Emulator {

    public static final String PATH = "src/main/java/ru/job4j/cache/files/";

    private void init(Scanner scanner, DirFileCache dirFileCache) {
        boolean run = true;
        String fileName = " ";
        while (run) {
            List.of("1. Указать кэшируемую директорию",
                            "2. Загрузить содержимое указанного файла в кэш",
                            "3. Получить содержимое файла из кэша")
                    .forEach(System.out::println);
            int num = Integer.parseInt(scanner.nextLine());

            if (num == 1) {
                fileName = scanner.nextLine();
            } else if (num == 2) {
                String string = "";
                try (Stream<String> in = Files.lines(Paths.get(PATH.concat(fileName)))) {
                    string = in.collect(Collectors.joining());
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                dirFileCache.put(fileName, string);
            } else if (num == 3) {
                if (fileName.equals(" ")) {
                    throw new IllegalArgumentException("Не задан кэшируемый файл");
                }
                System.out.println("Введите название файла");
                System.out.println(dirFileCache.load(scanner.nextLine()));
            } else {
                run = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator();
        DirFileCache dirFileCache = new DirFileCache(PATH);

        emulator.init(scanner, dirFileCache);
    }
}
