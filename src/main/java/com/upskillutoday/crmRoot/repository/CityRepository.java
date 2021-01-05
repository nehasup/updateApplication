package com.upskillutoday.crmRoot.repository;

import java.util.List;


import com.upskillutoday.crmRoot.model.CityMaster;

public interface CityRepository   {
	
	boolean insertCityDao(CityMaster city);

	List getCityListDao();

	boolean getRecordByCityIdDao(CityMaster city);

	boolean updateCityDao(CityMaster city);

	//City getrecordCityIdDao(Long cityId);

	

}
