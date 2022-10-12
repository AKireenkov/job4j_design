package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Scanner;

public class TODOApp {
    private static final ActionDelegate STUB_ACTION = System.out::println;
    private static final String EXIT = "Выход";
    private static final String ADD = "Добавить";
    private static final String SELECT = "Выбрать";
    private static final String PRINT = "Распечатать";
    private static final String YES = "Да";
    private static final String NO = "Нет";

    private static final List<String> ELEMENTS = List.of("Добавить", "Выбрать", "Распечатать", "Выход");

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        ConsoleMenuPrinter consoleMenuPrinter = new ConsoleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        start(scanner, menu, consoleMenuPrinter);
    }

    private static void start(Scanner scanner, Menu menu, ConsoleMenuPrinter consoleMenuPrinter) {
        System.out.println("Выбирите действие: " + System.lineSeparator());
        ELEMENTS.forEach(System.out::println);
        String in = scanner.nextLine();

        while (!EXIT.equalsIgnoreCase(in)) {
            if (ADD.equalsIgnoreCase(in)) {
                System.out.println("Добавить подпункт к существующему пункту, да/нет ?");
                in = scanner.nextLine();
                if (YES.equalsIgnoreCase(in)) {
                    System.out.println("Введите основной пункт");
                    String parentName = scanner.nextLine();
                    System.out.println("Введите подпункт");
                    String childName = scanner.nextLine();
                    boolean st = menu.add(parentName, childName, STUB_ACTION);
                } else if (NO.equalsIgnoreCase(in)) {
                    System.out.println("Введите пункт меню");
                    String itemName = scanner.nextLine();
                    menu.add(Menu.ROOT, itemName, STUB_ACTION);
                }
            } else if (SELECT.equalsIgnoreCase(in)) {
                System.out.println("Введите название пункта меню");
                String itemName = scanner.nextLine();
                System.out.println(menu.select(itemName).get());
            } else if (PRINT.equalsIgnoreCase(in)) {
                consoleMenuPrinter.print(menu);
            }
            ELEMENTS.forEach(System.out::println);
            in = scanner.nextLine();
        }
    }
}
