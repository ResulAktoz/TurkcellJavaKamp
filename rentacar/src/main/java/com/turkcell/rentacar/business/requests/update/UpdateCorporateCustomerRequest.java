package com.turkcell.rentacar.business.requests.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {

    @NotNull
    @Min(1)
    private int userId;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2)
    private String password;

    @NotNull
    @Size(min = 2)
    private String companyName;

    @NotNull
    @Size(min = 2)
    private String taxNumber;
}
