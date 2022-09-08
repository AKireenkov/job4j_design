package ru.job4j.ood.srp.reports.helpers;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static ru.job4j.ood.srp.reports.helpers.Constants.DATE_FORMAT;

public class XmlDateAdapter extends XmlAdapter<String, Calendar> implements CalendarFormatter {

    @Override
    public Calendar unmarshal(String v) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(stringToCalendarParse(v));
        return calendar;
    }

    @Override
    public String marshal(Calendar v) throws Exception {
        return DATE_FORMAT.format(v.getTime());
    }

    @Override
    public Date stringToCalendarParse(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm");
        LocalDateTime dateTimeFired = LocalDateTime.parse(date, formatter);
        Instant instant = dateTimeFired.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}
