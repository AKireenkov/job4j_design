package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.ListIterator;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            List<String> lines = read.lines().toList();
            ListIterator<String> it = lines.listIterator();
            while (it.hasNext()) {
                String cur = it.next();
                if (cur.contains("400 ") || cur.contains("500 ")) {
                    out.print(getTimeLog(cur) + ";");
                    while (cur.contains("400 ") || cur.contains("500 ")) {
                        cur = it.next();
                    }
                    out.println(getTimeLog(cur) + ";");
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static String getTimeLog(String line) {
        String[] log = line.split(" ");
        return log[1];
    }
}
