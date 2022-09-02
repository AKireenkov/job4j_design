package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный класс с фабричным методом load(), для реализации разных вариантов кеширования файлов.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 05.08.2022
 */
public abstract class AbstractCache<K, V> {
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    /**
     * Метод, записывает в карту cache пару ключ значение.
     *
     * @param key   ключ (в реализации DirFileCache ключ это название файла для кеширования)
     * @param value в карту кладем не само значение, а безопасную ссылку на него.
     *              Такая ссылка, будет удалена только в случае нехватки памяти в JVM.
     */
    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    /**
     * В методе, происходит получение значение из кеша, при этом используется метод getOrDefault() для безопасного получения значения,
     * где дефолтным значением будет SoftReference с null.
     * Если значение из кеша - null, тогда снова загружаем значение с помощью load(key) и кладем в кеш, по ключу.
     *
     * @param key ключ для получения/записи кеша.
     * @return значение полученное из кеша.
     */
    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    /**
     * Абстрактный метод, загрузки данных из какого либо источника. Например: DirFileCache - получение данных из файла.
     *
     * @param key ключ, по которому ищем элемент для выгрузки данных
     * @return значение, полученное из указанного источника
     */
    protected abstract V load(K key);
}
