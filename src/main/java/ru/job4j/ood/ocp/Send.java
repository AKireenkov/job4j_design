package ru.job4j.ood.ocp;

/**
 * Класс для демонстрации нарушения принципа Open Closed Principle.
 * Класс Message нарушает принцип - если нужно будет изменить подпись в сообщении - придется создать новый метод.
 * При этом, не будет смысла в наследовании класса Message.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 06.09.2022
 */
public class Send {
    private static class Message {
        public String email(String text) {
            return text
                    + System.lineSeparator()
                    + "sincerely, from test@yandex.ru";
        }
    }

    public static void main(String[] args) {
        System.out.println(new Message().email("testing ood principle - ocp"));
    }
}
