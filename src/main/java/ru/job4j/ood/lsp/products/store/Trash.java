package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    List<Food> trashFoods = new ArrayList<>();

    @Override
    public void add(Food food) {
        trashFoods.add(food);
    }

    @Override
    public List<Food> getFoodList() {
        return trashFoods;
    }
}
