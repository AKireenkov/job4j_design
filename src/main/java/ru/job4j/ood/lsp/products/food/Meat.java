package ru.job4j.ood.lsp.products.food;

import java.util.Calendar;

public class Meat extends Food {
    public Meat(String name, Calendar expiryDate, Calendar createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
