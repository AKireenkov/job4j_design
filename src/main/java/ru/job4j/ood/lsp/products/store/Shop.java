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
    public static final int PERCENT_25 = 25;
    public static final int PERCENT_75 = 75;
    public static final int PERCENT_100 = 100;
    private List<Food> shopFoods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean accept = accept(food);
        int percent = getPercentStales(food);
        if (accept) {
            if (percent > PERCENT_75 && percent < PERCENT_100) {
                setDiscount(food);
            }
            shopFoods.add(food);
        }
        return accept;
    }

    @Override
    public List<Food> getFoodList() {
        return List.copyOf(shopFoods);
    }

    @Override
    public int getPercentStales(Food food) {
        return Store.super.getPercentStales(food);
    }

    @Override
    public boolean accept(Food food) {
        return getPercentStales(food) > PERCENT_25 && getPercentStales(food) < PERCENT_100;
    }

    /**
     * Метод, подсчитывающий конечную стоимость продукта,
     * с учетом скидки.
     *
     * @param food объект, для которого будет установлена скидка.
     */
    public void setDiscount(Food food) {
        food.setPrice((food.getPrice() - food.getDiscount()));
    }

}
