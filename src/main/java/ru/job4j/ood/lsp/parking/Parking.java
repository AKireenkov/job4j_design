package ru.job4j.ood.lsp.parking;

import ru.job4j.collection.list.List;
import ru.job4j.ood.lsp.parking.car.Car;

/**
 * Интерфейс для реализации парковки.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 14.09.2022
 */
public interface Parking {
    /**
     * Метод определения типа автомобиля (легковой/грузовой).
     *
     * @param car объект типа Car.
     * @return тип автомобиля passenger/truck.
     */
    String typeCar(Car car);

    /**
     * Метод добавления автомобиля на парковочное место.
     *
     * @param car объект типа Car.
     * @return true, если автомобиль добавлен на парковку, иначе false.
     */
    boolean add(Car car);

    /**
     * Метод проверки, может ли автомобиль быть помещен на парковку.
     * Проверяет наличие свободных парковочных мест, и сопоставляет с типом автомобиля.
     *
     * @param car объект типа Car.
     * @return true, если автомобиль может быть добавлен на парковку, иначе false.
     */
    boolean accept(Car car);

    /**
     * Метод определяет количество оставшихся свободных мест на парковке.
     *
     * @return возвращает число свободных мест.
     */
    int numberOfAvailableParkingLots();

    /**
     * @return метод возвращает список автомобилей, которые помещены на парковку.
     */
    List<Car> getCarsList();
}
