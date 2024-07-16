package com.ecommerce.demo.service;

import com.ecommerce.demo.entity.User;
import com.ecommerce.demo.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUser(String username);

    public boolean deleteUserById(Long userId);

    public User updateUserById(Long userId, User updatedUser);
}
