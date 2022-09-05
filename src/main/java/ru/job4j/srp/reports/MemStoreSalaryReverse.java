package ru.job4j.srp.reports;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStoreSalaryReverse implements Store {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee em) {
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter)
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
    }
}
