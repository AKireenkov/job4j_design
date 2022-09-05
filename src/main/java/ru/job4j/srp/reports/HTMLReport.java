package ru.job4j.srp.reports;

import java.util.function.Predicate;

public class HTMLReport implements Report {

    private Store store;

    public HTMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<table class=\"employeeTable\">")
                .append(System.lineSeparator())
                .append("<thead>")
                .append(System.lineSeparator())
                .append("<tr>")
                .append(System.lineSeparator())
                .append("<td>Name</td>")
                .append(System.lineSeparator())
                .append("<td>Hired</td>")
                .append(System.lineSeparator())
                .append("<td>Fired</td>")
                .append(System.lineSeparator())
                .append("<td>Salary</td>")
                .append(System.lineSeparator())
                .append("</tr>")
                .append(System.lineSeparator())
                .append("</thead>")
                .append(System.lineSeparator())
                .append("<tbody>")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>")
                    .append(System.lineSeparator())
                    .append("<td>").append(employee.getName()).append("/td>")
                    .append(System.lineSeparator())
                    .append("<td>").append(employee.getHired()).append("/td>")
                    .append(System.lineSeparator())
                    .append("<td>").append(employee.getFired()).append("/td>")
                    .append(System.lineSeparator())
                    .append("<td>").append(employee.getName()).append("/td>")
                    .append(System.lineSeparator())
                    .append("</tr>")
                    .append(System.lineSeparator());
        }
        text.append("</tbody>")
                .append(System.lineSeparator())
                .append("</table>");
        return text.toString();
    }
}
