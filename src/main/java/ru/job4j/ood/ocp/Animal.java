package ru.job4j.ood.ocp;

/**
 * Класс для демонстрации нарушения принципа Open Closed Principle.
 * Все классы, которые будут наследоваться от Animal, при переопределении numberOfPaws(),
 * не смогут учитывать, что у таких типов как рыбы/птицы, есть плавники/крылья, т.е. другой тип конечностей.
 * Тогда, нужно будет создавать отдельный метод.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 06.09.2022
 */
public class Animal {
    private String nickname;
    private int paws;

    public Animal(String nickname, int paws) {
        this.nickname = nickname;
        this.paws = paws;
    }

    /**
     * тут описываем логику для работы с лапами животных,
     * например, определяем тип животного, в зависимости от количества лап - собака/обезьяна/кошка и тд
     *
     * @param number количество лап у животного
     */
    public void numberOfPaws(int number) {
    }
}
