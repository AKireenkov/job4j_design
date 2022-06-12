package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException {

        /**
         *  XML в POJO
         */
        final Car audi = new Car(false, 30000, "Q5", new Equipment(true, 4),
                new String[]{"Petr Ivanov", "Andrey Kireenkov"});

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(audi, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Car result = (Car) unmarshaller.unmarshal(reader);
            System.out.println(result);
            System.out.println(System.lineSeparator());
        }

        /**
         * JSON в POJO.
         */

        /* JSONObject из json-строки строки */
        JSONObject jsonEquipment = new JSONObject("{\"conditioner\":\"true\", \"winterTires\":\"4\"}"
        );

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Petr Ivanov");
        list.add("Andrey Kireenkov");
        JSONArray jsonOwners = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Car bmw = new Car(true, 777, "X6", new Equipment(false, 2),
                new String[]{"Petr Petrov", "Sergey Kireenkov"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sedan", bmw.isSedan());
        jsonObject.put("cost", bmw.getCost());
        jsonObject.put("model", bmw.getModel());
        jsonObject.put("equipment", jsonEquipment);
        jsonObject.put("owners", jsonOwners);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект car в json-строку */
        System.out.println(new JSONObject(bmw).toString());
    }
}