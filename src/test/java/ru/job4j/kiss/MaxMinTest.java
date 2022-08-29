package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MaxMinTest {
    @Test
    public void whenStringMin() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(List.of("Andrey", "Ivan", "Petr"), Comparator.naturalOrder()))
                .isEqualTo("Andrey");
    }

    @Test
    public void whenStringMax() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(List.of("Oleg", "Michael", "Semen"), Comparator.naturalOrder()))
                .isEqualTo("Semen");
    }

    @Test
    public void whenIntMin() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.min(List.of(1, 3, 9), Comparator.naturalOrder()))
                .isEqualTo(1);
    }

    @Test
    public void whenIntMax() {
        MaxMin maxMin = new MaxMin();
        assertThat(maxMin.max(List.of(1, 2, 11, 15, 4), Comparator.naturalOrder()))
                .isEqualTo(15);
    }
}