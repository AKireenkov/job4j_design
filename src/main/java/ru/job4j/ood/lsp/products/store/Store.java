package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.Calendar;
import java.util.List;

/**
 * Интерфейс, для реализации различных типов хранилищ.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 13.09.2022
 */
public interface Store {
    long MILLISECONDS_IN_DAY = 86400000;
    int INCLUDE_FIRST_DAY = 1;
    int INCLUDE_FIRST_AND_LAST_DAY = 2;

    /**
     * Метод добавления объекта в соответствующее хранилище.
     *
     * @param food объект типа Food, для перемещения в хранилище.
     */
    boolean add(Food food);

    /**
     * Метод для получения списка объектов типа Food.
     *
     * @return список объектов, из соответствующего хранилища.
     */
    List<Food> getFoodList();

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
    default int getPercentStales(Food food) {
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
     * Метод проверяет, может ли хранилище принять продукт.
     *
     * @param food объект, для которого выполняется проверка.
     * @return true/false если объект может/не может быть помещен в хранилище.
     */
    boolean accept(Food food);

    /**
     * Метод очищает список продуктов в хранилище.
     */
    void clean();
}
