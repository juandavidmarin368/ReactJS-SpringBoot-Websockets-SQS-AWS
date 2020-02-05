package com.websockets.WebSockets.Dao;

import java.util.ArrayList;
import java.util.List;

import com.websockets.WebSockets.model.User;

import org.springframework.stereotype.Repository;

@Repository
public class MainDao {

    List<User> users = new ArrayList<>();

    public List<User> getUsers(User payload) {

        users.add(payload);

        return users;

    }

}