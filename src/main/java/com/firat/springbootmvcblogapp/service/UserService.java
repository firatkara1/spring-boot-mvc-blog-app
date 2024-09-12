package com.firat.springbootmvcblogapp.service;

import com.firat.springbootmvcblogapp.dto.UserDto;
import com.firat.springbootmvcblogapp.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    UserDto createUserr(UserDto userDto);

    //User createUser(User user);
    UserDto registerNewUser(UserDto user);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);

}
