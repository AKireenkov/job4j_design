package ru.job4j.ood.lsp.products;

import org.junit.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;
import ru.job4j.ood.lsp.products.food.Milk;
import ru.job4j.ood.lsp.products.store.Shop;
import ru.job4j.ood.lsp.products.store.Store;
import ru.job4j.ood.lsp.products.store.Trash;
import ru.job4j.ood.lsp.products.store.Warehouse;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ControlQualityTest {
    @Test
    public void whenProductToWarehouse() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(
                expiryDate.get(Calendar.YEAR) + 2,
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 10
        );
        Calendar createDate = Calendar.getInstance();
        createDate.set(
                createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 10
        );
        Food milk = new Milk("Domik v derevne",
                expiryDate,
                createDate, 99, 50);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.movingTheProduct(milk);
        assertThat(warehouse.getFoodList()).contains(milk);
    }

    @Test
    public void whenProductToShop() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(
                expiryDate.get(Calendar.YEAR) + 1,
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 10, 0, 11
        );
        Calendar createDate = Calendar.getInstance();
        createDate.set(
                createDate.get(Calendar.YEAR) - 1,
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 20, 1, 45
        );

        Food milk = new Milk("Prostokvashino",
                expiryDate,
                createDate, 69, 11);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.movingTheProduct(milk);
        assertThat(shop.getFoodList()).contains(milk);
    }

    @Test
    public void whenProductToShopWithDiscount() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(
                expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 21
        );
        Calendar createDate = Calendar.getInstance();
        createDate.set(
                createDate.get(Calendar.YEAR) - 1,
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 15
        );

        Food meat = new Meat("pork",
                expiryDate,
                createDate, 500, 300);
        Food expected = new Meat("pork",
                expiryDate,
                createDate, 200, 300);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.movingTheProduct(meat);
        assertThat(shop.getFoodList()).contains(expected);
    }

    @Test
    public void whenProductToTrash() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(
                expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) - 1, 1, 1
        );
        Calendar createDate = Calendar.getInstance();
        createDate.set(
                createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 10, 12, 13
        );

        Food meat = new Meat("chicken",
                expiryDate,
                createDate, 200, 100);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.movingTheProduct(meat);
        assertThat(trash.getFoodList()).contains(meat);
    }

    @Test
    public void whenProductsToAllStore() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));

        Calendar expiryDate = Calendar.getInstance();
        Calendar createDate = Calendar.getInstance();

        expiryDate.set(
                expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 14
        );
        createDate.set(
                createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 7
        );
        Food beef = new Meat("beef",
                expiryDate,
                createDate, 200, 100);
        controlQuality.movingTheProduct(beef);

        expiryDate.set(
                expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 4
        );
        createDate.set(
                createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 33
        );
        Food yogurt = new Milk("yogurt",
                expiryDate,
                createDate, 235, 76);
        controlQuality.movingTheProduct(yogurt);

        expiryDate.set(
                expiryDate.get(Calendar.YEAR),
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) - 21
        );
        createDate.set(
                createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH) - 99
        );
        Food deer = new Meat("deer",
                expiryDate,
                createDate, 875, 11);
        controlQuality.movingTheProduct(deer);

        expiryDate.set(
                expiryDate.get(Calendar.YEAR) + 3,
                expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 21
        );
        createDate.set(
                createDate.get(Calendar.YEAR),
                createDate.get(Calendar.MONTH),
                createDate.get(Calendar.DAY_OF_MONTH), +10, 11, 12
        );
        Food cheese = new Milk("cheese",
                expiryDate,
                createDate, 151, 1);
        controlQuality.movingTheProduct(cheese);
        assertThat(shop.getFoodList()).contains(beef, yogurt);
        assertThat(trash.getFoodList()).contains(deer);
        assertThat(warehouse.getFoodList()).contains(cheese);
    }

    @Test
    public void whenProductsToAllStoreAndResort() {
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));

        Calendar expiryDateBeef = Calendar.getInstance();
        Calendar createDateBeef = Calendar.getInstance();
        expiryDateBeef.set(
                expiryDateBeef.get(Calendar.YEAR),
                expiryDateBeef.get(Calendar.MONTH),
                expiryDateBeef.get(Calendar.DAY_OF_MONTH) + 14
        );
        createDateBeef.set(
                createDateBeef.get(Calendar.YEAR),
                createDateBeef.get(Calendar.MONTH),
                createDateBeef.get(Calendar.DAY_OF_MONTH) - 7
        );
        Food beef = new Meat("beef",
                expiryDateBeef,
                createDateBeef, 200, 100);
        controlQuality.movingTheProduct(beef);

        Calendar expiryDateYogurt = Calendar.getInstance();
        Calendar createDateYogurt = Calendar.getInstance();
        expiryDateYogurt.set(
                expiryDateYogurt.get(Calendar.YEAR),
                expiryDateYogurt.get(Calendar.MONTH),
                expiryDateYogurt.get(Calendar.DAY_OF_MONTH) + 4
        );
        createDateYogurt.set(
                createDateYogurt.get(Calendar.YEAR),
                createDateYogurt.get(Calendar.MONTH),
                createDateYogurt.get(Calendar.DAY_OF_MONTH) - 33
        );
        Food yogurt = new Milk("yogurt",
                expiryDateYogurt,
                createDateYogurt, 235, 76);
        controlQuality.movingTheProduct(yogurt);

        Calendar expiryDateDeer = Calendar.getInstance();
        Calendar createDateDeer = Calendar.getInstance();
        expiryDateDeer.set(
                expiryDateDeer.get(Calendar.YEAR),
                expiryDateDeer.get(Calendar.MONTH),
                expiryDateDeer.get(Calendar.DAY_OF_MONTH) - 21
        );
        createDateDeer.set(
                createDateDeer.get(Calendar.YEAR),
                createDateDeer.get(Calendar.MONTH),
                createDateDeer.get(Calendar.DAY_OF_MONTH) - 99
        );
        Food deer = new Meat("deer",
                expiryDateDeer,
                createDateDeer, 875, 11);
        controlQuality.movingTheProduct(deer);

        Calendar expiryDateCheese = Calendar.getInstance();
        Calendar createDateCheese = Calendar.getInstance();
        expiryDateCheese.set(
                expiryDateCheese.get(Calendar.YEAR) + 3,
                expiryDateCheese.get(Calendar.MONTH),
                expiryDateCheese.get(Calendar.DAY_OF_MONTH) + 21
        );
        createDateCheese.set(
                createDateCheese.get(Calendar.YEAR),
                createDateCheese.get(Calendar.MONTH),
                createDateCheese.get(Calendar.DAY_OF_MONTH), +10, 11, 12
        );
        Food cheese = new Milk("cheese",
                expiryDateCheese,
                createDateCheese, 151, 1);
        controlQuality.movingTheProduct(cheese);

        assertThat(shop.getFoodList()).contains(beef, yogurt);
        assertThat(trash.getFoodList()).contains(deer);
        assertThat(warehouse.getFoodList()).contains(cheese);

        controlQuality.resort();

        assertThat(shop.getFoodList()).contains(beef, yogurt);
        assertThat(trash.getFoodList()).contains(deer);
        assertThat(warehouse.getFoodList()).contains(cheese);
    }
}