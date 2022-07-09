package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UserController {
    private UserDao userdao;

    public UserController(UserDao userdao) {
        this.userdao = userdao;
    }

    @RequestMapping(path = "/users/", method = RequestMethod.GET)
    public List<User> findAll() {
        return userdao.findAll();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public String getUserById(@PathVariable int id) {return userdao.getUserById(id);}

    @RequestMapping(path = "/user/{username}", method = RequestMethod.GET)
    public int findIdByUserName(@PathVariable String username) {
        return userdao.findIdByUsername(username);
    }
}
