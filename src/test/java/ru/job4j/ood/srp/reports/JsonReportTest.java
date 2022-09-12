package ru.job4j.ood.srp.reports;

import org.junit.Test;
import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.report.JsonReport;
import ru.job4j.ood.srp.reports.report.Report;
import ru.job4j.ood.srp.reports.store.MemStore;
import ru.job4j.ood.srp.reports.store.Store;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonReportTest {
    private static final ThreadLocal<DateFormat> DATE_FORMAT
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X"));

    @Test
    public void whenObjectToJson() {
        Store store = new MemStore();
        Calendar calendar = Calendar.getInstance();
        Employee worker = new Employee("Ivan", calendar, calendar, 100.0);
        store.add(worker);

        Report json = new JsonReport(store);
        String expect = "[{\"name\":\"%s\",\"hired\":\"%s\",\"fired\":\"%s\",\"salary\":%s}]"
                .formatted(worker.getName(),
                        DATE_FORMAT.get().format(worker.getHired().getTime()),
                        DATE_FORMAT.get().format(worker.getFired().getTime()),
                        worker.getSalary());
        assertThat(json.generate(em -> true)).isEqualTo(expect);
    }
}