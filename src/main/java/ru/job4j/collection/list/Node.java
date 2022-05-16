package ru.job4j.collection.list;

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
