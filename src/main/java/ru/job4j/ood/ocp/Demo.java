package ru.job4j.ood.ocp;

/**
 * Класс для демонстрации нарушения принципа Open Closed Principle.
 * Метод returnName() нарушает принцип, т.к. реализует возвращение имени только объекта User.
 * Если нужно будет вернуть имя например для объекта Animal - нужен будет новый метод.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 06.09.2022
 */
public class Demo {
    public static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static String returnName(User user) {
        return user.name;
    }

    public static void main(String[] args) {
        System.out.println(returnName(new User("Andrey", 27)));
    }
}
