package ru.job4j.ood.lsp.parking.car;

/**
 * Интерфейс для реализации автомобиля.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 16.09.2022
 */
public interface Car {

    /**
     * @return метод возвращает true, если автомобиль помещен на парковку.
     */
    boolean onParking();

    /**
     * метод подсчитывает расход топлива для автомобиля.
     *
     * @param car объект типа Car, для которого будет произведен расчет.
     * @return расход топлива автомобиля.
     */
    double fuelConsumption(Car car);
}
