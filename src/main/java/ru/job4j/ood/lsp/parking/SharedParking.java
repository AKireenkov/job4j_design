package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.car.Car;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.car.Passenger.PASSENGER_CAR_SIZE;

public class SharedParking implements Parking {
    private final List<Car> cars;
    private final List<Car> trucks;
    private int carAmount;
    private int truckAmount;

    public SharedParking(int carAmount, int truckAmount) {
        this.carAmount = carAmount;
        this.truckAmount = truckAmount;
        this.cars = new ArrayList<>(carAmount);
        this.trucks = new ArrayList<>(truckAmount);
    }

    @Override
    public boolean add(Car car) {
        boolean accept = accept(car);
        if (accept) {
            int size = car.getSize();
            if (size > PASSENGER_CAR_SIZE && truckAmount > 0) {
                trucks.add(car);
                truckAmount--;
            } else if (size >= PASSENGER_CAR_SIZE && carAmount > 0) {
                cars.add(car);
                carAmount -= car.getSize();
            }
        }
        return accept;
    }

    @Override
    public boolean accept(Car car) {
        int size = car.getSize();
        return carAmount >= size || (truckAmount > 0 && size > PASSENGER_CAR_SIZE);
    }

    @Override
    public List<Car> getCarsList() {
        List<Car> allCars = new ArrayList<>(cars);
        allCars.addAll(trucks);
        return allCars;
    }
}
