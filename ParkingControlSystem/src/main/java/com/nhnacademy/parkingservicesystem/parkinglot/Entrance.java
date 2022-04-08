package com.nhnacademy.parkingservicesystem.parkinglot;

import com.nhnacademy.parkingservicesystem.car.Car;
import java.time.LocalTime;

public class Entrance {

    public String scan(Car targetCar){
        String scanResult = targetCar.getLicensePlate();
        return scanResult;
    }
    public LocalTime getEnterTime(){
        return LocalTime.of(12, 00, 00); // LocalTime.now 가 맞으나 테스트를 위해 임의의 시간 설정.
    }
}
