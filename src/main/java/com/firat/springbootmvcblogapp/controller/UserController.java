package com.firat.springbootmvcblogapp.controller;

import com.firat.springbootmvcblogapp.dto.ApiResponse;
import com.firat.springbootmvcblogapp.dto.UserDto;
import com.firat.springbootmvcblogapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    //Post-> create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto createUserDto = userService.createUserr(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    //Put-> update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){

        UserDto updatedUser = userService.updateUser(userDto,uid);
        return ResponseEntity.ok(updatedUser);
    }

    //Delete-> delete user


    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Selected User Deleted!", true), HttpStatus.OK);
    }

    //Get-> get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {

        return ResponseEntity.ok(userService.getUserById(userId));
    }

    //Get-> get user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }




}
