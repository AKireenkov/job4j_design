package ru.job4j.ood.ocp;

import java.util.List;

/**
 * Класс для демонстрации нарушения принципа Open Closed Principle.
 * Поле animalList должно быть типом абстракции, но в данном случае - является списком конкретного типа Animal.
 * Метод add() - на вход принимает параметры конкретного типа, и возвращает так же, конкретный тип.
 * В случае, если нужно будет работать со списком другого типа (люди/автомобили/книги), нужно будет изменять этот класс.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 06.09.2022
 */
public class ListOfTypes {
    private List<Animal> animalList;

    public ListOfTypes() {
    }

    public Animal add(Animal animal) {
        animalList.add(animal);
        return animal;
    }
}
