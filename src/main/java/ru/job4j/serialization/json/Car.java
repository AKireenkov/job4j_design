package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean sedan;
    private final int cost;
    private final String model;
    private final Equipment equipment;
    private final String[] owners;


    public Car(boolean sedan, int cost, String model, Equipment equipment, String[] owners) {
        this.sedan = sedan;
        this.cost = cost;
        this.model = model;
        this.equipment = equipment;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "sedan=" + sedan
                + ", cost=" + cost
                + ", model='" + model + '\''
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }
}
