package com.upskillutoday.crmRoot.repository;

import java.util.List;


import com.upskillutoday.crmRoot.model.CityMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CityRepository   {
	boolean insertCityDao(CityMaster city);
	List getCityListDao();
	boolean getRecordByCityIdDao(CityMaster city);
	boolean updateCityDao(CityMaster city);
}

@Repository
@Transactional
class CityRepositoryImpl implements CityRepository {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean insertCityDao(CityMaster city) {
		try {
			entityManager.persist(city);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}



	@Override
	public List getCityListDao() {
		List<String> list = null;
		try {
			Query query = entityManager.createQuery("Select c from CityMaster as c where c.deletedFlag=1",CityMaster.class);
			list = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public boolean getRecordByCityIdDao(CityMaster city) {
		try{
			Query query = entityManager.createQuery("From CityMaster where cityId=:id");

			query.setParameter("id",city.getCityId());

			CityMaster city1= (CityMaster) query.getSingleResult();
			city.setCityName(city1.getCityName());
			city.setState(city1.getState());

			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean updateCityDao(CityMaster city) {

		try {
			entityManager.merge(city);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
