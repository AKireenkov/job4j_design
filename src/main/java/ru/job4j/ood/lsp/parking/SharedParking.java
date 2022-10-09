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
            if (car.getSize() == PASSENGER_CAR_SIZE) {
                cars.add(car);
                carAmount--;
            } else if (truckAmount > 0) {
                trucks.add(car);
                truckAmount--;
            } else {
                cars.add(car);
                carAmount -= car.getSize();
            }
        }
        return accept;
    }

    @Override
    public boolean accept(Car car) {
        return car.getSize() == PASSENGER_CAR_SIZE && carAmount > 0 || truckAmount > 0 || carAmount >= car.getSize();
    }

    @Override
    public List<Car> getCarsList() {
        List<Car> allCars = new ArrayList<>(cars);
        allCars.addAll(trucks);
        return allCars;
    }
}
