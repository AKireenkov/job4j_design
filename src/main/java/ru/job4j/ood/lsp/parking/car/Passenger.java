package ru.job4j.ood.lsp.parking.car;

public class Passenger implements Car {
    public static final int PASSENGER_CAR_SIZE = 1;

    @Override
    public int getSize() {
        return 0;
    }
}
