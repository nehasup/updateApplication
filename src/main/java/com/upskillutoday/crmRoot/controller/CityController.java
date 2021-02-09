package com.upskillutoday.crmRoot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.upskillutoday.crmRoot.dto.CityDto;
import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.model.CityMaster;
import com.upskillutoday.crmRoot.repository.JPARepository.CityJpaRepository;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.CityService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class CityController {
	
	@Autowired
	private CityService cityservice;
	
	@Autowired
	private CityJpaRepository cityjpaRepository;

	@PostMapping("/saveCity")
	@ResponseBody 
	public ResponseVO insertCity(
	        @RequestBody CityDto cityDto
    ) {
		  ResponseVO response = new ResponseVO();

	        boolean flag=cityservice.insertCityService(cityDto);
	        if(flag) {
	            response.setMessage("Insert City Sucessfully");
	            response.setStatusCode(String.valueOf(HttpStatus.OK));
	        } else {
	            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
	            response.setMessage("Insert Failed!!");
	        }
	        return response;
    }

	//get active city list
    @GetMapping("/getAllcityList")
    @ResponseBody
    public ResponseVO<List> getCityAllList() {
        ResponseVO<List> response=new ResponseVO<List>();
        System.out.println("List Successfully!!");
        List list=cityservice.getAllRecordCityService();
        response.setResult(list);
        if(list.size()!=0) {
            response.setStatusCode(String.valueOf(HttpStatus.OK));
            response.setMessage("Data is Present Successfully!!");
        } else {
            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            response.setMessage("Data is Not Present!!");
        }
        return response;
    }
 
    //get All city list
    @GetMapping("/getCities")
    public List<CityMaster> getAllCities() {
        return cityjpaRepository.findAll();
    }
 
    //getRecordByidForEdit.........By neha
    @GetMapping("/getAllcitybyid/{id}")
    @ResponseBody public ResponseVO<CityDto> getRecordByCityIdController(
            @PathVariable(value = "id") Long cityId
    ) {
         ResponseVO<CityDto> response = new ResponseVO<CityDto>();
         CityDto cityDto=new CityDto();
         cityDto.setCityId(cityId);
         boolean flag = cityservice.getRecordByCityIdService(cityDto);
         if(flag) {
             response.setMessage("Search By Data Sucessfully");
             response.setStatusCode(String.valueOf(HttpStatus.OK));
             response.setResult(cityDto);
         } else {
             response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
             response.setMessage("Search Failed!!");
             response.setResult(cityDto);
         }
         return response;
    }
 
 
    //for update City...by neha
     @PatchMapping("/updateCity")
     @ResponseBody  public ResponseVO updateCity(
             @RequestBody CityDto cityDto
     ) {
         ResponseVO<CityDto>responseVO=new ResponseVO<CityDto>();
         boolean flag= cityservice.updateCityService(cityDto);
         if(flag) {
             responseVO.setStatusCode(String.valueOf(HttpStatus.OK));
             responseVO.setMessage("Update Successfully!!");
             responseVO.setResult(cityDto);
         } else {
             responseVO.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
             responseVO.setMessage("Updation Failed!!");
             responseVO.setResult(cityDto);
         }
         return responseVO;
     }

     @PutMapping("/updateCityByid/{id}")
     public ResponseEntity<CityMaster> updateCityid(
             @PathVariable(value = "id") Long cityId,
             @RequestBody CityMaster cityDetails
     ) throws ResourceNotFoundException {
         CityMaster city = cityjpaRepository.findById(cityId)
         .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

         city.setCityName(cityDetails.getCityName());
         city.setState(cityDetails.getState());
         city.setUpdatedOn(new Date());
         city.setDeletedFlag(true);

         final CityMaster updatedCity = cityjpaRepository.save(city);
         return ResponseEntity.ok(updatedCity);
     }

     @DeleteMapping("/deleteCity/{id}")
     public Map<String, Boolean> deleteCity(
             @PathVariable(value = "id") Long cityId
     ) throws ResourceNotFoundException {
         CityMaster city = cityjpaRepository.findById(cityId)
        .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));
         city.setDeletedFlag(false);
         cityjpaRepository.save(city);
         Map<String, Boolean> response = new HashMap<>();
         response.put("deletedFlag", Boolean.TRUE);
         return response;
     }
}
