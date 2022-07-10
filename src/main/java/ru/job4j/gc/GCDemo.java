package ru.job4j.gc;

/**
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 11.07.2022
 */
public class GCDemo {

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    /**
     * Выводит на экран количество памяти до создания объектов, и после
     * При применении ключей -Xmx4m -Xms4m и создании объектов при i < 2000
     * Вызывается автоматическая очистка мусора
     */
    public static void main(String[] args) {
        info();
        for (int i = 0; i < 2000; i++) {
            new User(i, "N" + i);
        }
        info();
    }
}