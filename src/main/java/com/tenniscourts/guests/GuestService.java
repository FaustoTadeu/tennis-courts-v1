package com.tenniscourts.guests;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenniscourts.exceptions.EntityNotFoundException;
import com.tenniscourts.guests.DTO.CreateGuestRequestDTO;
import com.tenniscourts.guests.DTO.GuestDTO;
import com.tenniscourts.guests.DTO.UpdateGuestRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuestService {
	
	@Autowired
	private GuestMapperImpl guestMapper;
	
	@Autowired
	private GuestRepository guestRepository;

	 public Guest addGuest(CreateGuestRequestDTO createGuestRequestDTO) {
		   return guestRepository.save(guestMapper.map(createGuestRequestDTO));
	 }
	 
	 public GuestDTO updateGuest(UpdateGuestRequestDTO updateGuestRequestDTO) {
		 Optional<Guest> guestUpdate = guestRepository.findById(updateGuestRequestDTO.getGuestId());
		 if(guestUpdate.isPresent()) {
			 guestUpdate.get().setName(updateGuestRequestDTO.getGuestName());
			 guestUpdate.get().setUserUpdate(updateGuestRequestDTO.getUserId());
		     return guestMapper.map(guestRepository.save(guestUpdate.get()));
		 } else {
			 log.error("Guest not found. GuestId: " + updateGuestRequestDTO.getGuestId());
			 throw new EntityNotFoundException("Guest not found.");
		 }
	 }
	 
	 public void deleteGuest(Long guestId) {
		 Optional<Guest> guestDelete = guestRepository.findById(guestId);
		 if(guestDelete.isPresent()) {
		     guestRepository.delete(guestDelete.get());
		 } else {
			 log.error("Guest not found. GuestId: " + guestId);
			 throw new EntityNotFoundException("Guest not found.");
		 }
	 }
	 
	 public GuestDTO findGuestById(Long guestId) {
	     return guestRepository.findById(guestId).map(guestMapper::map).orElseThrow(() -> {
	         throw new EntityNotFoundException("Guest not found.");
	     });
	 }
	 
	 public GuestDTO findGuestByName(String name) {
	     return guestRepository.findByName(name).map(guestMapper::map).orElseThrow(() -> {
	         throw new EntityNotFoundException("Guest not found.");
	     });
	 }
	 
	 public List<GuestDTO> findAllGuests() {
	     return guestMapper.map(guestRepository.findAll());
	 }
}
