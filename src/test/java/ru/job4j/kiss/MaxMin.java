package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return findElementWithPredicate(value, comparator, (t) -> t < 0);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return findElementWithPredicate(value, comparator, (t) -> t > 0);
    }

    public <T> T findElementWithPredicate(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        T rsl = value.get(0);
        for (T element : value) {
            int t = comparator.compare(rsl, element);
            if (predicate.test(t)) {
                rsl = element;
            }
        }
        return rsl;
    }
}