package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index = 0;
    private int count = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        count = 0;
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                count++;
            }
        }
        return count > 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return getEvenElements();
    }

    public int getEvenElements() {
        while (data[index] % 2 != 0 && index < data.length - 1) {
            index++;
        }
        count--;
        return data[index++];
    }
}