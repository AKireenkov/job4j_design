package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Milk;
import ru.job4j.ood.lsp.products.store.Shop;
import ru.job4j.ood.lsp.products.store.Store;
import ru.job4j.ood.lsp.products.store.Trash;
import ru.job4j.ood.lsp.products.store.Warehouse;

import java.util.Calendar;

public class ControlQuality {
    Store shop = new Shop();
    Store trash = new Trash();
    Store warehouse = new Warehouse();

    public int percent(Food food) {
        int daysHavePassed = (int) Math.abs((Calendar.getInstance().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis())
                / 86400000) + 1;
        int totalDays = (int) Math.abs((food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis())
                / 86400000) + 2;
        int i = (daysHavePassed % totalDays) * 10;
        return i;
    }

    public void setDiscount(Food food) {
        food.setDiscount(food.getPrice() - food.getDiscount());
    }

    public void movingTheProduct(Food food) {
        int percent = percent(food);

        if (percent < 25) {
            warehouse.add(food);
        } else if (percent > 25 && percent < 75) {
            shop.add(food);
            System.out.println(shop.getFoodList());
        } else if (percent > 75 && percent < 100) {
            setDiscount(food);
            shop.add(food);
        } else {
            trash.add(food);
        }
    }

    public static void main(String[] args) {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.SEPTEMBER, 17);

        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 8);

        Milk milk = new Milk("test",
                expiryDate,
                createDate, 200.22, 100.00);


        ControlQuality controlQuality = new ControlQuality();
        controlQuality.movingTheProduct(milk);
    }
}
