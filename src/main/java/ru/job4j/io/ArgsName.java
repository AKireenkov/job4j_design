package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public int size() {
        return values.size();
    }

    public String get(String key) {
        String get = values.get(key);
        if (get == null) {
            throw new IllegalArgumentException("the requested item does not exist");
        }
        return get;
    }

    private void parse(String[] args) {
        for (String arg : args) {
            String[] val = arg.split("=", 2);
            checkValidate(val);
            values.put(val[0].replace("-", ""), val[1]);
        }
    }

    private void checkValidate(String[] val) {
        if (val.length < 2
                || val[0].isEmpty()
                || val[0].charAt(0) != '-'
                || val[0].length() == 1
                || val[1].isEmpty()) {
            throw new IllegalArgumentException("the transmitted value does not match the -key=value pattern");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("the array must not be empty");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}