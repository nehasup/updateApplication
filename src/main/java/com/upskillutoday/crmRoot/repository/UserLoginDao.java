package com.upskillutoday.crmRoot.repository;

import com.upskillutoday.crmRoot.model.UserMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public interface UserLoginDao {
    boolean userLoginDao(UserMaster user);
    
    boolean saveUserNameDao(UserMaster user);
}

@Repository
@Transactional
class UserLoginDaoImpl implements UserLoginDao{

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
