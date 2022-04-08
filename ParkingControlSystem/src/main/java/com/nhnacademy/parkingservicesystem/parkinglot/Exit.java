package com.nhnacademy.parkingservicesystem.parkinglot;

import java.time.Duration;
import java.time.LocalTime;

public class Exit {
    private int first30MinuteFee = 1000;
    private int add10minutesFee = 500;
    private int oneDayFee = 10_000;


    public long pay(LocalTime enterTime){
        final int oneDaySeconds = 86400;
        long resultFee = 0;
        LocalTime exitTime = LocalTime.now();// TODO 로컬 타임 00시에 대한 기준...
        Duration stayTime = Duration.between(enterTime,exitTime);
        long stayTimeSecond = stayTime.getSeconds();
        if (stayTimeSecond>=oneDaySeconds){ // 주차장에 머문 시간에 24시간 이상이면
            while(stayTimeSecond>oneDaySeconds){ // 주차장에 있는 시간이 24시간 아래가 될때까지 반복
                stayTimeSecond-=oneDaySeconds;
                resultFee+=oneDayFee;
            }
        }else{ // 머문시간이 24시간 이내 면
            stayTimeSecond -= 1800;
            resultFee += first30MinuteFee;
        }

         while (stayTimeSecond>600) { // 10분 아래가 될때까지 반복
             stayTimeSecond -= 600;
             resultFee += add10minutesFee;
         }
         if(stayTimeSecond != 0){
             resultFee += add10minutesFee;
         }
        return resultFee;
    }
}
