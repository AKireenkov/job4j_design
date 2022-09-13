package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    List<Food> warehouseFoods = new ArrayList<>();

    @Override
    public void add(Food food) {
        warehouseFoods.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return warehouseFoods;
    }
}
