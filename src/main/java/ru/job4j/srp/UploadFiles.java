package ru.job4j.srp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UploadFiles implements Upload {
    List<String> value = new ArrayList<>();

    @Override
    public void loadFile(String source) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(value::add);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        value.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Upload upload = new UploadFiles();
        File source = new File("source.txt");
        File target = new File("target.txt");
        upload.loadFile(source.getAbsolutePath());
        upload.saveFile(target.getAbsolutePath());
    }

    @Override
    public void saveFile(String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            value.forEach(out::println);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
