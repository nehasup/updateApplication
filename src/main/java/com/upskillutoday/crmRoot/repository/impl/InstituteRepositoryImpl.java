package com.upskillutoday.crmRoot.repository.impl;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upskillutoday.crmRoot.model.InstituteMaster;
import com.upskillutoday.crmRoot.repository.InstituteRepository;

import java.util.List;

@Repository
@Transactional
public class InstituteRepositoryImpl implements InstituteRepository {
	
	@Autowired
    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public boolean insertInsituteDao(InstituteMaster institute) {

		try {
			entityManager.persist(institute);
			return true;
		}
		catch (Exception e) {
			 e.printStackTrace();
	            return false;
		}
		
	}

	@Override
	public List getInstituteByCategoryFromStudentId(Long stduentId) {
    return entityManager
        .createQuery(
            "SELECT new InstituteNameResponse (im.instituteName) FROM InstituteMaster as im\n"
                + "    inner join CategoryMaster as c on im.categoryMaster.categoryId = c.categoryId\n"
                + "    where im.categoryMaster.categoryId = " + stduentId)
        .getResultList();
	}


}
