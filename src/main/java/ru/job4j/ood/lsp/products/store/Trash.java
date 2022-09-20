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
    public static final int PERCENT_100 = 100;
    private List<Food> trashFoods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean accept = accept(food);
        if (accept) {
            trashFoods.add(food);
        }
        return accept;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(trashFoods);
    }

    @Override
    public int getPercentStales(Food food) {
        return Store.super.getPercentStales(food);
    }

    @Override
    public boolean accept(Food food) {
        return getPercentStales(food) >= PERCENT_100;
    }
}
