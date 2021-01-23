package com.upskillutoday.crmRoot.service;

import com.upskillutoday.crmRoot.dto.UserLoginDto;
import com.upskillutoday.crmRoot.request.AuthenticationRequest;
import com.upskillutoday.crmRoot.response.AuthenticationResponse;
import com.upskillutoday.crmRoot.response.UserLoginResponseDto;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.User;

public interface UserLoginService {
    UserLoginResponseDto loginuserService(UserLoginDto userLoginDto);
//    User getUserByUserName(String s);
    AuthenticationResponse getLoginDataFromName(String username);
//    ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;
}
