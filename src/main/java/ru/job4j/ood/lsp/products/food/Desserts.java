package ru.job4j.ood.lsp.products.food;

import java.util.Calendar;

public class Desserts extends Food {
    public Desserts(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
