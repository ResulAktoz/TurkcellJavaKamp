package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.UserService;
import com.turkcell.rentacar.business.dtos.listDto.UserListDto;
import com.turkcell.rentacar.business.requests.update.UpdateUserRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getAll")
    public DataResult<List<UserListDto>> getAll(){
        return this.userService.getAll();
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return this.userService.update(updateUserRequest);
    }


}
