package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;
    private int count = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        for (int i : data) {
            if (i % 2 == 0) {
                count++;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return count > 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return getEvenElements(data);
    }

    public int getEvenElements(int[] elements) {
        while (elements[index] % 2 != 0 && index < data.length - 1) {
            index++;
        }
        count--;
        return elements[index++];
    }
}