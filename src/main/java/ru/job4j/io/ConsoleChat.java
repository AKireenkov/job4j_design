package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> answers = readPhrases();
        System.out.println("Введите свой вопрос:");
        Scanner input = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        String question = input.nextLine();
        breakIfOut:
        while (!OUT.equals(question)) {
            if (STOP.equals(question)) {
                while (!CONTINUE.equals(question)) {
                    log.add("Вопрос: " + question);
                    question = input.nextLine();
                    if (OUT.equals(question)) {
                        break breakIfOut;
                    }
                }
                log.add("Вопрос: " + question);
            } else {
                log.add("Вопрос: " + question);
                String answ = "Ответ бота: " + answers.get(new Random().nextInt(answers.size()));
                log.add(answ);
                System.out.println(answ);
            }
            question = input.nextLine();
            log.add(question);
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, Charset.forName("WINDOWS-1251")))) {
            answers.addAll(in.lines().toList());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path, Charset.forName("WINDOWS-1251"))
        )) {
            log.forEach(out::println);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/dialogue.txt", "./data/botAnswers.txt");
        cc.run();
    }
}
