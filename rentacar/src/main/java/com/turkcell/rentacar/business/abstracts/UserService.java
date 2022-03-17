package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.getDto.GetUserDto;
import com.turkcell.rentacar.business.dtos.listDto.UserListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.results.DataResult;

import java.util.List;

public interface UserService {

    DataResult<List<UserListDto>> getAll() throws BusinessException;
    DataResult<GetUserDto> getByUserId(int userId) throws BusinessException;

}
