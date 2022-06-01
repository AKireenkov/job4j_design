package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Path> fileList = new HashMap<>();
    private Set<Path> duplicates = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty current = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (fileList.containsKey(current)) {
            duplicates.add(file);
            duplicates.add(fileList.get(current));
        } else {
            fileList.put(current, file);
        }
        duplicates.forEach(System.out::println);
        return super.visitFile(file, attrs);
    }
}