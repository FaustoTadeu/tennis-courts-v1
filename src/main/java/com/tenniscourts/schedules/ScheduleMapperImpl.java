package com.tenniscourts.schedules;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tenniscourts.tenniscourts.TennisCourtDTO;
import com.tenniscourts.tenniscourts.TennisCourtMapperImpl;

@Component
public class ScheduleMapperImpl implements ScheduleMapper {
	
	@Autowired
	TennisCourtMapperImpl tennisCourtMapper;

	@Override
	public Schedule map(ScheduleDTO source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScheduleDTO mapFindById(Schedule schedule) {
		TennisCourtDTO.builder()
		.id(schedule.getTennisCourt().getId())
		.name(schedule.getTennisCourt().getName())
		.build();
		
		return ScheduleDTO.builder()
				.id(schedule.getId())
				.tennisCourt(TennisCourtDTO.builder()
						.id(schedule.getTennisCourt().getId())
						.name(schedule.getTennisCourt().getName())
						.build())
				.startDateTime(schedule.getStartDateTime())
				.endDateTime(schedule.getEndDateTime())
				.build();
	}

	@Override
	public List<ScheduleDTO> map(List<Schedule> schedules) {
		return schedules.stream().map(this::map).collect(Collectors.toList());
	}

	@Override
	public ScheduleDTO map(Schedule source) {
		// TODO Auto-generated method stub
		return null;
	}
}
