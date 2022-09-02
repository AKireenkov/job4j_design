package ru.job4j.assertj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс реализует загрузку списка имен, и сохранение его в карту.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 23.08.2022
 */
public class NameLoad {
    private final Map<String, String> values = new HashMap<>();

    /**
     * метод обрабатывает список пар ключ=значение, разбивает на пары по знаку =,
     * добавляет полученную пару, в карту values,
     * выбрысывает исключение, при получении пустого списка.
     *
     * @param names - список пар ключ=значение
     */
    public void parse(String... names) {
        if (names.length == 0) {
            throw new IllegalArgumentException("Names array is empty");
        }
        values.putAll(Arrays.stream(names)
                .map(String::trim)
                .filter(this::validate)
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(
                        e -> e[0],
                        e -> e[1],
                        (first, second) -> String.format("%s+%s", first, second)
                )));
    }

    /**
     * метод, проверяет пару ключ=значение, на наличие знака =, ключа и значения в одной конструкции.
     *
     * @param name - пара ключ=значение.
     * @return возвращает true, если пара корректна.
     * Выбрасывает исключение, если пара не соответствует шаблону.
     */
    private boolean validate(String name) {
        if (!name.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain the symbol \"=\"", name)
            );
        }
        if (name.startsWith("=")) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a key", name)
            );
        }
        if (name.indexOf("=") == name.length() - 1) {
            throw new IllegalArgumentException(
                    String.format("this name: %s does not contain a value", name)
            );
        }
        return true;
    }

    /**
     * Метод проверяет карту на наличие данных.
     *
     * @return возвращает карту с данными.
     * Либо, выбрасывает исключение, если карта пустая.
     */
    public Map<String, String> getMap() {
        if (values.isEmpty()) {
            throw new IllegalStateException("collection contains no data");
        }
        return values;
    }
}
