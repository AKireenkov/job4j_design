package ru.job4j.io;

import java.io.*;
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
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return st;
    }

    public void save(List<String> log, String rsl) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(rsl)
                )
        )) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        logFilter.save(log, "404.txt");
    }
}