package ru.job4j.collection;

/**
 * Класс реализует стек на базе связного списка.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 17.05.2022
 */
public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Метод удаляет первый элемент в стеке.
     *
     * @return значение удаляемого элемента.
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод добавляет первый элемент в стек.
     *
     * @param value элемент, который нужно добавить в стек.
     */
    public void push(T value) {
        linked.addFirst(value);
    }
}