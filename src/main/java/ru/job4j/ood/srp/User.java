package ru.job4j.ood.srp;

import java.util.List;

public class User {
    private String name;
    private int age;
    private String role;

    private List<User> users;

    public User(String name, int age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void add(User user) {
        users.add(user);
    }
}
