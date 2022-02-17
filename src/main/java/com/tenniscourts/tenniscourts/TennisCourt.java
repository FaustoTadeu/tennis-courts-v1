package com.tenniscourts.tenniscourts;

import com.tenniscourts.config.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
@EqualsAndHashCode(callSuper = true)
@ToString
@SuperBuilder
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TennisCourt extends BaseEntity<Long> {

   
	private static final long serialVersionUID = 1L;
	
	@Column
    @NotNull
    private String name;
}
