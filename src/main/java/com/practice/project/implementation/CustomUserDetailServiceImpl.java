package com.practice.project.implementation;

import com.practice.project.entity.CustomUserDetail;
import com.practice.project.entity.User;
import com.practice.project.dao.UserRepo;
import com.practice.project.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailServiceImpl implements CustomUserDetailService {
    private UserRepo userRepo;
    @Autowired
    public CustomUserDetailServiceImpl(UserRepo theUserRepo) {
        userRepo = theUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByEmail(email);
        user.orElseThrow(()-> new UsernameNotFoundException("User not found!"));

        return user.map(CustomUserDetail::new).get();
    }
}
