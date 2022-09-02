package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация односвязного списка.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 16.05.2022
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    /**
     * Метод добавления объекта в список.
     * Если, список пустой, элемент становится первым, где ссылка на следующий = null.
     * Если список не пустой, в цикле while() проходим по всем элементам, начиная с первого,
     * пока ссылка на следующий не будет null.
     * После получения ссылки на последний элемент, присваиваем ей значение объекта, который нужно добавить в список.
     *
     * @param value объект, который нужно добавить в список.
     */
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * Метод добавления первого объекта в список.
     *
     * @param value первый объект в списке.
     */
    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    /**
     * Метод удаления первого объекта в списке.
     * При попытке удалить элемент в пустом списке - выбрасывается исключение.
     * Сохраняем ссылку на отцепляемый элемент Node<T> temp.
     * По этой ссылке, обнуляем значение объекта и ссылку на следующий узел.
     * Следующий элемент в списке, сдвигаем на первое место.
     *
     * @return значение удаляемого элемента.
     */
    public T deleteFirst() {
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        T oldValue = head.value;
        Node<T> temp = head;
        head = head.next;
        temp.next = null;
        temp.value = null;
        return oldValue;
    }

    /**
     * Метод переворачивает список. Каждому элементу, устанавливается ссылка на следующий объект в качестве предыдущего.
     * Первому элемент, устанавливаем ссылку на следуещий = Null, head присваивается текущий элемент, а текущему присваивается значение следующего.
     * Второму элементу, ссылку на следущий = первый элемент в списке,
     * третьему элемент, ссылку на следующий = второй элемент в списке и т.д.
     *
     * @return true, если список возможно перевернуть.
     */
    public boolean revert() {
        boolean isReverse = head != null && head.next != null;
        if (isReverse) {
            Node<T> current = head.next;
            head.next = null;
            while (current != null) {
                Node<T> next = current.next;
                current.next = head;
                head = current;
                current = next;
            }
        }
        return isReverse;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * Модель объекта - узел. Хранит ссылку на значение элемента и ссылку на следующий элемент.
     *
     * @param <T> тип создаваемого объекта
     */
    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}