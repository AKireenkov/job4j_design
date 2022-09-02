package ru.job4j.assertj;

/**
 * Класс используется для демонстрации тестов по теме assertj.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 23.08.2022
 */
public class SimpleModel {
    private String name = "";

    public String getName() {
        if (name.length() == 0) {
            throw new IllegalArgumentException();
        }
        return name;
    }

    /**
     * Метод записывает имя в переменную name. Перед этим, проверяя на количество символов.
     * Если, количество символов в имени не совпадает с входным параметром number,
     * выбрасывается исключени
     *
     * @param word   слово, которое записываем в переменную объекта
     * @param number количество символов в слове
     */
    public void setName(String word, int number) {
        if (word.length() != number) {
            throw new IllegalArgumentException(
                    String.format("this word: %s has length not equal to: : %s", word, number)
            );
        }
        this.name = name;
    }
}
