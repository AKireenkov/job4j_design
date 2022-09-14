package ru.job4j.ood.lsp.products.store;

import ru.job4j.ood.lsp.products.food.Food;

import java.util.List;

/**
 * Интерфейс, для реализации различных типов хранилищ.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 13.09.2022
 */
public interface Store {
    /**
     * Метод добавления объекта в соответствующее хранилище.
     *
     * @param food объект типа Food, для перемещения в хранилище.
     */
    void add(Food food);

    /**
     * Метод для получения списка объектов типа Food.
     *
     * @return список объектов, из соответствующего хранилища.
     */
    List<Food> getFoodList();
}
