package com.tenniscourts.tenniscourts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tenniscourts.schedules.Schedule;
import com.tenniscourts.schedules.ScheduleDTO;
import com.tenniscourts.schedules.ScheduleRepository;

@Component
public class TennisCourtMapperImpl implements TennisCourtMapper {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	@Override
	public TennisCourtDTO map(TennisCourt tennisCourt) {
		
		List<Schedule> schedules = scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourt.getId());
		List<ScheduleDTO> schedulesDTO = new ArrayList<>();
		
	   schedules.forEach(sc -> {
		   schedulesDTO.add(
		   	   ScheduleDTO.builder()
			   .id(sc.getId())
			   .tennisCourt(TennisCourtDTO.builder()
			       .id(tennisCourt.getId())
				   .name(tennisCourt.getName())
				   .build()
			    ).build()
		    );
	   });
		return TennisCourtDTO.builder()
		.name(tennisCourt.getName())
		.tennisCourtSchedules(schedulesDTO)
		.build();
	}

	@Override
	public TennisCourt map(TennisCourtDTO source) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
