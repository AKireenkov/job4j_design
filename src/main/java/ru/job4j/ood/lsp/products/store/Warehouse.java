package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализует перемещение объекта типа Food на склад.
 * Объект будет перемещен в это хранилище, если процент несвежести продукта, будет менее 25%.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 13.09.2022
 */
public class Warehouse implements Store {

    public static final int PERCENT_25 = 25;
    private List<Food> warehouseFoods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean accept = accept(food);
        if (accept) {
            warehouseFoods.add(food);
        }
        return accept;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(warehouseFoods);
    }

    @Override
    public int getPercentStales(Food food) {
        return Store.super.getPercentStales(food);
    }

    @Override
    public boolean accept(Food food) {
        return getPercentStales(food) < PERCENT_25;
    }
}
