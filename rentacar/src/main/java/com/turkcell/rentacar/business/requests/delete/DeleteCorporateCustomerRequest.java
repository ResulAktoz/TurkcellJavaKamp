package com.turkcell.rentacar.business.requests.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCorporateCustomerRequest {

    @NotNull
    @Min(1)
    private int userId;
}
