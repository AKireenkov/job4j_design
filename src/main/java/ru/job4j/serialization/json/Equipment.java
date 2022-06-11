package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;

public class Equipment {

    @XmlAttribute
    private boolean conditioner;
    @XmlAttribute
    private int winterTires;

    public Equipment() {
    }

    public Equipment(boolean conditioner, int winterTires) {
        this.conditioner = conditioner;
        this.winterTires = winterTires;
    }

    @Override
    public String toString() {
        return "Equipment{"
                + "conditioner=" + conditioner
                + ", winterTires=" + winterTires
                + '}';
    }
}
