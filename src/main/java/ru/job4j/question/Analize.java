package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> prev = new HashMap<>();

        for (User p : previous) {
            prev.put(p.getId(), p.getName());
        }

        for (User c : current) {
            int id = c.getId();
            String name = c.getName();
            if (prev.get(id) != null && !prev.get(id).equals(name)) {
                info.setChanged(+1);
            } else if (prev.get(id) == null) {
                info.setAdded(+1);
                if (current.size() == prev.size()) {
                    info.setDeleted(+1);
                }
            }
            if (current.size() < prev.size()) {
                info.setDeleted(+1);
            }
        }
        return info;
    }

}
