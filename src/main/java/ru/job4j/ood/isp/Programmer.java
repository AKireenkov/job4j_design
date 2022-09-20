package ru.job4j.ood.isp;

/**
 * Интерфейс для демонстрации нарушения принципа ISP.
 * В данном случае, методы name() и age() будут лишними,
 * т.к. это общая информация о человеке, которую стоит вынести в отдельный класс.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 20.09.2022
 */
public interface Programmer {
    String name();

    int age();

    String language();
}
