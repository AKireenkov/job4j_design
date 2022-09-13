package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.store.Shop;
import ru.job4j.ood.lsp.products.store.Store;
import ru.job4j.ood.lsp.products.store.Trash;
import ru.job4j.ood.lsp.products.store.Warehouse;

import java.util.Calendar;

public class ControlQuality {
    public static final long MILLISECONDS_IN_DAY = 86400000;
    public static final int INCLUDE_FIRST_DAY = 1;
    public static final int INCLUDE_FIRST_AND_LAST_DAY = 2;
    Store shop = new Shop();
    Store trash = new Trash();
    Store warehouse = new Warehouse();

    public int percent(Food food) {
        double daysHavePassed = Math.abs((Calendar.getInstance().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis())
                / MILLISECONDS_IN_DAY) + INCLUDE_FIRST_DAY;
        double totalDays = Math.abs((food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis())
                / MILLISECONDS_IN_DAY) + INCLUDE_FIRST_AND_LAST_DAY;
        double percent = (daysHavePassed / totalDays) * 100;
        return (int) percent;
    }

    public void setDiscount(Food food) {
        food.setPrice((food.getPrice() - food.getDiscount()));
    }

    public void movingTheProduct(Food food) {
        int percent = percent(food);
        if (percent < 25) {
            warehouse.add(food);
        } else if (percent > 25 && percent < 75) {
            shop.add(food);
        } else if (percent > 75 && percent < 100) {
            setDiscount(food);
            shop.add(food);
        } else {
            trash.add(food);
        }
    }
}
