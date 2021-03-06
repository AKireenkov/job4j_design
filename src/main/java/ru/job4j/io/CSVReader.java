package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CSVReader {
    public static void handle(ArgsName argsName) throws IOException {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String target = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");
        checkValidArgs(argsName.size(), path, delimiter, target, filter);

        int[] index = new int[filter.length];
        int count = 0;
        StringBuilder st = new StringBuilder();

        try (var scanner = new Scanner(path).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                String[] line = scanner.next().split(delimiter);
                for (int i = 0; i < line.length; i++) {
                    for (String f : filter) {
                        if (f.equals(line[i])) {
                            index[count++] = i;
                            break;
                        }
                    }
                }
                for (int j : index) {
                    st.append(line[j]).append(delimiter);
                }
                st.deleteCharAt(st.length() - 1)
                        .append(System.lineSeparator());
            }
        }
        if (!"stdout".equals(target)) {
            try (PrintWriter ot = new PrintWriter(new FileWriter(target))) {
                ot.print(st);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            System.out.println(st);
        }
    }

    private static void checkValidArgs(int size, Path path, String delimiter, String target, String[] filter) {
        if (size != 4) {
            throw new IllegalArgumentException("check the number of arguments passed");
        }

        if (!Files.exists(path) && (!Files.exists(Path.of(target)) || !"stdout".equals(target))) {
            throw new IllegalArgumentException("invalid path argument passed");
        }

        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("the separator does not match the format");
        }

        if (filter.length == 0) {
            throw new IllegalArgumentException("the filter is not registered");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName arguments = ArgsName.of(args);
        Path path = Path.of(arguments.get("path"));
        String delimiter = arguments.get("delimiter");
        String target = arguments.get("out");
        String[] filter = arguments.get("filter").split(",");
        checkValidArgs(arguments.size(), path, delimiter, target, filter);
        handle(arguments);
    }
}