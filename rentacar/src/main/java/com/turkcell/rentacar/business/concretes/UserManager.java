package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.UserService;
import com.turkcell.rentacar.business.dtos.getDto.GetUserDto;
import com.turkcell.rentacar.business.dtos.listDto.UserListDto;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.mapping.ModelMapperService;
import com.turkcell.rentacar.core.results.DataResult;
import com.turkcell.rentacar.core.results.SuccessDataResult;
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

        return new SuccessDataResult<List<UserListDto>>(response, "Kullanıcılar başarıyla listelendi.");



    }

    @Override
    public DataResult<GetUserDto> getByUserId(int userId) throws BusinessException {
        checkIfUserExist(userId);

        User user = this.userDao.getById(userId);

        GetUserDto response = this.modelMapperService.forDto().map(user, GetUserDto.class);

        return new SuccessDataResult<GetUserDto>(response, "İd'ye göre listeleme başarılı.");

    }

    private void checkIfUserExist(int userId){
       if(!this.userDao.existsById(userId)){
           throw new BusinessException("Bu id'de kullanıcı bulunamadı");
       }
    }
}
