package com.nhnacademy.parkingservicesystem.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Exit {
    FeeTable feeTable;
    public long pay(LocalDateTime enterTime){
        return checkTimeAndCalculated(enterTime);
    }

    public void setFee(){
        HashMap<String, Integer> InitializationFeeTable = new HashMap<>();
        InitializationFeeTable.put("first30MinuteFee",0);
        InitializationFeeTable.put("after30MinuteFeeFrom1Hour",1000);
        InitializationFeeTable.put("add10minutesFee",500);
        InitializationFeeTable.put("oneDayFee",15_000);
        feeTable = new FeeTable(InitializationFeeTable);
    }

    public long checkTimeAndCalculated(LocalDateTime enterTime){
        setFee();
        final int oneDaySeconds = 86400;
        long resultFee = 0;
        LocalDateTime exitTime = LocalDateTime.now();// LocalTime은 00시에 대한 기준을 처리 못해서 LocalDateTime 사용.
        Duration stayTime = Duration.between(enterTime,exitTime);
        long stayTimeSecond = stayTime.getSeconds();
        if (stayTimeSecond>=oneDaySeconds){ // 주차장에 머문 시간에 24시간 이상이면
            while(stayTimeSecond>oneDaySeconds){ // 주차장에 있는 시간이 24시간 아래가 될때까지 반복
                stayTimeSecond-=oneDaySeconds;
                resultFee+=feeTable.getFee("oneDayFee");
            }
        }else{ // 머문시간이 24시간 이내 면
            stayTimeSecond -= 1800;
            resultFee += feeTable.getFee("first30MinuteFee");
        }
        stayTimeSecond-=1800;
        resultFee += feeTable.getFee("after30MinuteFeeFrom1Hour");

        while (stayTimeSecond>600) { // 10분 아래가 될때까지 반복
            stayTimeSecond -= 600;
            resultFee += feeTable.getFee("add10minutesFee");
        }
        if(stayTimeSecond != 0){
            resultFee += feeTable.getFee("add10minutesFee");
        }


        return resultFee;
    }
}
