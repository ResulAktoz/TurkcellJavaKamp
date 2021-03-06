package com.turkcell.rentacar.business.dtos.getDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarDto {
	

	private double dailyPrice;
	private int modelYear;
	private String kilometerInfo;
	private String description;
	private String brandName;
	private String colorName;

}
