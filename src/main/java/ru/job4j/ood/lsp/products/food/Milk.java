package ru.job4j.ood.lsp.products.food;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Milk extends Food {
    public Milk(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }

    public static void main(String[] args) {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.JANUARY, 1);

        Calendar createDate = Calendar.getInstance();
        expiryDate.set(2021, Calendar.JANUARY, 30);

        Milk milk = new Milk("test",
                expiryDate,
                createDate, 200.22, 100);

        System.out.println(expiryDate.getTime());
        System.out.println(milk);
    }


}
