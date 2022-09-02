package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Реализация двусвязного списка
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 16.05.2022
 */
public class SimpleLinkedList<E> implements List<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    /**
     * Метод добавления элемента в список. Первый элемент, будет содержать ссылки на сам элемент и на послежний элемент.
     * Второй элемент, имеет ссылку на сам элемент и на первый элемент.
     * Каждый последующий - содержит ссылку на предыдущий и на сам элемент.
     * После добавления элемента, увеличиваем счетчики size - размер списка
     * и modCount - количество модификаций списка
     *
     * @param value значение, которое вставляем в список
     */
    @Override
    public void add(E value) {
        if (first == null) {
            first = new Node<>(null, value, last);
        } else if (last == null) {
            first.next = new Node<>(first, value, null);
        } else {
            last.next = new Node<>(last, value, null);
        }
        size++;
        modCount++;
    }

    /**
     * Метод получения элемента из списка.
     * Метод checkIndex() проверяет, что индекс по которому мы ищем элемент, есть в списке.
     * Затем, перебираем все элементы, и переходим по ссылке на следующий, пока count не станет равен index.
     *
     * @param index номер элемента, который нужно найти в списке.
     * @return значение найденного элемента.
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> temp = first;
        int count = 0;
        while (count != index) {
            temp = temp.next;
            count++;
        }
        return temp.item;
    }

    /**
     * Отдельно реализуем Iterator для такого списка в стиле fail-fast.
     *
     * @return либо boolean значение, либо элемент списка.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            Node<E> node = first;

            /**
             * Метод проверяет наличие следующего элемента в списке.
             * Выбрасывает исключение, если с момента создания итератора коллекция подверглась структурному изменению.
             *
             * @return true, если в списке есть следующий элемент.
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            /**
             * Метод переводит счетчик на следующий элемент.
             * Если следующего элемента нет, будет выброшего исключение.
             *
             * @return элемент, на который было переключение счетчика.
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = node.item;
                node = node.next;
                return rsl;
            }
        };
    }
}