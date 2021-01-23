package com.upskillutoday.crmRoot.service.impl;

import com.upskillutoday.crmRoot.repository.UserMasterRepository;
import com.upskillutoday.crmRoot.request.AuthenticationRequest;
import com.upskillutoday.crmRoot.response.AuthenticationResponse;
//import com.upskillutoday.crmRoot.service.MyUserDetailsService;
//import com.upskillutoday.crmRoot.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.UserLoginDto;
import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.repository.UserLoginDao;
import com.upskillutoday.crmRoot.response.UserLoginResponseDto;
import com.upskillutoday.crmRoot.service.UserLoginService;

@Service(value = "UserLoginService")
public class UserLoginServiceImpl implements UserLoginService{
	
	@Autowired
	private UserLoginDao userloginDao;

	@Autowired
    private UserMasterRepository userMasterRepository;

//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtUtils jwtUtils;



    @Override
    public UserLoginResponseDto loginuserService(UserLoginDto userLoginDto) {
        UserMaster user=new UserMaster();
        //user.setContactNo(userLoginDto.getContactNo());
        boolean flag = userloginDao.userLoginDao(user);
        UserLoginResponseDto userLoginResponseDto=null;
        if (flag) {
            if (userLoginDto.getContactNo() != null) {
                if (userLoginDto.getPass().equals(user.getPass())) {
                    if (user.getLoginStatus().equalsIgnoreCase("Active")) {
                        System.out.println("Verified ...");
                        userLoginDto.setLoginMsg("Login Successfully..!!");
                        userLoginDto.setFlag(true);
                    } else {
                        //System.out.println("kmdklcmkldmklcj");
                        userLoginDto.setLoginMsg("Your account is Block or Inactive");
                        //userMasterDto.setLoginStatus(false);
                        userLoginDto.setFlag(false);
                    }
                } else {
                    userLoginDto.setLoginMsg("Invalid Password....");
                    //userMasterDto.setLoginStatus(false);
                    userLoginDto.setFlag(false);
                }

            } else {
                //System.out.println("kmdklcmkldmklcj");
                userLoginDto.setLoginMsg("Invaild MobNo or password!!...");
                System.out.println("Masg::::::::" + userLoginDto.getLoginMsg());
                // userMasterDto.setLoginStatus(false);
                userLoginDto.setFlag(false);
            }

        } else {
            //System.out.println("kmdklcmkldmklcj");
            userLoginDto.setLoginMsg("Invaild mobNo or password!!...");
            System.out.println("Masg::::::::" + userLoginDto.getLoginMsg());
            //userMasterDto.setLoginStatus(false);
            userLoginDto.setFlag(false);
        }
        return userLoginResponseDto;
    }

//    @Override
//    public User getUserByUserName(String s) {
//        return userMasterRepository.getUserByUserName(s);
//    }

    @Override
    public AuthenticationResponse getLoginDataFromName(String username) {
        return userMasterRepository.getLoginDataFromName(username);
    }
//
//    @Override
//    public ResponseEntity<?> createAuthenticationToken(
//            AuthenticationRequest authenticationRequest
//    ) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            authenticationRequest.getUsername(),
//                            authenticationRequest.getPassword())
//            );
//        } catch (Exception e) {
//            throw new Exception("Incorrect Username or Password", e);
//        }
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = jwtUtils.generateToken(userDetails);
//        AuthenticationResponse otherData = this.getLoginDataFromName(userDetails.getUsername());
//        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwt);
//        authenticationResponse.setOthers(otherData.getEmployeeId(), otherData.getEmployeeName(), otherData.getRoleId(), otherData.getRoleName(), otherData.getUserId(), otherData.getContact(), otherData.getEmailId());
//        return ResponseEntity.ok(authenticationResponse);
//    }
}
