package com.tenniscourts.schedules;

import com.tenniscourts.tenniscourts.TennisCourtDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
@ApiModel(value = "ScheduleDTO", description = "Object with schedule data.")
public class ScheduleDTO {

	@ApiModelProperty(value = "Id of schedule.")
    private Long id;

	@ApiModelProperty(value = "Tennis court related to a schedule.")
    private TennisCourtDTO tennisCourt;
    
	@ApiModelProperty(value = "Start date of schedule.")
    private LocalDateTime startDateTime;

	@ApiModelProperty(value = "End date of schedule.")
    private LocalDateTime endDateTime;

}
