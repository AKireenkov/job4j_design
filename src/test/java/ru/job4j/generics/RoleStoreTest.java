package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleTypeIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role result = store.findById("1");
        assertThat(result.getType(), is("User"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleTypeUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.add(new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getType(), is("User"));
    }

    @Test
    public void whenReplaceThenRoleTypeIsAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("1", new Role("1", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getType(), is("Admin"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleType() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.replace("10", new Role("10", "Admin"));
        Role result = store.findById("1");
        assertThat(result.getType(), is("User"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleTypeIsUser() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "User"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getType(), is("User"));
    }
}