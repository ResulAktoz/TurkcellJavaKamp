package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.UserService;
import com.turkcell.rentacar.business.constants.messages.BusinessMessages;
import com.turkcell.rentacar.business.dtos.getDto.GetUserDto;
import com.turkcell.rentacar.business.dtos.listDto.UserListDto;
import com.turkcell.rentacar.business.requests.update.UpdateUserRequest;
import com.turkcell.rentacar.core.utilities.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.UserDao;
import com.turkcell.rentacar.entities.abstracts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {

    private UserDao userDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public UserManager(UserDao userDao, ModelMapperService modelMapperService) {
        this.userDao = userDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<UserListDto>> getAll() throws BusinessException {
        List<User> result = this.userDao.findAll();

        List<UserListDto>  response = result.stream()
                .map(user -> this.modelMapperService.forDto()
                        .map(user, UserListDto.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<UserListDto>>(response, BusinessMessages.USERS_LISTED_SUCCESSFULLY);

    }

    @Override
    public Result update(UpdateUserRequest updateUserRequest) {
        checkIfUserExist(updateUserRequest.getUserId());

        User user = this.modelMapperService.forRequest()
                .map(updateUserRequest, User.class);

        this.userDao.save(user);

        return new SuccessResult(BusinessMessages.USER_UPDATED_SUCCESSFULLY);

    }


    private void checkIfUserExist(int userId){
       if(!this.userDao.existsById(userId)){
           throw new BusinessException(BusinessMessages.USER_NOT_FOUND);
       }
    }
}
