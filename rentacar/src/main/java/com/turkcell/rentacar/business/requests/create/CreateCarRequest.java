package com.turkcell.rentacar.business.requests.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	@NotNull
	@Min(100)
	private int dailyPrice;

	@Min(2000)
	@NotNull
	private int modelYear;

	@NotNull
	@Size(min = 1, max = 50)
	private String description;

	@Min(1)
	@NotNull
	private int brandId;

	@Min(1)
	@NotNull
	private int colorId;

}
