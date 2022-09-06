package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class HTMLReportTest {
    @Test
    public void whenHTMLGenerate() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new HTMLReport(store);

        StringBuilder expect = new StringBuilder()
                .append("<table class=\"employeeTable\">").append(System.lineSeparator())
                .append("<thead>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<td>Name</td>").append(System.lineSeparator())
                .append("<td>Hired</td>").append(System.lineSeparator())
                .append("<td>Fired</td>").append(System.lineSeparator())
                .append("<td>Salary</td>").append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("</thead>").append(System.lineSeparator())
                .append("<tbody>").append(System.lineSeparator())
                .append("<tr>").append(System.lineSeparator())
                .append("<td>").append(worker.getName()).append("/td>").append(System.lineSeparator())
                .append("<td>").append(worker.getHired()).append("/td>").append(System.lineSeparator())
                .append("<td>").append(worker.getFired()).append("/td>").append(System.lineSeparator())
                .append("<td>").append(worker.getName()).append("/td>").append(System.lineSeparator())
                .append("</tr>").append(System.lineSeparator())
                .append("</tbody>").append(System.lineSeparator())
                .append("</table>");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}