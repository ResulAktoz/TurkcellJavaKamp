package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetUserDto;
import com.turkcell.rentacar.business.dtos.listDto.UserListDto;
import com.turkcell.rentacar.business.requests.update.UpdateUserRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import java.util.List;

public interface UserService {

    DataResult<List<UserListDto>> getAll();
    Result update(UpdateUserRequest updateUserRequest);

}
