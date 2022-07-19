package com.job.security;

import com.job.modal.UserEntity;
import com.job.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepo.findByUsername(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("no user found with username"+username);
        }

        return new UserPrincipal(user.getUserId(),user.getUsername(),user.getPassword(),user.getRole());
    }

    public UserDetails loadUserById(long userId) throws UsernameNotFoundException {
        UserEntity user= userRepo.findById(userId).get();
        if(user==null)
        {
            throw new UsernameNotFoundException("no user found with id"+userId);
        }

        return new UserPrincipal(user.getUserId(),user.getUsername(),user.getPassword(),user.getRole());
    }
}
