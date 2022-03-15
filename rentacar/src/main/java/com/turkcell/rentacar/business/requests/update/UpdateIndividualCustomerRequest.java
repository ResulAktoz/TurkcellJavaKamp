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
public class UpdateIndividualCustomerRequest {

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
    private String firstName;

    @NotNull
    @Size(min = 2)
    private String lastName;

    @NotNull
    @Size(min = 11, max = 11)
    private String nationalIdentity;
}
