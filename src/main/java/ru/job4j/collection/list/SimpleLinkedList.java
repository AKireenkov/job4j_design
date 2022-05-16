package ru.job4j.collection.list;

import java.util.*;

public class SimpleLinkedList<E> implements List<E> {

    private E[] container;
    private Node<E> node;
    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    public SimpleLinkedList() {
        this.container = (E[]) new Object[0];
    }

    @Override
    public void add(E value) {
        if (size == container.length) {
            arrayExtension();
        }
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        container[size++] = newNode.item;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return container[index++];
            }
        };
    }

    public void arrayExtension() {
        int newSize = size == 0 ? 2 : size * 2;
        container = Arrays.copyOf(container, newSize);
    }

}