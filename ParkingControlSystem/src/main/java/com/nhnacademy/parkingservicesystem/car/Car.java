package com.nhnacademy.parkingservicesystem.car;

import com.nhnacademy.parkingservicesystem.user.User;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Car {
    private String licensePlate;
    private LocalDateTime enterTime;
    private User user;

    public Car(String licensePlate, User user) {
        this.licensePlate = licensePlate;
        this.user = user;
    }

    public String getLicensePlate() {
        return licensePlate;
    }


    public LocalDateTime getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(LocalDateTime enterTime) {
        this.enterTime = enterTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Car car = (Car) o;

        return licensePlate == car.licensePlate;
    }


    @Override
    public int hashCode() {
        return Objects.hash(licensePlate);
    }
}
