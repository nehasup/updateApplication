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
