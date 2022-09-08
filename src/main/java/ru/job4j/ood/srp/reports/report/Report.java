package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.employee.Employee;

import java.util.function.Predicate;

/**
 * Интерфейс, для реализации различных видов отчетов.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.09.2022
 */
public interface Report {
    /**
     * Метод генерации отчета.
     *
     * @param filter условие отбора сотрудников.
     * @return отчет в формате строки.
     */
    String generate(Predicate<Employee> filter);
}
