package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private int size;
    private SimpleArrayList<T> set = new SimpleArrayList<>(size);

    @Override
    public boolean add(T value) {
        boolean mustAdd = !contains(value);
        if (mustAdd) {
            set.add(value);
        }
        size++;
        return mustAdd;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        for (T el : this) {
            if (Objects.equals(el, value)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleSet<?> simpleSet = (SimpleSet<?>) o;
        return Objects.equals(set, simpleSet.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }
}
