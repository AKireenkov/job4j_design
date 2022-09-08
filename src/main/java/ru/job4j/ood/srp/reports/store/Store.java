package ru.job4j.ood.srp.reports.store;

import ru.job4j.ood.srp.reports.employee.Employee;

import java.util.List;
import java.util.function.Predicate;

/**
 * Интерфейс для реализации хранилища.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.09.2022
 */
public interface Store {
    /**
     * Метод добавления элемента.
     *
     * @param em объект типа Employee, который необходимо добавить.
     */
    void add(Employee em);

    /**
     * Метод поиска элементов в списке по заданному условию.
     *
     * @param filter условие отбора сотрудника из общего списка.
     * @return список, отфильтрованный по заданному условию.
     */
    List<Employee> findBy(Predicate<Employee> filter);
}
