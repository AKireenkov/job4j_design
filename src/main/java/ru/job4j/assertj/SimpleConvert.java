package ru.job4j.assertj;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * В классе реализованы методы конвертации массива элементов.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 24.08.2022
 */
public class SimpleConvert {

    /**
     * @param example список элементов в виде массива.
     * @return тот же список, без преобразований.
     */
    public String[] toArray(String... example) {
        return example;
    }

    /**
     * Метод преобразует массив элементов в stream, затем, собирает в коллекцию
     *
     * @param example список элементов в виде массива.
     * @return массив элементов преобразованный в коллекцию ArrayList
     */
    public List<String> toList(String... example) {
        return Arrays.stream(example).toList();
    }

    /**
     * Метод преобразует массив элементов в stream, затем, собирает в коллекцию
     *
     * @param example список элементов в виде массива.
     * @return массив элементов преобразованный в коллекцию Set
     */
    public Set<String> toSet(String... example) {
        return Arrays.stream(example).collect(Collectors.toSet());
    }

    /**
     * Метод преобразует массив элементов в stream, затем, собирает в карту
     *
     * @param example список элементов в виде массива.
     * @return массив элементов преобразованный в карту
     */
    public Map<String, Integer> toMap(String... example) {
        AtomicInteger i = new AtomicInteger();
        return Arrays.stream(example)
                .collect(Collectors.toMap(
                        e -> e,
                        e -> i.getAndIncrement(),
                        (first, second) -> first)
                );
    }
}