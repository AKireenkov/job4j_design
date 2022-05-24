package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {
    @Test
    public void whenPut() {
        SimpleMap<Integer, String> input = new SimpleMap<>();
        Assert.assertTrue(input.put(1, "test"));
        Assert.assertThat(input.get(1), is("test"));
        Assert.assertTrue(input.put(2, "test2"));
        Assert.assertTrue(input.put(3, "test3"));
        Assert.assertThat(input.get(2), is("test2"));
    }

    @Test
    public void whenPutDuplicate() {
        SimpleMap<Integer, String> input = new SimpleMap<>();
        Assert.assertTrue(input.put(1, "test"));
        Assert.assertFalse(input.put(1, "test2"));
        Assert.assertThat(input.get(1), is("test"));
    }

    @Test
    public void whenGet() {
        SimpleMap<Integer, String> input = new SimpleMap<>();
        assertNull(input.get(1));
        Assert.assertTrue(input.put(2, "test2"));
        Assert.assertThat(input.get(2), is("test2"));
    }

    @Test
    public void whenGetNegative() {
        SimpleMap<Integer, String> input = new SimpleMap<>();
        Assert.assertTrue(input.put(2, "test2"));
        Assert.assertNotEquals("test", input.get(2));
    }

    @Test
    public void whenRemove() {
        SimpleMap<Integer, String> input = new SimpleMap<>();
        Assert.assertTrue(input.put(1, "test"));
        Assert.assertTrue(input.remove(1));
        Assert.assertFalse(input.remove(5));
    }

    @Test
    public void whenRemoveEmpty() {
        SimpleMap<Integer, String> input = new SimpleMap<>();
        Assert.assertFalse(input.remove(5));
    }

    @Test
    public void whenGetIterator() {
        SimpleMap<Integer, String> input = new SimpleMap<>();
        input.put(1, "test");
        input.put(2, "test2");
        Iterator<Integer> it = input.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorNegative() {
        SimpleMap<Integer, String> input = new SimpleMap<>();
        Iterator<Integer> it = input.iterator();
        it.next();
    }
}