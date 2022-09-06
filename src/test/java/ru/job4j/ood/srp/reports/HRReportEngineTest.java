package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class HRReportEngineTest {
    @Test
    public void whenGeneratedReport() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerIvan = new Employee("Ivan", now, now, 100.0);
        store.add(workerIvan);

        Employee workerAndrey = new Employee("Andrey", now, now, 250.0);
        store.add(workerAndrey);

        Employee workerPetr = new Employee("Petr", now, now, 200.0);
        store.add(workerPetr);

        Report engine = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerAndrey.getName()).append(";")
                .append(workerAndrey.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerPetr.getName()).append(";")
                .append(workerPetr.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerIvan.getName()).append(";")
                .append(workerIvan.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}