package com.ecommerce.demo.controller;

import com.ecommerce.demo.entity.Role;
import com.ecommerce.demo.entity.User;
import com.ecommerce.demo.entity.UserRole;
import com.ecommerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {


    @Autowired
    private UserService userService1;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleId(45L);
        role.setRoleName("NORMAL");
//        roles.add();

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return userService1.createUser(user, roles);
//        return user;
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
      return userService1.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUserById(@PathVariable("userId") Long userId) {
        return this.userService1.deleteUserById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUserById(@PathVariable("userId") Long userId, @RequestBody User updatedUser){
       return  this.userService1.updateUserById(userId, updatedUser);
    }

}
