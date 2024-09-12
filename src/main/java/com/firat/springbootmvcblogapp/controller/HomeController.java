package com.firat.springbootmvcblogapp.controller;

import com.firat.springbootmvcblogapp.model.TestUser;
import com.firat.springbootmvcblogapp.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private TestUserService testUserService;

    //localhost:8080/home/user
    @GetMapping("/users")
    public List<TestUser> getUser(){
        System.out.println("getting users!");
        return testUserService.getUsers();
    }


    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();

    }
}
