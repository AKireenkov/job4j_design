package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {
    public static void main(String[] args) throws IOException {
        checkValidArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void checkValidArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("check the number of arguments passed");
        }

        Pattern path = Pattern.compile("([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)|(\\.)");
        Matcher matcherPath = path.matcher(args[0]);
        if (!matcherPath.find()) {
            throw new IllegalArgumentException("the path to the directory is not valid");
        }

        Pattern extension = Pattern.compile("\\.[a-zA-Z]{1,}");
        Matcher matcherExtension = extension.matcher(args[1]);
        if (!matcherExtension.find()) {
            throw new IllegalArgumentException("the format does not match pattern");
        }
    }
}