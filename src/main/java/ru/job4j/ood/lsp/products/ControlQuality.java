package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.store.Shop;
import ru.job4j.ood.lsp.products.store.Store;
import ru.job4j.ood.lsp.products.store.Trash;
import ru.job4j.ood.lsp.products.store.Warehouse;

import java.util.Calendar;

/**
 * Класс реализует контроль за качеством продуктов.
 * В зависимости от оставшегося срока годности, продукты попадают в соответствующее хранилище.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 13.09.2022
 */
public class ControlQuality {
    public static final long MILLISECONDS_IN_DAY = 86400000;
    public static final int INCLUDE_FIRST_DAY = 1;
    public static final int INCLUDE_FIRST_AND_LAST_DAY = 2;
    Store shop = new Shop();
    Store trash = new Trash();
    Store warehouse = new Warehouse();

    /**
     * Метод высчитывает процент несвежести продукта, от 0 до 100,
     * где 0% - продукт свежий, 100% - не свежий.
     * <p>
     * daysHavePassed - количество прошедших дней с даты изготовления.
     * <p>
     * totalDays - общий срок годности продукта.
     *
     * @param food объект типа Food, для которого вычисляется процент несвежести.
     * @return целочисленное значение процента несвежести продукта.
     */
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

    /**
     * Метод, подсчитывающий конечную стоимость продукта,
     * с учетом скидки.
     *
     * @param food объект, для которого будет установлена скидка.
     */
    public void setDiscount(Food food) {
        food.setPrice((food.getPrice() - food.getDiscount()));
    }

    /**
     * Метод для перемещения объекта в хранилище,
     * в зависимости от подсчитанного процента несвежести.
     *
     * @param food объект, который будет помещен в хранилище.
     */
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
