package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) (count / capacity) == LOAD_FACTOR) {
            expand();
        }
        int hash = hash(key);
        int index = indexFor(hash);
        modCount++;
        boolean cellFree = get(key) == null;
        if (cellFree) {
            table[index] = new MapEntry<K, V>(key, value);
            count++;
        }
        return cellFree;
    }

    private int hash(Object key) {
        int h;
        if (key == null) {
            h = 0;
        } else {
            h = key.hashCode();
            h = h ^ (h >>> 16);
        }
        return h;
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        capacity *= 2;
        table = Arrays.copyOf(table, capacity);
    }

    @Override
    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash);
        return table[index] == null ? null : table[index].value;
    }

    @Override
    public boolean remove(K key) {
        boolean removed = this.get(key) != null;
        int hash = hash(key);
        int index = indexFor(hash);
        if (removed) {
            table[index] = null;
        }
        modCount++;
        return removed;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index = 0;
            MapEntry<K, V> currentElement = null;

            @Override
            public boolean hasNext() {
                boolean hasNext = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (index < table.length) {
                    index++;
                    while (currentElement == null && index < table.length) {
                        currentElement = table[index++];
                    }
                    index--;
                    hasNext = currentElement != null;
                }
                return hasNext;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
