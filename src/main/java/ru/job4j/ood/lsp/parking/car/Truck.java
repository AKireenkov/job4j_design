package ru.job4j.ood.lsp.parking.car;

import java.util.Objects;

import static ru.job4j.ood.lsp.parking.car.Passenger.PASSENGER_CAR_SIZE;

public class Truck implements Car {
    private final int size;

    public Truck(int size) {
        if (size <= PASSENGER_CAR_SIZE) {
            throw new IllegalArgumentException("This is not a truck !");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
