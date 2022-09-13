package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    List<Food> shopFoods = new ArrayList<>();

    @Override
    public void add(Food food) {
        shopFoods.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return shopFoods;
    }
}
