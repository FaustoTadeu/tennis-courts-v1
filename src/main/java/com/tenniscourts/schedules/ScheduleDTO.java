package com.tenniscourts.schedules;

import com.tenniscourts.tenniscourts.TennisCourtDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class ScheduleDTO {

    private Long id;

    private TennisCourtDTO tennisCourt;
    
    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

}
