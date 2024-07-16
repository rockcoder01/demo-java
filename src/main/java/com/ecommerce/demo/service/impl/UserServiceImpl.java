package com.ecommerce.demo.service.impl;

import com.ecommerce.demo.entity.User;
import com.ecommerce.demo.entity.UserRole;
import com.ecommerce.demo.repo.RoleRepository;
import com.ecommerce.demo.repo.UserRepository;
import com.ecommerce.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUserName(user.getUserName());
        if (local != null) {
            throw new Exception("User Already Exist");
        } else {
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);

        }

        return local;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUserName(username);
    }

    @Override
    public boolean deleteUserById(Long userId){
         this.userRepository.deleteById(userId);
        return true;
    }

    @Override
    public User updateUserById(Long userId, User updatedUser) {
        User existingUser = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Update fields
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setUserName(updatedUser.getUserName());
        existingUser.setPhone(updatedUser.getPhone());
        // ... update other fields as needed

        // Update user roles (if necessary)
        if (updatedUser.getUserRoles() != null) {
            existingUser.getUserRoles().clear();
            existingUser.getUserRoles().addAll(updatedUser.getUserRoles());
        }

        // Save the changes
        return this.userRepository.save(existingUser);
    }
}
