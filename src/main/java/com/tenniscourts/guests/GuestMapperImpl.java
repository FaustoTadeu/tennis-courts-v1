package com.tenniscourts.guests;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.tenniscourts.guests.DTO.CreateGuestRequestDTO;
import com.tenniscourts.guests.DTO.GuestDTO;
import com.tenniscourts.guests.DTO.UpdateGuestRequestDTO;


@Component
public class GuestMapperImpl implements GuestMapper {

	@Override
	public List<GuestDTO> map(List<Guest> guests) {
		return guests.stream().map(this::map).collect(Collectors.toList());
	}
	
	@Override
	public Guest map(CreateGuestRequestDTO createGuestRequestDTO) {
		return Guest.builder()
		.name(createGuestRequestDTO.getGuestName())
		.build();
	}
	
	@Override
	public GuestDTO map(Guest guest) {
		return GuestDTO.builder()
		.id(guest.getId())
		.name(guest.getName())
		.build();
	}
	
	@Override
	public Guest map(UpdateGuestRequestDTO updateGuestRequestDTO) {
		return Guest.builder()
		.name(updateGuestRequestDTO.getGuestName())
		.build();
	}
}
