package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.function.Predicate;

import static ru.job4j.ood.srp.reports.helpers.Constants.*;

/**
 * Реализация отчета для инженеров.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.09.2022
 */
public class ReportEngine implements Report {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    /**
     * Метод генерации отчета.
     * Формирует отчет в формате CSV.
     * В отчет попадает список работников, удовлетворяющих условию предиката.
     *
     * @param filter условие отбора сотрудника из общего списка.
     * @return отчет, аналогичный CSV, в формате строки.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}