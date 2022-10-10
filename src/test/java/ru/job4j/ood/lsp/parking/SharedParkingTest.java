package ru.job4j.ood.lsp.parking;

import org.junit.Test;
import ru.job4j.ood.lsp.parking.car.Car;
import ru.job4j.ood.lsp.parking.car.Passenger;
import ru.job4j.ood.lsp.parking.car.Truck;

import static org.assertj.core.api.Assertions.assertThat;

public class SharedParkingTest {
    @Test
    public void whenPassengerAndTruckHasParking() {
        Parking parking = new SharedParking(2, 1);
        Car passengerOne = new Passenger();
        Car passengerTwo = new Passenger();
        Car truck = new Truck(2);
        assertThat(parking.add(passengerOne)).isTrue();
        assertThat(parking.add(passengerTwo)).isTrue();
        assertThat(parking.add(truck)).isTrue();
    }

    @Test
    public void whenTruckHasParkingOnPassenger() {
        Parking parking = new SharedParking(3, 0);
        Car passengerOne = new Passenger();
        Car truck = new Truck(2);
        parking.add(passengerOne);
        parking.add(truck);
        assertThat(parking.getCarsList()).contains(passengerOne, truck);
    }

    @Test
    public void whenTruckDoesNotParking() {
        Parking parking = new SharedParking(3, 0);
        Car truck = new Truck(4);
        assertThat(parking.accept(truck)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCarIsNotATruck() {
        Car truck = new Truck(1);
    }

    @Test
    public void whenTrucksHasParkingOnPassenger() {
        Parking parking = new SharedParking(4, 1);
        Car truckOne = new Truck(3);
        Car truckTwo = new Truck(2);
        parking.add(truckOne);
        parking.add(truckTwo);
        assertThat(parking.getCarsList()).contains(truckOne, truckTwo);
        assertThat(parking.accept(new Passenger())).isTrue();
    }

    @Test
    public void whenTrucksDoesNotParkingOnPassenger() {
        Parking parking = new SharedParking(4, 1);
        Car truckOne = new Truck(3);
        Car truckTwo = new Truck(2);
        parking.add(truckOne);
        parking.add(truckTwo);
        assertThat(parking.getCarsList()).contains(truckOne, truckTwo);
        assertThat(parking.accept(new Passenger())).isTrue();
    }

    @Test
    public void whenCarDoesNotParking() {
        Parking parking = new SharedParking(0, 2);
        assertThat(parking.add(new Passenger())).isFalse();
        assertThat(parking.add(new Passenger())).isFalse();
        assertThat(parking.add(new Truck(2))).isTrue();
        assertThat(parking.add(new Truck(2))).isTrue();
    }
}