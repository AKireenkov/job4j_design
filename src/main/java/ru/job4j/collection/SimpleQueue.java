package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    int lengthIn;
    int lengthOut;

    public T poll() {
        if (lengthOut == 0 && lengthIn == 0) {
            throw new NoSuchElementException();
        }

        if (lengthOut == 0) {
            while (lengthIn != 0) {
                out.push(in.pop());
                lengthIn--;
                lengthOut++;
            }
        }
        lengthOut--;
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
        lengthIn++;
    }
}