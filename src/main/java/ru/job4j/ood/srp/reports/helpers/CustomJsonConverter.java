package ru.job4j.ood.srp.reports.helpers;

import com.google.gson.*;
import ru.job4j.ood.srp.reports.employee.Employee;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static ru.job4j.ood.srp.reports.helpers.Constants.DATE_FORMAT;

public class CustomJsonConverter implements JsonSerializer<Employee>, JsonDeserializer<Employee>, CalendarFormatter {
    private JsonObject object = new JsonObject();

    @Override
    public Employee deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        object = jsonElement.getAsJsonObject();

        Calendar hired = Calendar.getInstance();
        Calendar fired = Calendar.getInstance();

        String name = object.get("name").getAsString();
        hired.setTime(stringToCalendarParse("hired"));
        fired.setTime(stringToCalendarParse("fired"));
        double salary = object.get("salary").getAsDouble();

        return new Employee(name, hired, fired, salary);
    }

    @Override
    public JsonObject serialize(Employee employee, Type type, JsonSerializationContext jsonSerializationContext) {
        object.addProperty("name", employee.getName());
        object.addProperty("hired", DATE_FORMAT.format(employee.getHired().getTime()));
        object.addProperty("fired", DATE_FORMAT.format(employee.getFired().getTime()));
        object.addProperty("salary", employee.getSalary());
        return object;
    }

    @Override
    public Date stringToCalendarParse(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm");
        LocalDateTime dateTimeFired = LocalDateTime.parse(object.get(date).getAsString(), formatter);
        Instant instant = dateTimeFired.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
