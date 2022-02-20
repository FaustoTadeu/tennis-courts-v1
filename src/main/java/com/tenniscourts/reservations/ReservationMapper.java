package com.tenniscourts.reservations;


public interface ReservationMapper {

    Reservation map(ReservationDTO source);

    ReservationDTO map(Reservation source);

    Reservation map(CreateReservationRequestDTO source);
}
