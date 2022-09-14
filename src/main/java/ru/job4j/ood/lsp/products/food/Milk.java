package ru.job4j.ood.lsp.products.food;

import java.util.Calendar;

/**
 * Предназначен для создания вида продуктов, типа Milk.
 * Наследует методы класа Food.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 13.09.2022
 */
public class Milk extends Food {
    public Milk(String name, Calendar expiryDate, Calendar createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
