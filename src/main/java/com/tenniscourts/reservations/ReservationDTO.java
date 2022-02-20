package com.tenniscourts.reservations;

import com.tenniscourts.guests.DTO.GuestDTO;
import com.tenniscourts.schedules.ScheduleDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@ApiModel(value = "ReservationDTO", description = "Object with reservation data.")
public class ReservationDTO {

	@ApiModelProperty(value = "Id of reservation.")
    private Long id;

	@ApiModelProperty(value = "Schedule related to reservation.")
    private ScheduleDTO schedule;
    
	@ApiModelProperty(value = "Guest related to reservation.")
    private GuestDTO guest;

	@ApiModelProperty(value = "Status of a reservation.")
    private String reservationStatus;

	@ApiModelProperty(value = "Previous reservation in case of reschedule.")
    private ReservationDTO previousReservation;

	@ApiModelProperty(value = "Refund value of a reservation.")
    private BigDecimal refundValue;

	@ApiModelProperty(value = "Value of fee deposit.")
    private BigDecimal value;

    @NotNull
    @ApiModelProperty(value = "Schedule id related to the reservation.", required = true)
    private Long scheduledId;

    @NotNull
    @ApiModelProperty(value = "Guest id related to the guest.", required = true)
    private Long guestId;
}
