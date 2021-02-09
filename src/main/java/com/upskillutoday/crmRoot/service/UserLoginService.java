package com.upskillutoday.crmRoot.service;

import com.upskillutoday.crmRoot.repository.JPARepository.UserMasterRepository;
import com.upskillutoday.crmRoot.request.AuthenticationRequest;
import com.upskillutoday.crmRoot.response.AuthenticationResponse;
import com.upskillutoday.crmRoot.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

public interface UserLoginService {
    User getUserByUserName(String s);
    ResponseEntity<?> createAuthenticationToken(AuthenticationRequest authenticationRequest) throws Exception;
}

@Service
@Transactional
class UserLoginServiceImpl implements UserLoginService{

    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public User getUserByUserName(String s) {
        return userMasterRepository.getUserByUserName(s);
    }

    public AuthenticationResponse getLoginDataFromName(String username) {
        return userMasterRepository.getLoginDataFromName(username);
    }

    @Override
    public ResponseEntity<?> createAuthenticationToken(
            AuthenticationRequest authenticationRequest
    ) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Incorrect Username or Password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        AuthenticationResponse otherData = this.getLoginDataFromName(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwt);
        authenticationResponse.setOthers(otherData.getEmployeeId(), otherData.getEmployeeName(), otherData.getRoleId(), otherData.getRoleName(), otherData.getUserId(), otherData.getContact(), otherData.getEmailId());
        return ResponseEntity.ok(authenticationResponse);
    }
}
