package ru.job4j.collection.list;

/**
 * Нода, для реализации двусвязного списка,
 * где хранятся ссылки на предыдущий/следующий элементы и ссылка на сам элемент.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 16.05.2022
 */
public class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.prev = prev;
        this.item = element;
        this.next = next;
    }
}
