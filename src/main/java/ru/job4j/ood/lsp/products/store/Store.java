package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.List;

public interface Store {
    void add(Food food);

    List<Food> getFoodList();
}
