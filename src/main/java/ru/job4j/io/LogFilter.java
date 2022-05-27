package ru.job4j.io;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> st = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String s : in.lines().toList()) {
                String[] string = s.split(" ");
                if (string[string.length - 2].contains("404")) {
                    st.add(s);
                    st.add(System.lineSeparator());
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return st;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}