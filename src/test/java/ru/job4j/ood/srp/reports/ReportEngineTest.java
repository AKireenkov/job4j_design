package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.report.Report;
import ru.job4j.ood.srp.reports.report.ReportEngine;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.ood.srp.reports.helpers.Constants.DATE_FORMAT;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        System.out.println(expect);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}