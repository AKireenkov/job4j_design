package ru.job4j.template;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArticlesGeneratorTest {

    @Ignore
    @Test
    public void whenMissingKey() {
        Generator generator = new ArticlesGenerator();
        Map<String, String> map = Map.of("name", "Andrey", "subject", "you");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("${who} am a ${name}, Who are ${subject}?", map);
        });
    }

    @Ignore
    @Test
    public void whenExtraKey() {
        Generator generator = new ArticlesGenerator();
        Map<String, String> map = Map.of("name", "Andrey",
                "subject", "you",
                "who", "I");
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce("I am a ${name}, Who are ${subject}?", map);
        });
    }

    @Ignore
    @Test
    public void whenHaveAllKeyAndValue() {
        Generator generator = new ArticlesGenerator();
        Map<String, String> map = Map.of("name", "Andrey",
                "subject", "you");
        assertThat(generator.produce("I am a ${name}, Who are ${subject}?", map))
                .isEqualTo("I am a Andrey, Who are you?");
    }

    @Ignore
    @Test
    public void whenValueDoesNotMatch() {
        Generator generator = new ArticlesGenerator();
        Map<String, String> map = Map.of("name", "Andrey",
                "subject", "you");
        assertThat(generator.produce("I am a ${name}, Who are ${subject}?", map))
                .isNotEqualTo("I am a Petr, Who are you?");
    }
}