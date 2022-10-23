package ru.job4j.ood.lsp.violation;

public class Driver {
    private String name;
    private int age;
    private boolean driverLicense;

    public Driver(String name, int age, boolean driverLicense) {
        this.name = name;
        this.age = age;
        this.driverLicense = driverLicense;
    }

    public int getAge() {
        return age;
    }

    public boolean isDriverLicense() {
        return driverLicense;
    }
}
