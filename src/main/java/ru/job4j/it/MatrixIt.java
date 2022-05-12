package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[row].length == 0) {
            if (row < data.length - 1) {
                row++;
            } else {
                return false;
            }
        }
        return column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column == data[row].length - 1) {
            return data[row++][column];
        }
        return data[row][column++];
    }
}