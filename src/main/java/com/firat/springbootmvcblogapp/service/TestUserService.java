package com.firat.springbootmvcblogapp.service;

import com.firat.springbootmvcblogapp.model.TestUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TestUserService {

    private List<TestUser> store = new ArrayList<>();

    public TestUserService(){
        store.add(new TestUser(UUID.randomUUID().toString(),"Firat Kara","firatkara@gmail.com"));
        store.add(new TestUser(UUID.randomUUID().toString(),"Umit Kara","umitkara@gmail.com"));
        store.add(new TestUser(UUID.randomUUID().toString(),"Samet Kara","Sametkara@gmail.com"));
    }

    public List<TestUser> getUsers(){
        return this.store;
    }














}
