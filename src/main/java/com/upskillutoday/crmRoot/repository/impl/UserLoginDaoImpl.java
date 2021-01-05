package com.upskillutoday.crmRoot.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.repository.UserLoginDao;
import com.upskillutoday.crmRoot.response.UserLoginResponseDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Repository(value = "UserLoginDao")
@Transactional
public class UserLoginDaoImpl implements UserLoginDao{

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public boolean userLoginDao(UserMaster user) {
		return false;
//        try {
//            Query q = entityManager.createQuery("select new com.upskillutoday.crmRoot.response.UserLoginResponseDto(u.userId as userId,u.fullName as fullName,u.emailId as emailId,u.contactNo as contactNo,u.birthDate as birthDate,u.gender as gender,u.city as city,u.userName as userName,u.pass as pass,u.loginStatus as loginStatus,u.updatedBy as updatedBy,u.updatedOn as updatedOn,u.deletedFlag as deletedFlag) from User as u where u.contactNo=:contactNo");
//            q.setParameter("contactNo");
//
//            UserLoginResponseDto d= (UserLoginResponseDto) q.getSingleResult();
//
//            System.out.println("LoginId" + d.getUserId());
//
//            user.setUserId(d.getUserId());
//         //   user.setContactNo(d.getContactNo());
//         //   user.setFullName(d.getFullName());
//            user.setUserName(d.getUserName());
//           
//            user.setPass(d.getPass());
//
//            //System.out.println("InstId:"+user.getInstitute().getInstId());
//            //System.out.println("DealerId:"+user.getDealer().getDealerId());
//
//            return true;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return false;
//        }
    }


	@Override
	public boolean saveUserNameDao(UserMaster user) {
		try {
			entityManager.persist(user);
			return true;
		}
		catch (Exception e) {
			 e.printStackTrace();
	            return false;
		}
	}
}
