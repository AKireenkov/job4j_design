package ru.job4j.serialization.json;

public class Equipment {
    private final boolean conditioner;
    private final int winterTires;

    public Equipment(boolean conditioner, int winterTires) {
        this.conditioner = conditioner;
        this.winterTires = winterTires;
    }

    @Override
    public String toString() {
        return "Equipment{"
                + "conditioner=" + conditioner
                + ", winterTires=" + winterTires
                + '}';
    }
}
