package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private boolean sedan;
    @XmlAttribute
    private int cost;
    @XmlAttribute
    private String model;
    private Equipment equipment;
    @XmlElementWrapper(name = "owners")
    @XmlElement(name = "owner")
    private String[] owners;

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{"
                + "sedan=" + sedan
                + ", cost=" + cost
                + ", model='" + model + '\''
                + ", equipment=" + equipment
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }

    public Car(boolean sedan, int cost, String model, Equipment equipment, String[] owners) {
        this.sedan = sedan;
        this.cost = cost;
        this.model = model;
        this.equipment = equipment;
        this.owners = owners;
    }

}
