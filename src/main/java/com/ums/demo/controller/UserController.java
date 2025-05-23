package com.ums.demo.controller;

import com.ums.demo.entity.User;
import com.ums.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public Page<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return userService.getAllUsers(page, size, sortBy, direction);
    }

    @GetMapping("/getUserByEmail")
    public User getUserByEmail(@RequestParam String email){
        return  userService.getUsersByEmail(email);
    }
    @GetMapping("/getUserByFirstName")
    public List<User> getUsersByFirstName(@RequestParam String firstName){
        return userService.getAllUserByFirstName(firstName);
    }
    @GetMapping("/example-domain-users")
    public List<User> getUsersWithExampleDomain() {
        return userService.getUsersWithExampleDomain();
    }


}
