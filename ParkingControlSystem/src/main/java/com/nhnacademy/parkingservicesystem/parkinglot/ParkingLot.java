package com.nhnacademy.parkingservicesystem.parkinglot;

import com.nhnacademy.parkingservicesystem.car.Car;
import com.nhnacademy.parkingservicesystem.exception.MoneyNegativeException;
import com.nhnacademy.parkingservicesystem.user.User;
import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    Map<String, Car> parkingLotSpaces = new HashMap<>();
    Entrance entrance = new Entrance();
    Exit exit = new Exit();

    public void enterParkingCar(String parkingArea, Car car){
        car.setEnterTime(entrance.getEnterTime());
        parkingLotSpaces.put(parkingArea,car);

    }

    public Map<String, Car> getSpaces() {
        return parkingLotSpaces;
    }

    public String alertCarNumber(Car car){
        return entrance.scan(car);
    }

    public void feePayment(String area){
        long fee = getFee(area);
        if(parkingLotSpaces.get(area).getUser().getBalance()<fee){
            throw new MoneyNegativeException("잔액이 부족합니다.");
        }
        parkingLotSpaces.get(area).getUser().setBalance( parkingLotSpaces.get(area).getUser().getBalance()-fee );
    }

    public long getFee(String area){
        return exit.pay(parkingLotSpaces.get(area).getEnterTime());
    }

    public void exit(String area){
        this.parkingLotSpaces.remove(area);
    }



}
