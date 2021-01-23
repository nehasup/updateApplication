package com.upskillutoday.crmRoot.controller;

import com.upskillutoday.crmRoot.repository.UserMasterRepository;
import com.upskillutoday.crmRoot.request.AuthenticationRequest;
import com.upskillutoday.crmRoot.response.AuthenticationResponse;
//import com.upskillutoday.crmRoot.service.MyUserDetailsService;
//import com.upskillutoday.crmRoot.util.JwtUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.upskillutoday.crmRoot.dto.UserLoginDto;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.response.UserLoginResponseDto;
import com.upskillutoday.crmRoot.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;

//	@RequestMapping(value = "/auth", method = RequestMethod.POST)
//	public ResponseEntity<?> createAuthenticationTokne(
//			@RequestBody AuthenticationRequest authenticationRequest
//	) throws Exception {
//	 	return userLoginService.createAuthenticationToken(authenticationRequest);
//	}
}
