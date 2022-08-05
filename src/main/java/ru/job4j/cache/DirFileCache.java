package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String string = "";
        try (Stream<String> in = Files.lines(Paths.get(cachingDir.concat(key)))) {
            string = in.collect(Collectors.joining(", "));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return string;
    }
}
