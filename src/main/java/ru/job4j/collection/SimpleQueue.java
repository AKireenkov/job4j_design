package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс реализует очередь на двух стеках, по принципу first input first output.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 17.05.2022
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int lengthIn;
    int lengthOut;

    /**
     * Метод удаляет первый элемент из второго стека.
     * Если второй стек - пустой, переклвдываем в него все элементы из первого стека:
     * in.pop() - достаем первый элемент из первого стека, out.push() - кладем элемент во второй стек,
     * таким образом первый становится последним; второй - предпоследним и т.д.
     * Выбрасывает исключение, если стеки пусты.
     *
     * @return
     */
    public T poll() {
        if (lengthOut == 0 && lengthIn == 0) {
            throw new NoSuchElementException();
        }

        if (lengthOut == 0) {
            while (lengthIn != 0) {
                out.push(in.pop());
                lengthIn--;
                lengthOut++;
            }
        }
        lengthOut--;
        return out.pop();
    }

    /**
     * Метод добавляет в первый стек элемент в начало списка.
     *
     * @param value добавляемый элемент в стек.
     */
    public void push(T value) {
        in.push(value);
        lengthIn++;
    }
}