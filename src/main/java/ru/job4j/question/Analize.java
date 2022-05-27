package ru.job4j.question;

import java.util.List;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> current, Set<User> previous) {
        Info info = new Info(0, 0, 0);
        List<Integer> idPrevious = previous.stream().map(User::getId).toList();
        List<String> namePrevious = previous.stream().map(User::getName).toList();

        List<User> deleted = current
                .stream()
                .filter(c -> !previous.contains(c))
                .toList();
        List<User> added = previous
                .stream()
                .filter(p -> !current.contains(p))
                .toList();
        List<User> changed = current
                .stream()
                .filter(c -> idPrevious.contains(c.getId()) && !namePrevious.contains(c.getName()))
                .toList();

        info.setAdded(added.size() - changed.size());
        info.setDeleted(deleted.size() - changed.size());
        info.setChanged(changed.size());
        return info;
    }

}
