package ru.job4j.ood.isp.menu;

import java.util.List;
import java.util.Scanner;

public class TODOApp {
    private static final ActionDelegate STUB_ACTION = System.out::println;
    private static final int ADD = 1;
    private static final int SELECT = 2;
    private static final int PRINT = 3;
    private static final int EXIT = 4;
    private static final String YES = "Да";
    private static final String NO = "Нет";

    private static final List<String> ELEMENTS = List.of("1.Добавить", "2.Выбрать", "3.Распечатать", "4.Выход");

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        ConsoleMenuPrinter consoleMenuPrinter = new ConsoleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        start(scanner, menu, consoleMenuPrinter);
    }

    private static void start(Scanner scanner, Menu menu, ConsoleMenuPrinter consoleMenuPrinter) {
        System.out.println("Выбирите номер действия: " + System.lineSeparator());
        ELEMENTS.forEach(System.out::println);
        int in = Integer.parseInt(scanner.nextLine());

        while (EXIT != in) {
            if (ADD == in) {
                System.out.println("Добавить подпункт к существующему пункту, да/нет ?");
                String input = scanner.nextLine();
                if (YES.equalsIgnoreCase(input)) {
                    System.out.println("Введите основной пункт");
                    String parentName = scanner.nextLine();
                    System.out.println("Введите подпункт");
                    String childName = scanner.nextLine();
                    boolean st = menu.add(parentName, childName, STUB_ACTION);
                } else if (NO.equalsIgnoreCase(input)) {
                    System.out.println("Введите пункт меню");
                    String itemName = scanner.nextLine();
                    menu.add(Menu.ROOT, itemName, STUB_ACTION);
                }
            } else if (SELECT == in) {
                System.out.println("Введите название пункта меню");
                String itemName = scanner.nextLine();
                System.out.println(menu.select(itemName).get());
            } else if (PRINT == in) {
                consoleMenuPrinter.print(menu);
            }
            ELEMENTS.forEach(System.out::println);
            in = Integer.parseInt(scanner.nextLine());
        }
    }
}
