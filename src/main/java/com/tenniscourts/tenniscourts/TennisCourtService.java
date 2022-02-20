package com.tenniscourts.tenniscourts;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TennisCourtService {

	@Autowired
    private TennisCourtRepository tennisCourtRepository;

	@Autowired
    private TennisCourtMapperImpl tennisCourtMapper;

	/**
	  * Method used to add a tennis court
	  *
	  * @param TennisCourtDTO tennisCourtDTO
	  * @return TennisCourtDTO
	  */
    public TennisCourtDTO addTennisCourt(TennisCourtDTO tennisCourt) {
        return tennisCourtMapper.map(tennisCourtRepository.save(tennisCourtMapper.map(tennisCourt)));
    }

    /**
	  * Method used to find tennis court by id.
	  *
	  * @param Long id
	  * @return TennisCourtDTO
	  */
    public TennisCourtDTO findTennisCourtById(Long id) {
        return tennisCourtRepository.findById(id).map(tennisCourtMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Tennis Court not found.");
        });
    }

    /**
	  * Method used to find tennis court and related schedules by id.
	  *
	  * @param Long id
	  * @return TennisCourtDTO
	  */
    public TennisCourtDTO findTennisCourtWithSchedulesById(Long id) {
    	 return tennisCourtRepository.findById(id).map(tennisCourtMapper::mapWithSchedule).orElseThrow(() -> {
             throw new EntityNotFoundException("Tennis Court not found.");
         });
    }
}
