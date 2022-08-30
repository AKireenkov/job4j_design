package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        if (row < 0 || column < 0 || date.before(Calendar.getInstance())) {
            throw new IllegalArgumentException();
        }
        return new Ticket3D(account, row, column, date);
    }

    @Override
    public void add(Session session) {
    }
}
