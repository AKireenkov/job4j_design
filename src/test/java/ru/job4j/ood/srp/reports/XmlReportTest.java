package ru.job4j.ood.srp.reports;

import org.junit.Test;
import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.report.Report;
import ru.job4j.ood.srp.reports.report.XmlReport;
import ru.job4j.ood.srp.reports.store.MemStore;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.job4j.ood.srp.reports.helpers.Constants.DATE_FORMAT;

public class XmlReportTest {
    @Test
    public void whenObjectToXml() {
        Store store = new MemStore();
        Calendar calendar = Calendar.getInstance();
        Employee worker = new Employee("Ivan", calendar, calendar, 100.0);
        store.add(worker);

        Report xml = new XmlReport(store);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>%s</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>%s</salary>
                    </employee>
                </employees>
                """
                .formatted(worker.getName(),
                        DATE_FORMAT.format(worker.getHired().getTime()),
                        DATE_FORMAT.format(worker.getFired().getTime()),
                        worker.getSalary());
        assertThat(xml.generate(em -> true)).isEqualTo(expect);
    }
}