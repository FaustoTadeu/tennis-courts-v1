package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
@AllArgsConstructor
@Api(tags= {"Reservation API"})
public class ReservationController extends BaseRestController {

	@Autowired
    private final ReservationService reservationService;

    @PostMapping
    @ApiOperation(value = "Create new Reservation.", notes = "Create new Reservation.")
    public ResponseEntity<Void> bookReservation(@ApiParam(required = true, name = "CreateReservationRequestDTO") CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @GetMapping("/id/{reservationId}")
    @ApiOperation(value = "Find reservation by id.", notes = "Find reservation by id.")
    public ResponseEntity<ReservationDTO> findReservationById(@ApiParam(required = true, value = "Id of reservation to be found.") @PathVariable("reservationId") Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @DeleteMapping("/cancel/{reservationId}")
    @ApiOperation(value = "Cancel Reservation.", notes = "Cancel Reservation.")
    public ResponseEntity<ReservationDTO> cancelReservation(@ApiParam(required = true, value = "Id of Reservation to be cancelled.") @PathVariable("reservationId") Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @PutMapping("reschedule/{reservationId}/{scheduleId}")
    @ApiOperation(value = "Reschedule Reservation.", notes = "Reschedule Reservation.")
    public ResponseEntity<ReservationDTO> rescheduleReservation(@ApiParam(required = true, value = "Id of Reservation to be rescheduled.") @PathVariable("reservationId") Long reservationId, 
    		@ApiParam(required = true, value = "Id of schedule to be reschedule.") @PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
