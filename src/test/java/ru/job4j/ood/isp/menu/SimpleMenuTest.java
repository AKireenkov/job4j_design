package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);

        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddAndPrint() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        String expected = """
                1.Сходить в магазин\r
                 1.1.Купить продукты\r
                  1.1.1.Купить хлеб\r
                  1.1.2.Купить молоко\r
                2.Покормить собаку\r
                """;
        String rsl = new ConsoleMenuPrinter().print(menu);
        assertThat(expected.equals(rsl)).isTrue();
    }

    @Test
    public void whenNotAdded() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(menu.add("Покормить собаку", "Покормить таксу", STUB_ACTION)).isFalse();
        assertThat(menu.add("Купить продукты", "Купить молоко", STUB_ACTION)).isFalse();
    }

    @Test
    public void whenAdded() {
        Menu menu = new SimpleMenu();
        assertThat(menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION)).isTrue();
        assertThat(menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION)).isTrue();
        assertThat(menu.add("Купить продукты", "Купить хлеб", STUB_ACTION)).isTrue();
        assertThat(menu.add("Купить продукты", "Купить молоко", STUB_ACTION)).isTrue();
        assertThat(menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION)).isTrue();
    }
}