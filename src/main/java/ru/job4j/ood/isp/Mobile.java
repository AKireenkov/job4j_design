package ru.job4j.ood.isp;

/**
 * Интерфейс для демонстрации нарушения принципа ISP.
 * В данном случае, всем моделям мобильного телефона придется реализовывать метод keyboard().
 * При этом, не вссе смартфоны физически имют клавиатуру.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 20.09.2022
 */
public interface Mobile {
    double cost();

    double diagonal();

    String keyboard();
}
