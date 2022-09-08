package ru.job4j.ood.srp.reports.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.job4j.ood.srp.reports.helpers.CustomJsonConverter;
import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.store.MemStore;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements Report {
    private Store store;

    public JsonReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return new GsonBuilder()
                .registerTypeAdapter(Employee.class, new CustomJsonConverter())
                .create()
                .toJson(store.findBy(filter));
    }

    public static void main(String[] args) {
        Store store = new MemStore();
        Date now = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);

        Employee workerIvan = new Employee("Ivan", calendar, calendar, 100.0);
        store.add(workerIvan);
        String report = new JsonReport(store).generate(em -> true);
        System.out.println(report);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Employee.class, new CustomJsonConverter())
                .create();
        List<Employee> employees = gson.fromJson(report, new TypeToken<List<Employee>>() {
        }.getType());
        employees.forEach(System.out::println);
    }
}
