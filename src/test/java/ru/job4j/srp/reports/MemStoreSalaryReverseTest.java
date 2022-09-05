package ru.job4j.srp.reports;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class MemStoreSalaryReverseTest {
    @Test
    public void whenSalarySorted() {
        Store store = new MemStoreSalaryReverse();
        Calendar now = Calendar.getInstance();
        Employee workerIvan = new Employee("Ivan", now, now, 100.0);
        store.add(workerIvan);

        Employee workerAndrey = new Employee("Andrey", now, now, 250.0);
        store.add(workerAndrey);

        Employee workerPetr = new Employee("Petr", now, now, 200.0);
        store.add(workerPetr);

        assertThat(store.findBy(em -> true)).containsExactly(
                new Employee("Andrey", now, now, 250.0),
                new Employee("Petr", now, now, 200.0),
                new Employee("Ivan", now, now, 100.0)
        );
    }
}