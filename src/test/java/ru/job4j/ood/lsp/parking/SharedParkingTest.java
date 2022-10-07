package ru.job4j.ood.lsp.parking;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.ood.lsp.parking.car.Car;
import ru.job4j.ood.lsp.parking.car.Passenger;
import ru.job4j.ood.lsp.parking.car.Truck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class SharedParkingTest {
    @Test
    @Ignore
    public void whenPassengerAndTruckHasParking() {
        Parking parking = new SharedParking(2, 1);
        Car passengerOne = new Passenger();
        Car passengerTwo = new Passenger();
        Car truck = new Truck(2);
        assertTrue(parking.add(passengerOne));
        assertTrue(parking.add(passengerTwo));
        assertTrue(parking.add(truck));
        assertThat(parking.numberOfAvailableParkingLots()).isEqualTo(0);

    }

    @Test
    @Ignore
    public void whenTruckHasParkingOnPassenger() {
        Parking parking = new SharedParking(3, 0);
        Car passengerOne = new Passenger();
        Car truck = new Truck(2);
        parking.add(passengerOne);
        parking.add(truck);
        assertThat(parking.numberOfAvailableParkingLots()).isEqualTo(0);
        assertThat(parking.getCarsList()).contains(passengerOne, truck);
    }

    @Test
    @Ignore
    public void whenTruckDoesNotParking() {
        Parking parking = new SharedParking(3, 0);
        Car truck = new Truck(4);
        assertThat(parking.accept(truck)).isFalse();
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenCarIsNotATruck() {
        Car truck = new Truck(1);
    }

    @Test
    @Ignore
    public void whenTrucksHasParkingOnPassenger() {
        Parking parking = new SharedParking(4, 1);
        Car truckOne = new Truck(3);
        Car truckTwo = new Truck(2);
        parking.add(truckOne);
        parking.add(truckTwo);
        assertThat(parking.numberOfAvailableParkingLots()).isEqualTo(1);
        assertThat(parking.getCarsList()).contains(truckOne, truckTwo);
        assertThat(parking.accept(new Passenger())).isTrue();
    }
}