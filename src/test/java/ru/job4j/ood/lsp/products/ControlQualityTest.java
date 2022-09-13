package ru.job4j.ood.lsp.products;

import org.junit.Test;
import ru.job4j.ood.lsp.products.food.Food;
import ru.job4j.ood.lsp.products.food.Meat;
import ru.job4j.ood.lsp.products.food.Milk;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ControlQualityTest {
    @Test
    public void whenProductToWarehouse() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2024, Calendar.JULY, 30);
        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 1);

        Food milk = new Milk("Domik v derevne",
                expiryDate,
                createDate, 99, 50);

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.movingTheProduct(milk);
        assertThat(controlQuality.warehouse.getFoodList()).contains(milk);
    }

    @Test
    public void whenProductToShop() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.SEPTEMBER, 17);
        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 8);

        Food milk = new Milk("Prostokvashino",
                expiryDate,
                createDate, 69, 11);

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.movingTheProduct(milk);
        assertThat(controlQuality.shop.getFoodList()).contains(milk);
    }

    @Test
    public void whenProductToShopWithDiscount() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.OCTOBER, 10);
        Calendar createDate = Calendar.getInstance();
        createDate.set(2021, Calendar.JANUARY, 1);

        Food meat = new Meat("pork",
                expiryDate,
                createDate, 500, 300);
        Food expected = new Meat("pork",
                expiryDate,
                createDate, 200, 300);

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.movingTheProduct(meat);
        assertThat(controlQuality.shop.getFoodList()).contains(expected);
    }

    @Test
    public void whenProductToTrash() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.SEPTEMBER, 10);
        Calendar createDate = Calendar.getInstance();
        createDate.set(2020, Calendar.AUGUST, 22);

        Food meat = new Meat("chicken",
                expiryDate,
                createDate, 200, 100);

        ControlQuality controlQuality = new ControlQuality();
        controlQuality.movingTheProduct(meat);
        assertThat(controlQuality.trash.getFoodList()).contains(meat);
    }
}