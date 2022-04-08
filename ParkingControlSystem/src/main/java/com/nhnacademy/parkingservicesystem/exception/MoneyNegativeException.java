package com.nhnacademy.parkingservicesystem.exception;

public class MoneyNegativeException extends IllegalArgumentException{
    public MoneyNegativeException(String s) {
        super(s);
    }
}
