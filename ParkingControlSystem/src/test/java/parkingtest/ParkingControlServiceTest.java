package parkingtest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.nhnacademy.parkingservicesystem.car.Car;
import com.nhnacademy.parkingservicesystem.exception.MoneyNegativeException;
import com.nhnacademy.parkingservicesystem.parkingservice.ParkingControlService;
import com.nhnacademy.parkingservicesystem.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParkingControlServiceTest {
    ParkingControlService service;

    @BeforeEach
    void setUp(){
        service = new ParkingControlService();
    }

    @Test
    @DisplayName("주차장에 차가 들어오면 번호판 인식해보기")
    void scan_the_license_plate_car_enter_the_parking_lot(){
        User user = new User(20_000);
        Car car = new Car("12하 3456", user);
        String toCompareLicensePlate = "12하 3456";
        assertThat(service.scanTheLicensePlate(car)).isEqualTo(toCompareLicensePlate);
    }

    @Test
    @DisplayName("차를 특정 구역에 주차 테스트 코드")
    void parking_in_a_specific_area(){
        User user = new User(20_000);
        Car car = new Car("12하 3456", user);
        service.parkingSpecificArea("A-1",car);
        assertThat(service.checkForParking("A-1")).isEqualTo(car);
    }

    @Test
    @DisplayName("나갈 때 요금 확인하기")
    void exit_fee_notify(){
        User user = new User(20_000);
        Car car = new Car("12하 3456",user);
        service.parkingSpecificArea("A-1",car);// 차를 한번 주차했다가
        assertThat(service.getFee("A-1")).isEqualTo(11000); // LocalTime.now를 써서 12:00 ~ 현재 코딩하는 시간 16시13분 now의 결과가 12500 성공
    }

    @Test
    @DisplayName("주차장에서 차가 나가는데 나가려면 주차 시간만큼 결제 필요")
    void payment_after_exit_parking_lot(){
        User user = new User(20_000);
        Car car = new Car("12하 3456",user);
        service.parkingSpecificArea("A-1",car);;// 차를 한번 주차했다가
        assertThat(service.exitParkingLot("A-1")).isEqualTo(0); // 계산을 해고 뺐으니 size는 0이여야한다.
    }

    @Test
    @DisplayName("주차된 차가 나가려 하는데 돈이 없으면 못나간다는 예외 상황 던지기")
    void no_money_no_leave(){
        User user = new User(100);
        Car car = new Car("12하 3456",user);
        service.parkingSpecificArea("A-1",car);;// 차를 한번 주차했다가
        assertThatThrownBy(()->service.exitParkingLot("A-1"))
            .isInstanceOf(MoneyNegativeException.class)
            .hasMessageContaining("잔액","부족");
    }

    @Test
    @DisplayName("요금표 변경")
    void change_the_price_table_and_car_level_check(){
        User user = new User(20_000);
        Car car = new Car("12하 3456",user);
        service.parkingSpecificArea("A-1",car);// 차를 한번 주차했다가
        assertThat(service.getFee("A-1")).isEqualTo(11000); // 변경된 요금표로 리턴
    }
}
