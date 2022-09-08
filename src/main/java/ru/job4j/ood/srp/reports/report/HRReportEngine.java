package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Реализация отчета для HR.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.09.2022
 */
public class HRReportEngine implements Report {

    private Store store;

    public HRReportEngine(Store store) {
        this.store = store;
    }

    /**
     * Метод генерации отчета.
     * Формирует отчет в формате CSV.
     * В отчет попадает список работников, удовлетворяющих условию предиката.
     * По каждому сотруднику, отображаются только name и salary.
     * Список сотрудников в отчете, отсортирован по полю salary в обратном порядке.
     *
     * @param filter условие отбора сотрудника из общего списка.
     * @return отчет, аналогичный CSV, в формате строки.
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> employees = store.findBy(filter)
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary)
                        .reversed()).toList();
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}