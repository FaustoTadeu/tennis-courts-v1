package com.tenniscourts.schedules;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ApiModel(value = "CreateScheduleRequestDTO", description = "Object with schedule data.")
public class CreateScheduleRequestDTO {

	@NotNull(message = "Tennis court id cannot be null")
	 @ApiModelProperty(value = "Id of a tennis court to create a schedule. Cannot be null", required = true)
    private Long tennisCourtId;

	@NotNull(message = "Start date time name cannot be null")
	 @ApiModelProperty(value = "Start date time to create a schedule. Cannot be null.", required = true)
    private LocalDateTime startDateTime;

}
