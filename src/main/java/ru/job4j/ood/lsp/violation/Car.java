package ru.job4j.ood.lsp.violation;

/**
 * Класс для демонстрации нарушения принципа LSP.
 * В данном случае, метод typeCar() нарушает принцип,
 * т.к. условие отбирает из конкретного типа.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 09.09.2022
 */
public class Car {
    private int wheel;
    private String color;

    private Driver driver;

    public Car(int wheel, String color) {
        this.wheel = wheel;
        this.color = color;
    }

    public String typeCar(int wheel) {
        return switch (wheel) {
            case 4 -> "Sedan";
            case 6 -> "Truck";
            case 8 -> "Bus";
            default -> "unknown type";
        };
    }

    public int cost(String color) {
        if (!color.contains("red")
                || !color.contains("black")
                || !color.contains("white")) {
            throw new IllegalArgumentException("cars of this color do not exist!");
        }
        return color.contains("black") ? 100 : 10;
    }

    public boolean canDrive(Driver driver) {
        return driver.isDriverLicense();
    }
}
