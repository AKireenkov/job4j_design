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
