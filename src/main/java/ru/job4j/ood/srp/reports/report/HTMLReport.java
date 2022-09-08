package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.function.Predicate;

/**
 * Реализация отчета HTML.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.09.2022
 */
public class HTMLReport implements Report {

    private Store store;

    public HTMLReport(Store store) {
        this.store = store;
    }

    /**
     * Метод генерации отчета.
     * Формирует отчет в формате HTML.
     * В отчет попадает список работников, удовлетворяющих условию предиката.
     *
     * @param filter условие отбора сотрудника из общего списка.
     * @return отчет, аналогичный HTML, в формате строки.
     */
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
