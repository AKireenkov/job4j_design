package ru.job4j.ood.lsp.products.food;

import java.util.Calendar;

public class Food {
    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private double price;
    private int discount;

    public Food(String name, Calendar expiryDate, Calendar createDate, double price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", expiryDate=" + expiryDate +
                ", createDate=" + createDate +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
