package com.turkcell.rentacar.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {
	@NotNull
	@Min(1)
	private int id;

	@NotNull
	@Min(100)
	private int dailyPrice;

	@Min(2000)
	@NotNull
	private int modelYear;

	@NotNull
	private String description;

	@NotNull
	private String brandName;

	@NotNull
	private String colorName;

}
