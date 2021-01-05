package com.upskillutoday.crmRoot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

   
    @Override
    public UserLoginResponseDto loginuserService(UserLoginDto userLoginDto) {

        UserMaster user=new UserMaster();
        //user.setContactNo(userLoginDto.getContactNo());

        boolean flag = userloginDao.userLoginDao(user);

        UserLoginResponseDto userLoginResponseDto=null;

        if (flag) {
            if (userLoginDto.getContactNo() != null)
            {
                if (userLoginDto.getPass().equals(user.getPass()))
                {
                    if (user.getLoginStatus().equalsIgnoreCase("Active"))
                    {
                        System.out.println("Verified ...");
                        // userMasterDto.setLoginStatus(true);
                       
       // userLoginResponseDto = new UserLoginResponseDto(user.getUserId(),user.getFullName(),user.getEmailId(),user.getContactNo(),user.getBirthDate(),user.getGender(),user.getCity(),user.getUserName(),user.getPass(),user.getLoginStatus(),user.getUpdatedBy(),user.getUpdatedOn(),user.isDeletedFlag());

                        userLoginDto.setLoginMsg("Login Successfully..!!");
                        userLoginDto.setFlag(true);
                    }
                    else {
                        //System.out.println("kmdklcmkldmklcj");
                        userLoginDto.setLoginMsg("Your account is Block or Inactive");
                        //userMasterDto.setLoginStatus(false);
                        userLoginDto.setFlag(false);
                    }
                }
                else {
                    userLoginDto.setLoginMsg("Invalid Password....");
                    //userMasterDto.setLoginStatus(false);
                    userLoginDto.setFlag(false);
                }

            }
            else {
                //System.out.println("kmdklcmkldmklcj");
                userLoginDto.setLoginMsg("Invaild MobNo or password!!...");
                System.out.println("Masg::::::::" + userLoginDto.getLoginMsg());
                // userMasterDto.setLoginStatus(false);
                userLoginDto.setFlag(false);
            }

        }
        else {
            //System.out.println("kmdklcmkldmklcj");
            userLoginDto.setLoginMsg("Invaild mobNo or password!!...");
            System.out.println("Masg::::::::" + userLoginDto.getLoginMsg());
            //userMasterDto.setLoginStatus(false);
            userLoginDto.setFlag(false);
        }
        return userLoginResponseDto;
    }
}
