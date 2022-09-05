package ru.job4j.srp.reports;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class HRReportEngineTest {
    @Test
    public void whenGeneratedReport() {
        Store store = new MemStoreSalaryNet();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100.0);
        store.add(worker);

        Employee exp = new Employee("Ivan", now, now, 87.0);

        Report engine = new HRReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(exp.getName()).append(";")
                .append(exp.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}