package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.car.Car;

import java.util.ArrayList;
import java.util.List;

public class SharedParking implements Parking {
    private List<Car> passengers;
    private List<Car> trucks;
    private int numbersOfPassengersCars;
    private int numbersOfTrucksCars;

    public SharedParking(int numbersOfPassengersCars, int numbersOfTrucksCars) {
        this.numbersOfPassengersCars = numbersOfPassengersCars;
        this.numbersOfTrucksCars = numbersOfTrucksCars;
        this.passengers = new ArrayList<>(numbersOfPassengersCars);
        this.trucks = new ArrayList<>(numbersOfTrucksCars);
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
