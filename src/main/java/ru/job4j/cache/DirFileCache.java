package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс, реализует абстрактный метод load(), для загрузки данных, в даннаом случае - из указанногой дирректории и файла.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.08.2022
 */
public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод, сопоставляет указанные дирректорию и файл, и получает все элементы в формате String.
     * Если указанный файл или директория не найдены - выбрасывается исключение.
     *
     * @param key ключ, по которому ищем элемент для выгрузки данных
     * @return строку, которую удалось извлечь из файла.
     */
    @Override
    protected String load(String key) {
        String string = "";
        try {
            string = Files.readString(Path.of(cachingDir, key));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return string;
    }
}
