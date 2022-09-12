package ru.job4j.ood.srp.reports.helpers;

import com.google.gson.*;
import ru.job4j.ood.srp.reports.employee.Employee;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CustomJsonConverter implements JsonSerializer<Employee>, JsonDeserializer<Employee> {
    private JsonObject object = new JsonObject();

    private static final ThreadLocal<DateFormat> DATE_FORMAT
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss X"));

    @Override
    public Employee deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        object = jsonElement.getAsJsonObject();

        Calendar hired = Calendar.getInstance();
        Calendar fired = Calendar.getInstance();

        String name = object.get("name").getAsString();

        try {
            hired.setTime(DATE_FORMAT.get().parse(object.get("hired").getAsString()));
            fired.setTime(DATE_FORMAT.get().parse(object.get("fired").getAsString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        double salary = object.get("salary").getAsDouble();

        return new Employee(name, hired, fired, salary);
    }

    @Override
    public JsonObject serialize(Employee employee, Type type, JsonSerializationContext jsonSerializationContext) {
        object.addProperty("name", employee.getName());
        object.addProperty("hired", DATE_FORMAT.get().format(employee.getHired().getTime()));
        object.addProperty("fired", DATE_FORMAT.get().format(employee.getFired().getTime()));
        object.addProperty("salary", employee.getSalary());
        return object;
    }
}
