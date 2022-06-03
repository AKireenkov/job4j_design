package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName arguments = ArgsName.of(args);
        Path directory = Path.of(arguments.get("d"));
        String exclude = arguments.get("e");
        File output = new File(arguments.get("o"));
        checkValidArgs(args.length, directory, exclude, output);
        List<Path> files = Search.search(directory, p -> !p.toFile().getName().endsWith(exclude));
        zip.packFiles(files, output);
    }

    private static void checkValidArgs(int size, Path directory, String exclude, File output) {
        if (size != 3) {
            throw new IllegalArgumentException("check the number of arguments passed");
        }

        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("invalid path argument passed");
        }

        if (exclude.charAt(0) != '.') {
            throw new IllegalArgumentException("the format does not match pattern");
        }

        if (!output.getName().endsWith(".zip")) {
            throw new IllegalArgumentException("the format does not match zip");
        }
    }
}