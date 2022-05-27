package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> prev = new HashMap<>();
        int add = 1;
        int del = 1;
        int ch = 1;

        for (User p : previous) {
            prev.put(p.getId(), p.getName());
        }

        for (User c : current) {
            int id = c.getId();
            String name = c.getName();
            if (prev.get(id) != null && !prev.get(id).equals(name)) {
                info.setChanged(ch++);
            } else if (prev.get(id) == null) {
                info.setAdded(add++);
                if (current.size() == prev.size()) {
                    info.setDeleted(del++);
                }
            }
            int diffSize = prev.size() - current.size();
            if (diffSize > 0) {
                info.setDeleted(diffSize);
            }
        }
        return info;
    }

}
