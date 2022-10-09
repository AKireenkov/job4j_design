package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.car.Car;

import java.util.ArrayList;
import java.util.List;

public class SharedParking implements Parking {
    private final List<Car> passengers;
    private final List<Car> trucks;
    private int carAmount;
    private int truckAmount;

    public SharedParking(int carAmount, int truckAmount) {
        this.carAmount = carAmount;
        this.truckAmount = truckAmount;
        this.passengers = new ArrayList<>(carAmount);
        this.trucks = new ArrayList<>(truckAmount);
    }

    @Override
    public boolean add(Car car) {
        return false;
    }

    @Override
    public boolean accept(Car car) {
        return false;
    }

    @Override
    public List<Car> getCarsList() {
        return null;
    }
}
