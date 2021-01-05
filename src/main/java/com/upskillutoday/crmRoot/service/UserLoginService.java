package com.upskillutoday.crmRoot.service;

import com.upskillutoday.crmRoot.dto.UserLoginDto;
import com.upskillutoday.crmRoot.response.UserLoginResponseDto;

public interface UserLoginService {
    UserLoginResponseDto loginuserService(UserLoginDto userLoginDto);
}
