package com.upskillutoday.crmRoot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.CityDto;
import com.upskillutoday.crmRoot.model.CityMaster;
import com.upskillutoday.crmRoot.repository.CityRepository;
import com.upskillutoday.crmRoot.service.CityService;

@Service
public class CityServiceImpl implements CityService{
	
	
	@Autowired
	private CityRepository cityRepository;

	@Override
	public boolean insertCityService(CityDto cityDto) {
		
		CityMaster city=new CityMaster();
		city.setCityName(cityDto.getCityName());
		city.setState(cityDto.getState());
	    Date date = new Date();
        city.setUpdatedOn(date);
        city.setDeletedFlag(true);
        

        boolean flag=cityRepository.insertCityDao(city);

        return flag;
	}

	@Override
	public List getAllRecordCityService() {
        List  list=cityRepository.getCityListDao();
        return list;
    }
	
	@Override
    public boolean getRecordByCityIdService(CityDto cityDto) {

       
        CityMaster city = new CityMaster();
        city.setCityId(cityDto.getCityId());
       

        boolean flag=cityRepository.getRecordByCityIdDao(city);
        
        cityDto.setCityName(city.getCityName());
        cityDto.setState(city.getState());

        System.out.println("neha"+city.getCityName());
    
    

        return flag;
    }
	
	
    @Override
    public boolean updateCityService(CityDto cityDto) {
    	
    	CityMaster city = new CityMaster();
    	city.setCityId(cityDto.getCityId());
    	city.setCityName(cityDto.getCityName());
    	city.setState(cityDto.getState());
    	//city.setUpdatedBy(updatedBy);
    	city.setUpdatedOn(new Date());
    	city.setDeletedFlag(true);
    	
    

        boolean flag=cityRepository.updateCityDao(city);


        System.out.println("Out update service ");

        return flag;
    }

}
