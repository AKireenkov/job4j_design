package ru.job4j.io;

import java.io.*;

public class Analizy {
    public static void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean error = false;
            for (String s : read.lines().toList()) {
                boolean haveCode = s.contains("400 ") || s.contains("500 ");
                if (!error && haveCode) {
                    out.print(getTimeLog(s) + ";");
                    error = true;
                } else if (error && !haveCode) {
                    error = false;
                    out.print(getTimeLog(s) + ";");
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static String getTimeLog(String line) {
        String[] log = line.split(" ");
        return log[1];
    }
}
