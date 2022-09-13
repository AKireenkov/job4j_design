package ru.job4j.ood.lsp.products.food;

import ru.job4j.ood.lsp.products.ControlQuality;

import java.util.Calendar;

public class Milk extends Food {
    public Milk(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }

    public static void main(String[] args) {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.SEPTEMBER, 17);

        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 8);

        Milk milk = new Milk("test",
                expiryDate,
                createDate, 200.22, 100.00);
        ControlQuality controlQuality = new ControlQuality();
        System.out.println(controlQuality.percent(milk));
    }
}
