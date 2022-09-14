package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализует перемещение объекта типа Food в магазин.
 * Если процент несвежести, будет в пределах от 25 до 75 процентов, объект будет перемещен по обычной цене.
 * Если процент несвежести, будет в пределах от 75 до 100 процентов, объект будет перемещен по цене, с учетом скидки.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 13.09.2022
 */
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
