package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.report.AccountingReport;
import ru.job4j.ood.srp.reports.report.Report;
import ru.job4j.ood.srp.reports.store.MemStore;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.ood.srp.reports.helpers.Constants.DATE_FORMAT;
import static ru.job4j.ood.srp.reports.helpers.Constants.RATE;

public class AccountingReportTest {
    @Test
    public void whenSalarySorted() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee workerIvan = new Employee("Ivan", now, now, 100.0);
        store.add(workerIvan);
        Report engine = new AccountingReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(workerIvan.getName()).append(";")
                .append(DATE_FORMAT.format(workerIvan.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(workerIvan.getFired().getTime())).append(";")
                .append(workerIvan.getSalary() * RATE).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}