package com.upskillutoday.crmRoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


public interface MyUserDetailsService extends UserDetailsService {
}

@Service
@Transactional
class MyUserDetailsServiceImpl implements MyUserDetailsService {

    @Autowired
    private UserLoginService userLoginService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userLoginService.getUserByUserName(s);
    }
}
