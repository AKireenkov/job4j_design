package ru.job4j.srp.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStoreSalaryNet implements Store {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public void add(Employee em) {
        em.setSalary(em.getSalary() - em.getSalary() * 0.13);
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }
}
