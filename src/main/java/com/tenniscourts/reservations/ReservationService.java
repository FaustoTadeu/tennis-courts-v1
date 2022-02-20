package com.tenniscourts.reservations;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.guests.Guest;
import com.tenniscourts.guests.GuestRepository;
import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.schedules.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationService {

	@Autowired
    private ReservationRepository reservationRepository;
	
	@Autowired
    private GuestRepository guestRepository;
	
	@Autowired
    private ScheduleRepository scheduleRepository;

	@Autowired
    private ReservationMapperImpl reservationMapper;
	
	private static final BigDecimal VALUE = BigDecimal.valueOf(10);
	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	
	/**
	  * Method used to book a Reservation
	  *
	  * @param CreateReservationRequestDTO createReservationRequestDTO
	  * @return ReservationDTO
	  */
    public ReservationDTO bookReservation(CreateReservationRequestDTO createReservationRequestDTO) {
    	Optional<Guest> guest = guestRepository.findById(createReservationRequestDTO.getGuestId());
    	if(guest.isPresent()) {
    		Optional<Schedule> schedule = scheduleRepository.findById(createReservationRequestDTO.getScheduleId());
    		if(schedule.isPresent()) {
    			return reservationMapper.map(reservationRepository.save(Reservation.builder()
    				       .guest(guest.get())
    				       .schedule(schedule.get())
    				       .reservationStatus(ReservationStatus.READY_TO_PLAY)
    				       .value(VALUE)
    					   .build()));
    		} else {
    			throw new EntityNotFoundException("Schedule not found when create a reservation. CreateReservationRequestDTO: " + createReservationRequestDTO);
    		}
    	} else { 
    		throw new EntityNotFoundException("Guest not found when create a reservation. CreateReservationRequestDTO: " + createReservationRequestDTO);
    	}
    }

    /**
	  * Method used to find a Reservation by Id
	  *
	  * @param Long reservationId
	  * @return ReservationDTO
	  */
    public ReservationDTO findReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservationMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Reservation not found.");
        });
    }

    /**
	  * Method used to cancel a Reservation
	  *
	  * @param Long reservationId
	  * @return ReservationDTO
	  */
    public ReservationDTO cancelReservation(Long reservationId) {
        return reservationMapper.map(this.cancel(reservationId));
    }

    /**
	  * Method used to cancel a Reservation
	  *
	  * @param Long reservationId
	  * @return ReservationDTO
	  */
    private Reservation cancel(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {

            this.validateCancellation(reservation);

            BigDecimal refundValue = getRefundValue(reservation);
            return this.updateReservation(reservation, refundValue, ReservationStatus.CANCELLED);

        }).orElseThrow(() -> {
            throw new EntityNotFoundException("Reservation not found.");
        });
    }

    /**
	  * Method used to cancel a Reservation
	  *
	  * @param Reservation reservation
	  * @param BigDecimal refundValue
	  * @param ReservationStatus status
	  * @return Reservation
	  */
    private Reservation updateReservation(Reservation reservation, BigDecimal refundValue, ReservationStatus status) {
        reservation.setReservationStatus(status);
        reservation.setValue(reservation.getValue().subtract(refundValue));
        reservation.setRefundValue(refundValue);

        return reservationRepository.save(reservation);
    }

    /**
	  * Method used to validate a Reservation to cancel
	  *
	  * @param Reservation reservation
	  */
    private void validateCancellation(Reservation reservation) {
        if (!ReservationStatus.READY_TO_PLAY.equals(reservation.getReservationStatus())) {
            throw new IllegalArgumentException("Cannot cancel/reschedule because it's not in ready to play status.");
        }

        if (reservation.getSchedule().getStartDateTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Can cancel/reschedule only future dates.");
        }
    }


    /**
	  * Method used to cancel a Reservation
	  *
	  * @param Reservation reservation
	  * @return BigDecimal
	  */
    public BigDecimal getRefundValue(Reservation reservation) {
        long hours = ChronoUnit.HOURS.between(LocalDateTime.now(), reservation.getSchedule().getStartDateTime());

        if (hours >= 24) {
            return reservation.getValue();
        } else if (hours < 24 && hours >= 12) {
        	return reservation.getValue().multiply(BigDecimal.valueOf(75)).divide(ONE_HUNDRED);
        } else if(hours < 12 && hours >= 2) {
        	return reservation.getValue().multiply(BigDecimal.valueOf(50)).divide(ONE_HUNDRED);
        } else if (hours < 2 && hours >= 1) {
        	return reservation.getValue().multiply(BigDecimal.valueOf(25)).divide(ONE_HUNDRED);
        }
        return BigDecimal.ZERO;
    }

    /**
	  * Method used to reschedule a Reservation
	  *
	  * @param Long previousReservationId
	  * @param Long scheduleId
	  * @return ReservationDTO
	  */
    public ReservationDTO rescheduleReservation(Long previousReservationId, Long scheduleId) {

    	Optional<Reservation> previousReservation = reservationRepository.findById(previousReservationId);
    	if(previousReservation.isPresent()) {
            if (scheduleId.equals(previousReservation.get().getSchedule().getId())) {
                throw new IllegalArgumentException("Cannot reschedule to the same slot.");
            }
    	}
    	this.validateCancellation(previousReservation.get());

        BigDecimal refundValue = getRefundValue(previousReservation.get());
        updateReservation(previousReservation.get(), refundValue, ReservationStatus.RESCHEDULED);

        ReservationDTO newReservation = bookReservation(CreateReservationRequestDTO.builder()
                .guestId(previousReservation.get().getGuest().getId())
                .scheduleId(scheduleId)
                .build());
        newReservation.setPreviousReservation(reservationMapper.map(previousReservation.get()));
        return newReservation;
    }
}
