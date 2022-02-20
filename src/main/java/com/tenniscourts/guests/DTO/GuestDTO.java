package com.tenniscourts.guests.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel(value = "GuestDTO", description = "Object with guest data.")
public class GuestDTO {
	
	@ApiModelProperty(value = "Id of guest.")
	private Long id;
	
	@ApiModelProperty(value = "Name of guest.")
	private String name;

}
