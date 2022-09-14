package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализует перемещение объекта типа Food в мусор.
 * Объект будет перемещен в это хранилище, если процент несвежести продукта, будет более 100%.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 13.09.2022
 */
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
