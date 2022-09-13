package ru.job4j.ood.lsp.products;

import org.junit.Test;
import ru.job4j.ood.lsp.products.food.Milk;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ControlQualityTest {
    @Test
    public void whenProductToWarehouse() {

    }

    @Test
    public void whenProductToShop() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.SEPTEMBER, 17);

        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 8);

        Milk milk = new Milk("test",
                expiryDate,
                createDate, 200.22, 100.00);


        ControlQuality controlQuality = new ControlQuality();
        controlQuality.movingTheProduct(milk);
        assertThat(controlQuality.shop.getFoodList()).contains(milk);
    }

    @Test
    public void whenProductToShopWithDiscount() {

    }

    @Test
    public void whenProductToTrash() {

    }
}