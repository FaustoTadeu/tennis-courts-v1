package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import com.tenniscourts.guests.DTO.CreateGuestRequestDTO;
import com.tenniscourts.guests.DTO.GuestDTO;
import com.tenniscourts.guests.DTO.UpdateGuestRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guest")
@AllArgsConstructor
@Api(tags= {"Guest"})
public class GuestController extends BaseRestController {

	@Autowired
    private final GuestService guestService;

    @PostMapping
    @ApiOperation(value = "Create new Guest.", notes = "Create new Guest.")
    public ResponseEntity<Void> createGuest(@ApiParam(required = true, name = "CreateGuestRequestDTO") CreateGuestRequestDTO createGuestRequestDTO) {
        return ResponseEntity.created(locationByEntity(guestService.addGuest(createGuestRequestDTO).getId())).build();
    }
    
    @PutMapping
    @ApiOperation(value = "Update existing Guest.", notes = "Update existing Guest.")
    public ResponseEntity<GuestDTO> updateGuest(@ApiParam(required = true, name = "UpdateGuestRequestDTO") UpdateGuestRequestDTO updateGuestRequestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(updateGuestRequestDTO));
    }
    
    @DeleteMapping("/{guestId}")
    @ApiOperation(value = "Create new Guest.", notes = "Create new Guest.")
    public ResponseEntity<String> deleteGuest(@ApiParam(required = true, value ="Guest id to be deleted.") @PathVariable("guestId") Long guestId ) {
        guestService.deleteGuest(guestId);
    	return ResponseEntity.ok("Guest successfully deleted");
    }
    
    @GetMapping
    @ApiOperation(value = "Find all guests.", notes = "Find all guests.")
    public ResponseEntity<List<GuestDTO>> findAllGuests() {
        return ResponseEntity.ok(guestService.findAllGuests());
    }

    @GetMapping("/id/{guestId}")
    @ApiOperation(value = "Find guest by id.", notes = "Find guest by id.")
    public ResponseEntity<GuestDTO> findGuestById(@ApiParam(required = true, value ="Guest id to be find.") @PathVariable("guestId") Long guestId) {
        return ResponseEntity.ok(guestService.findGuestById(guestId));
    }
    
    @GetMapping("/name/{guestName}")
    @ApiOperation(value = "Find guest by name.", notes = "Find guest by name.")
    public ResponseEntity<GuestDTO> findGuestByName(@ApiParam(required = true, value ="Guest name to be find.") @PathVariable("guestName") String guestName) {
        return ResponseEntity.ok(guestService.findGuestByName(guestName));
    }
}
