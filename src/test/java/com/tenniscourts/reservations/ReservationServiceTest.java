package com.tenniscourts.reservations;

import com.tenniscourts.schedules.Schedule;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ReservationService.class)
public class ReservationServiceTest {

    @InjectMocks
    ReservationService reservationService;

    @Test
    public void getRefundValueFullRefund() {
        LocalDateTime startDateTime = LocalDateTime.now().plusDays(2);
        Assert.assertEquals(reservationService.getRefundValue(Reservation.builder().schedule(Schedule.builder().startDateTime(startDateTime).build()).value(new BigDecimal(10L)).build()), new BigDecimal(10));
    }
    
    @Test
    public void getRefundValueSeventyFivePrecentRefund() {
        LocalDateTime startDateTime = LocalDateTime.now().plusHours(18);
        Assert.assertEquals(reservationService.getRefundValue(Reservation.builder().schedule(Schedule.builder().startDateTime(startDateTime).build()).value(new BigDecimal(10L)).build()), new BigDecimal(7.50));
    }
    
    @Test
    public void getRefundValueFifthPercentRefund() {
        LocalDateTime startDateTime = LocalDateTime.now().plusHours(10);
        Assert.assertEquals(reservationService.getRefundValue(Reservation.builder().schedule(Schedule.builder().startDateTime(startDateTime).build()).value(new BigDecimal(10L)).build()), new BigDecimal(5));
    }
    
    @Test
    public void getRefundValuetwentyfivePercentRefund() {
        LocalDateTime startDateTime = LocalDateTime.now().plusHours(1);
        Assert.assertEquals(reservationService.getRefundValue(Reservation.builder().schedule(Schedule.builder().startDateTime(startDateTime).build()).value(new BigDecimal(10L)).build()), new BigDecimal(2.50));
    }
}