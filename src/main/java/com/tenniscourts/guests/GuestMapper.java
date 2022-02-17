package com.tenniscourts.guests;

import java.util.List;

import com.tenniscourts.guests.DTO.CreateGuestRequestDTO;
import com.tenniscourts.guests.DTO.GuestDTO;
import com.tenniscourts.guests.DTO.UpdateGuestRequestDTO;

public interface GuestMapper {
	
	Guest map(CreateGuestRequestDTO createGuestRequestDTO);
	
	public GuestDTO map(Guest guest);
	
	public Guest map(UpdateGuestRequestDTO updateGuestRequestDTO);

	public List<GuestDTO> map(List<Guest> guests);
}
