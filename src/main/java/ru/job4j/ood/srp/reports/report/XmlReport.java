package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.employee.Employee;
import ru.job4j.ood.srp.reports.employee.Employees;
import ru.job4j.ood.srp.reports.store.MemStore;
import ru.job4j.ood.srp.reports.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class XmlReport implements Report {
    private Store store;
    private Marshaller marshaller;

    public XmlReport(Store store) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String xml = "";
        StringWriter stringWriter = new StringWriter();
        try {
            marshaller.marshal(new Employees(store.findBy(filter)), stringWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        xml = stringWriter.getBuffer().toString();
        return xml;
    }

    public static void main(String[] args) throws JAXBException {
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100.0);
        Employee worker2 = new Employee("Andrey", now, now, 200.0);

        Store store = new MemStore();
        store.add(worker1);
        store.add(worker2);

        String xml = new XmlReport(store).generate(employee -> true);
    }
}
