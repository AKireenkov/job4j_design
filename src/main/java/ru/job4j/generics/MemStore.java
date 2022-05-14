package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        String id = model.getId();
        if (storage.get(id) == null) {
            storage.put(id, model);
        }
    }

    @Override
    public boolean replace(String id, T model) {
        T rsl = storage.containsKey(id) ? storage.put(id, model) : null;
        return rsl != null;
    }

    @Override
    public boolean delete(String id) {
        storage.remove(id);
        return !storage.containsKey(id);
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }
}