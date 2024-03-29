package ru.job4j.cache;

import java.util.Scanner;

/**
 * Эмулятор, для работы с пользователем.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.08.2022
 */
public class Emulator {

    public static final String DEFAULT_PATH = "src/main/java/ru/job4j/cache/files/";

    public static final int CACHING_DIRECTORY = 1;

    public static final int CACHING_FILE = 2;

    private static final int UPLOAD_FILE = 3;

    private static final int GET_FILE_FROM_CACHE = 4;

    private static final String MENU = """
            1. Указать директорию
            2. Указать кэшируемый файл
            3. Загрузить содержимое указанного файла в кэш
            4. Получить содержимое файла из кэша
            """;

    /**
     * Метод реализует основную логику работы пунктов меню.
     * Позволяет указать директорию из которой будет закеширован файл, если такая директория не указана, будет использована директория по умолчанию.
     * Позволяет указать кэшируемый файл, загрузить указанный файл в кэш и достать содержимое из файла.
     * При этом, если не указан кэшируемый файл, но пользователь попытается загрузить/достать значение из файла - он получит информационное сообщение и будет возвращен в меню.
     *
     * @param scanner      сканирует введенные символы из консоли
     * @param dirFileCache объект класса DirFileCache, гворит о том, что в методе используется реализация кеширования значения из файла.
     */
    private void init(Scanner scanner, DirFileCache dirFileCache) {
        boolean run = true;
        String directory = " ";
        String fileName = " ";
        while (run) {
            System.out.println(MENU);
            int num = Integer.parseInt(scanner.nextLine());
            if (num == CACHING_DIRECTORY) {
                System.out.println("Укажите относительный путь к кэшируемому файлу:");
                directory = scanner.nextLine();
            } else if (num == CACHING_FILE) {
                if (directory.isBlank()) {
                    System.out.println("Будет закэширован файл из папки по умолчанию - src/main/java/ru/job4j/cache/files");
                    directory = DEFAULT_PATH;
                }
                System.out.println("Укажите файл для кэширования");
                fileName = scanner.nextLine();
            } else if (num == UPLOAD_FILE) {
                if (fileName.isBlank()) {
                    System.out.println("Необходимо указать название файла для кэширования!");
                    break;
                }
                dirFileCache.put(fileName, dirFileCache.get(fileName));
            } else if (num == GET_FILE_FROM_CACHE) {
                if (fileName.isBlank()) {
                    System.out.println("Необходимо указать название файла для кэширования!");
                    break;
                }
                System.out.println("Введите название файла");
                System.out.println(dirFileCache.get(scanner.nextLine()));
            } else {
                run = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Emulator emulator = new Emulator();
        DirFileCache dirFileCache = new DirFileCache(DEFAULT_PATH);
        emulator.init(scanner, dirFileCache);
    }
}
