package ru.job4j.gc.prof;

import java.util.Random;
import java.util.Scanner;

public class StartUI {
    public void init(Scanner scanner) {
        boolean run = true;
        RandomArray randomArray = new RandomArray(new Random());
        while (run) {
            showMenu();
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 0) {
                System.out.println("Введите количество элементов массива");
                Scanner els = new Scanner(System.in);
                randomArray.insert(Integer.parseInt(els.nextLine()));
                System.out.println("Массив успешно создан");
            } else if (select == 1) {
                if (!randomArray.isEmpty()) {
                    Sort bubbleSort = new BubbleSort();
                    bubbleSort.sort(randomArray);
                } else {
                    System.out.println("Не создан массив для сортировки");
                }
            } else if (select == 2) {
                if (!randomArray.isEmpty()) {
                    Sort insertSort = new InsertSort();
                    insertSort.sort(randomArray);
                } else {
                    System.out.println("Не создан массив для сортировки");
                }
            } else if (select == 3) {
                if (!randomArray.isEmpty()) {
                    Sort mergeSort = new MergeSort();
                    mergeSort.sort(randomArray);
                } else {
                    System.out.println("Не создан массив для сортировки");
                }
            }
            if (select == 4) {
                run = false;
            }
        }
    }

    private void showMenu() {
        String[] menu = {"Создание массива", "Сортировка пузырьком",
                "Сортировка вставками", "Сортировка слиянием", "Выход."};
        System.out.println("Menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StartUI start = new StartUI();
        start.init(scanner);
    }
}
