package ru.job4j.collection.list;

/**
 * Интерфейс, для реализации списков.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 16.05.2022
 */
public interface List<E> extends Iterable<E> {
    void add(E value);

    E get(int index);
}