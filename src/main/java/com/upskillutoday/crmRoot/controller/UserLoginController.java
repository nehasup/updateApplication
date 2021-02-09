package com.upskillutoday.crmRoot.controller;

//import com.upskillutoday.crmRoot.service.MyUserDetailsService;
//import com.upskillutoday.crmRoot.util.JwtUtils;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
        import com.upskillutoday.crmRoot.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;

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
