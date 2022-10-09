package ru.job4j.ood.lsp.violation;

/**
 * Класс для демонстрации нарушения принципа LSP.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 09.09.2022
 */
public class Audi extends Car {
    public Audi(int wheel, String color) {
        super(wheel, color);
    }

    /**
     * Метод нарушает принцип,
     * т.к. условие в родительском классе Car, которое выбрасывает исключение - тут не реализовано.
     *
     * @param color цвет автомобиля.
     * @return стоимость, в зависимости от цвета автомобиля.
     */
    @Override
    public int cost(String color) {
        int cost = 777;
        if ("A8".equals(color)
                || "A4".equals(color)
                || "TT".equals(color)) {
            cost = 1000;
        }
        return cost;
    }

    /**
     * Метод нарушает принцип, т.к. тут реализована лишняя проверка на возраст водителя.
     *
     * @param driver объект водитель.
     * @return true если водитель имеет право на вождение, иначе false.
     */
    @Override
    public boolean canDrive(Driver driver) {
        return super.canDrive(driver) && driver.getAge() > 21;
    }
}
