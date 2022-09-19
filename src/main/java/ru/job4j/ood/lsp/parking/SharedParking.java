package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.car.Car;

import java.util.ArrayList;
import java.util.List;

public class SharedParking implements Parking {
    private List<Car> passengers = new ArrayList<>();
    private List<Car> trucks = new ArrayList<>();
    private int carSize;

    public SharedParking(int carSize) {
        this.carSize = carSize;
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
    public int numberOfAvailableParkingLots() {
        return 0;
    }

    @Override
    public List<Car> getCarsList() {
        return null;
    }
}
