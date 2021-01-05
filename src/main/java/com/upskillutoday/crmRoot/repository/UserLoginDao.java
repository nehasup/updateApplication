package com.upskillutoday.crmRoot.repository;

import com.upskillutoday.crmRoot.model.UserMaster;

public interface UserLoginDao {
    boolean userLoginDao(UserMaster user);
    
    boolean saveUserNameDao(UserMaster user);
}
