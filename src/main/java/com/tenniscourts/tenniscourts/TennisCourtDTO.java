package com.tenniscourts.tenniscourts;

import com.tenniscourts.schedules.ScheduleDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TennisCourtDTO", description = "Object with tennis court data.")
public class TennisCourtDTO {

	@ApiModelProperty(value = "Id of tennis court.")
    private Long id;

    @NotNull
    @ApiModelProperty(value = "Name of tennis court.")
    private String name;

    @ApiModelProperty(value = "List of schedules related to the tennis court.")
    private List<ScheduleDTO> tennisCourtSchedules;

}
