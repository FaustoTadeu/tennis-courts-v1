package com.tenniscourts.reservations;

import org.springframework.stereotype.Component;

import com.tenniscourts.guests.DTO.GuestDTO;
import com.tenniscourts.schedules.ScheduleDTO;
import com.tenniscourts.tenniscourts.TennisCourtDTO;

@Component
public class ReservationMapperImpl implements ReservationMapper {

	@Override
	public Reservation map(ReservationDTO source) {
		return null;
	}

	@Override
	public ReservationDTO map(Reservation reservation) {
		return ReservationDTO.builder()
		       .id(reservation.getId())
			   .schedule(ScheduleDTO.builder()
		            .id(reservation.getSchedule().getId())
		            .tennisCourt(TennisCourtDTO.builder()
		                .id(reservation.getSchedule().getTennisCourt().getId())
		                .name(reservation.getSchedule().getTennisCourt().getName())
		                .build())
		           .startDateTime(reservation.getSchedule().getStartDateTime())
		           .endDateTime(reservation.getSchedule().getEndDateTime())
		           .build())
			   .guest(GuestDTO.builder()
			       .id(reservation.getGuest().getId())
			       .name(reservation.getGuest().getName())
				   .build())
			   .reservationStatus(reservation.getReservationStatus().name())
			   .refundValue(reservation.getRefundValue())
			   .value(reservation.getValue())
			   .build();
	}

	@Override
	public Reservation map(CreateReservationRequestDTO source) {
		return null;
	}
	
	

}
