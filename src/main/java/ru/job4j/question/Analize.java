package ru.job4j.question;

import java.util.List;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> current, Set<User> previous) {
        Info info = new Info(0, 0, 0);
        List<Integer> idPrevious = previous.stream().map(User::getId).toList();
        List<Integer> idCurrent = current.stream().map(User::getId).toList();
        for (User u : current) {
            if (!previous.contains(u) && !idPrevious.contains(u.getId())) {
                info.setDeleted(+1);
            } else if (!previous.contains(u) && idPrevious.contains(u.getId())) {
                info.setChanged(+1);
            }
        }
        for (User p : previous) {
            if (!current.contains(p) && !idCurrent.contains(p.getId())) {
                info.setAdded(+1);
            }
        }
        return info;
    }

}
