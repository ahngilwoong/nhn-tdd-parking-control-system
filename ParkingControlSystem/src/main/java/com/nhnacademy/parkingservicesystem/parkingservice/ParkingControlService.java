package com.nhnacademy.parkingservicesystem.parkingservice;

import com.nhnacademy.parkingservicesystem.car.Car;
import com.nhnacademy.parkingservicesystem.parkinglot.ParkingLot;

public class ParkingControlService {
    ParkingLot parkingLot = new ParkingLot();

    public String scanTheLicensePlate(Car targetCar) {
        return parkingLot.alertCarNumber(targetCar);
    }

    public void parkingSpecificArea(String parkingArea, Car car) {
        parkingLot.enterParkingCar(parkingArea,car);
    }

    public Car checkForParking(String parkingArea) {
        return parkingLot.getSpaces().get(parkingArea);
    }

    public int exitParkingLot(String area) {
        parkingLot.feePayment(area);
        parkingLot.exit(area);
        return parkingLot.getSpaces().size();
    }

    public long getFee(String area) {
        return parkingLot.getFee(area);
    }
}
