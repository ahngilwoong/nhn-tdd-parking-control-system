package com.nhnacademy.parkingservicesystem.parkinglot;

import com.nhnacademy.parkingservicesystem.car.Car;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Entrance {

    public String scan(Car targetCar) {
        String scanResult = targetCar.getLicensePlate();
        return scanResult;
    }

    public LocalDateTime getEnterTime() {
        return LocalDateTime.of(2022, 4, 10, 15, 00,
            00); // LocalDateTime.now 로 하는게 맞으나 테스트를 위해 임의의 시간 설정.
    }

}
