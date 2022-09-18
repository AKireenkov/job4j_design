package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.store.Store;

import java.util.List;

/**
 * Класс реализует контроль за качеством продуктов.
 * В зависимости от оставшегося срока годности, продукты попадают в соответствующее хранилище.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 13.09.2022
 */
public class ControlQuality {
    List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * Метод для перемещения объекта в хранилище,
     * проходит по всему списку хранилищ,
     * пытается добавить в каждое хранилище, переданный продукт.
     *
     * @param food объект, который будет помещен в хранилище.
     */
    public void movingTheProduct(Food food) {
        stores.forEach(store -> store.add(food));
    }
}
