package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenStatusWithOneError() throws IOException {
        File source = folder.newFile("statusWithOneError.txt");
        File target = folder.newFile("statusWithOneError.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("500 10:59:01");
            out.println("400 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(l -> rsl.append(l).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("""
                10:57:01;11:02:02;\r
                """));
    }

    @Test
    public void whenStatusWithTwoError() throws IOException {
        File source = folder.newFile("statusWithTwoError.txt");
        File target = folder.newFile("statusWithTwoError.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        Analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(l -> rsl.append(l).append(System.lineSeparator()));
        }
        assertThat(rsl.toString(), is("""
                10:57:01;10:59:01;\r
                11:01:02;11:02:02;\r
                """));
    }
}