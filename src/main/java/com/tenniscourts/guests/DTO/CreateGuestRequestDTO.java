package com.tenniscourts.guests.DTO;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "CreateGuestRequestDTO", description = "Object with guest data.")
public class CreateGuestRequestDTO {

	
	 @NotNull(message = "Guest name cannot be null")
	 @ApiModelProperty(value = "Name to create a guest. Cannot be null", required = true)
	 private String guestName;

	 @NotNull
	 @ApiModelProperty(value = "User that is creating a guest. Cannot be null", required = true)
	 private Long userId;
}
