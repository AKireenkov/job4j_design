package ru.job4j.ood.srp.reports.store;

import ru.job4j.ood.srp.reports.employee.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Хранилище для объектов класса Employee.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.09.2022
 */
public class MemStore implements Store {
    private final List<Employee> employees = new ArrayList<>();

    /**
     * Метод добавления объектов в список.
     *
     * @param em объект типа Employee.
     */
    @Override
    public void add(Employee em) {
        employees.add(em);
    }

    /**
     * Метод поиска элементов в списке объектов Employee,
     * по условию переданному в параметре.
     *
     * @param filter условие отбора сотрудника из общего списка.
     * @return Список объектов типа Employee, отфильтрованный по условию.
     */
    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}
