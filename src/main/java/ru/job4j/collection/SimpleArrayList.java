package ru.job4j.collection;

import ru.job4j.list.List;

import java.util.*;

/**
 * Реализация динамического списка на основе массива.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 14.05.2022
 */
public class SimpleArrayList<T> implements List<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * Метод добавления элемента в список.
     * Если массив заполнен полностью, происходит расширение массива методом arrayExtension() в 2 раза.
     *
     * @param value значение, которое записываем в массив.
     */
    @Override
    public void add(T value) {
        if (size == container.length) {
            arrayExtension();
        }
        container[size++] = value;
        modCount++;
    }

    /**
     * Метод замены элемента в массиве, по индексу.
     *
     * @param index    индекс элемента, который нужно заменить.
     * @param newValue новое значение элемента
     * @return старое значение, элемента по индексу.
     */
    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    /**
     * Метод удаления элемента по индексу.
     * Получаем новый массив, путем копирования массива container.
     * Копируем часть элементов начиная с index + 1,
     * вставляем в этот же массив, начиная с index.
     * Размер нового массива, уменьшается на 1.
     * Последний элемент обнуляем.
     *
     * @param index номер удаляемого элемента в массиве.
     * @return значение удаляемого элемента.
     */
    @Override
    public T remove(int index) {
        final int newSize = size - 1;
        T el = get(index);
        System.arraycopy(container, index + 1, container, index, newSize - index);
        container[size - 1] = null;
        size = newSize;
        modCount++;
        return el;
    }

    /**
     * Метод получения значения элемента в массиве.
     *
     * @param index индекс элемента, который нужно получить.
     * @return значение найденного элемента.
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    /**
     * @return размер массива.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Метод увеличивает размер массива в 2 раза.
     */
    public void arrayExtension() {
        int newSize = size == 0 ? 2 : size * 2;
        container = Arrays.copyOf(container, newSize);
    }

    /**
     * Итератор проходит по всем элементам списка, проверяет наличие следующего элемента в списке.
     * Выбрасывает исключение, в случае, если список был изменен после создания итератора.
     *
     * @return boolean значение, либо значение элемента в списке.
     */
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            /**
             * Метод проверяет наличие следующего элемента в списке.
             * Выбрасывает исключение, если список был изменен после создания итератора.
             *
             * @return true, если текущий элемент не последний.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return index < size;
            }

            /**
             * Метод переводит счетчик на следующий элемент.
             * Если следующего элемента нет, выбрасывает исключение.
             *
             * @return следующий элемент в списке.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[index++];
            }
        };
    }
}