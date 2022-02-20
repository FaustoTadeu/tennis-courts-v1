package com.tenniscourts.schedules;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.tenniscourts.TennisCourt;
import com.tenniscourts.tenniscourts.TennisCourtRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ScheduleService {

	@Autowired
    private ScheduleRepository scheduleRepository;
    
	@Autowired
    private TennisCourtRepository tennisCourtRepository;

	@Autowired
    private final ScheduleMapperImpl scheduleMapper;

    
    /**
	  * Method used to add a schedule
	  *
	  * @param Long tennisCourtId
	  * @param CreateScheduleRequestDTO createScheduleRequestDTO
	  * @return ReservationDTO
	  */
    public Schedule addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
       Optional<TennisCourt> tennisCourt = tennisCourtRepository.findById(tennisCourtId);
       if(tennisCourt.isPresent()) {
    	   return scheduleRepository.save(
    			    		  Schedule.builder()
    	                      .startDateTime(createScheduleRequestDTO.getStartDateTime())
    	                      .endDateTime(createScheduleRequestDTO.getStartDateTime().plusHours(1))
    	                      .tennisCourt(tennisCourt.get())
    	   .build());    
    	   
       } else {
    	   log.error("Tennis court not found on save schedule. CreateScheduleRequestDTO: " + createScheduleRequestDTO);
		   throw new EntityNotFoundException("Tennis court not found on save schedule. CreateScheduleRequestDTO: " + createScheduleRequestDTO);
       }
    }

    /**
	  * Method used to find a schedule by dates.
	  *
	  * @param LocalDateTime startDate
	  * @param LocalDateTime endDate
	  * @return List ScheduleDTO 
	  */
    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        return scheduleMapper.map(scheduleRepository.findAllByStartDateTimeBetween(startDate, endDate));
    }

    /**
	  * Method used to find a schedule by id
	  *
	  * @param Long scheduleId
	  * @return ReservationDTO
	  */
    public ScheduleDTO findScheduleById(Long scheduleId) {
    	 return scheduleRepository.findById(scheduleId).map(scheduleMapper::mapFindById).orElseThrow(() -> {
	         throw new EntityNotFoundException("Schedule not found. scheduleId: " + scheduleId);
	     });
    }

    /**
	  * Method used to find a schedule by tennis court 
	  *
	  * @param Long tennisCourtId
	  * @return List ScheduleDTO
	  */
    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
}
