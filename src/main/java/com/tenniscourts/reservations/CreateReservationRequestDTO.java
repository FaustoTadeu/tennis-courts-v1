package com.tenniscourts.reservations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@ApiModel(value = "CreateReservationRequestDTO", description = "Object with reservation data.")
public class CreateReservationRequestDTO {

	@NotNull(message = "Guest id cannot be null")
	@ApiModelProperty(value = "Id of guest to create a reservation. Cannot be null", required = true)
    private Long guestId;

	@NotNull(message = "Id of schedule cannot be null")
	@ApiModelProperty(value = "Id of schedule to create a reservation. Cannot be null", required = true)
    private Long scheduleId;

}
