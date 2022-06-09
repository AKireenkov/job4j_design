package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Andrey Kireenkov";
        byte monthsOfTraining = 3;
        int age = 33;
        boolean maleGender = true;
        float height = 192.5F;
        double weight = 78.2D;
        char gender = 'M';
        short shoeSize = 43;
        long clothingSize = 33;
        LOG.debug("User info name : {}, age : {}, maleGender : {}, height : {}, weight : {}, gender : {}, shoeSize : {}, clothingSize : {}, monthsOfTraining : {}",
                name, age, maleGender, height, weight, gender, shoeSize, clothingSize, monthsOfTraining);
    }
}