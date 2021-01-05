package com.upskillutoday.crmRoot.service;

import java.util.List;

import com.upskillutoday.crmRoot.dto.CityDto;
import com.upskillutoday.crmRoot.model.CityMaster;

public interface CityService {

	public boolean insertCityService(CityDto cityDto);

	List getAllRecordCityService();

	boolean getRecordByCityIdService(CityDto cityDto);

	boolean updateCityService(CityDto cityDto);

}
