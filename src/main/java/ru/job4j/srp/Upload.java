package ru.job4j.srp;

public interface Upload {
    void loadFile(String source);

    void saveFile(String target);

    void print(String file);
}
