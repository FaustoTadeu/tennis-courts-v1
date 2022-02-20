package com.tenniscourts.guests.DTO;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel(value = "UpdateGuestRequestDTO", description = "Object with updated guest data.")
public class UpdateGuestRequestDTO {
	
	 @NotNull(message = "Guest id cannot be null")
	 @ApiModelProperty(value = "Id of guest to be edited. Cannot be null", required = true)
	 private Long guestId;
	
	 @NotNull(message = "Guest name cannot be null")
	 @ApiModelProperty(value = "New name of guest to be edited. Cannot be null", required = true)
	 private String guestName;

	 @NotNull(message = "User id cannot be null")
	 @ApiModelProperty(value = "Id of user that is editing the guest. Cannot be null", required = true)
	 private Long userId;

}
