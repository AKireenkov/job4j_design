package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс является примером нарушения принципа DIP.
 * 1. В данном случае, сам класс Order - не зависит от интерфейса,
 * т.е., мы не сможем добавить ещё одну реализацию заказов, если это потребуется.
 * 2. Список orders - хранит все данные о заказе - тут следует выделить интерфейс-хранилище.
 * 3. Классы BMW и Customer, аналогично классу Order, имеют только одну реализацию,
 * при необходимости добавить другой автомобиль, мы столкнемся с проблемой.
 * 4. Сам класс Order зависит от классов более низкого уровня - BMW/Customer.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 08.10.2022
 */
public class Order {
    private BMW car;
    private Customer customer;
    private List<Order> orders = new ArrayList<>();

    public Order(BMW car, Customer customer) {
        this.car = car;
        this.customer = customer;
    }

    class BMW {
        private int price;
        private String model;
    }

    class Customer {
        private String name;
        private int age;
    }
}
