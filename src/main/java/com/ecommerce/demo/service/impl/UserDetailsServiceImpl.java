package com.ecommerce.demo.service.impl;

import com.ecommerce.demo.entity.User;
import com.ecommerce.demo.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(username);
            if(user == null){
                throw  new UsernameNotFoundException("No user credantial match");
            }
        return user;
    }
}
