package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.ws.rs.QueryParam;

@Controller
@RequestMapping("/schedule")
@AllArgsConstructor
@Api(tags= {"Schedule API"})
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ApiOperation(value = "Create new Schedule.", notes = "Create new Schedule.")
    public ResponseEntity<Void> addScheduleTennisCourt (@ApiParam(required = true, name = "CreateScheduleRequestDTO") CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @GetMapping("/date")
    @ApiOperation(value = "Find schedule by start and end dates.", notes = "Find schedule by start and end dates.")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@ApiParam(required = true, value ="Start date to find schedules.") @QueryParam("startDate") LocalDate startDate, 
    		@ApiParam(required = true, value ="End date to find schedules.") @QueryParam("endDate") LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @GetMapping("/id/{scheduleId}")
    @ApiOperation(value = "Find schedule by id.", notes = "Find schedule by id.")
    public ResponseEntity<ScheduleDTO> findByScheduleId(@ApiParam(required = true, value ="Id to find the schedule.") @PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findScheduleById(scheduleId));
    }
}
