package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Andrey Kireenkov"));
        assertThat(config.value("password"), is("admin="));
        assertThat(config.value("value"), is("val=1"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("user"), is("Andrey"));
        assertThat(config.value("value"), is("15"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithValueOnly() {
        String path = "./data/pair_with_value_only.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithKeyOnly() {
        String path = "./data/pair_with_key_only.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithKeyValueExeption() {
        String path = "./data/pair_with_key_value.properties";
        Config config = new Config(path);
        config.load();
    }
}
