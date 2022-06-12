package ru.job4j.search;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Search {
    private static List<Path> matchingFiles = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ArgsName arguments = ArgsName.of(args);
        Path directory = Path.of(arguments.get("d"));
        String fileName = arguments.get("n");
        String searchType = arguments.get("t");
        String target = arguments.get("o");

        checkValidateArgs(arguments.size(), directory, fileName, searchType, target);


        if ("mask".equals(searchType)) {
            matchingFiles.addAll(search(directory, file -> file.toFile().getName().endsWith(fileName)));
        } else if ("name".equals(searchType)) {
            matchingFiles.addAll(search(directory, file -> file.toFile().getName().equals(fileName)));
        } else if ("regex".equals(searchType)) {
            matchingFiles.addAll(search(directory, file -> Pattern.matches(fileName, file.toFile().getName())));
        }

        saveMatchingFiles(target);
    }

    private static void saveMatchingFiles(String target) {
        try (PrintWriter out = new PrintWriter(new FileWriter(target))) {
            matchingFiles.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkValidateArgs(int size, Path directory, String fileName, String searchType, String target) {
        if (size != 4) {
            throw new IllegalArgumentException("invalid number of arguments");
        }
        if (!((Files.exists(directory) || Files.isDirectory(directory))
                && Files.exists(Path.of(target)))) {
            throw new IllegalArgumentException("invalid path argument passed");
        }

        if (fileName.charAt(0) != '.' && !Pattern.matches("\\w+\\.[a-z]+", fileName) && !fileName.contains(".")) {
            throw new IllegalArgumentException("the file is searched by mask, name, or regular expression");
        }

        List<String> list = List.of("mask", "name", "regex");
        if (!("mask".equals(searchType) || "name".equals(searchType) || "regex".equals(searchType))) {
            throw new IllegalArgumentException("enter one of the correct search types" + list);
        }

    }

    private static List<Path> search(Path path, Predicate<Path> condition) throws IOException {
        SearchFiles searchFiles = new SearchFiles(condition);
        Files.walkFileTree(path, searchFiles);
        return searchFiles.getFiles();
    }
}
