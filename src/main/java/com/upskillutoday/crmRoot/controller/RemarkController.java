package com.upskillutoday.crmRoot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.upskillutoday.crmRoot.dto.RemarkDto;
import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import com.upskillutoday.crmRoot.repository.RemarkJpaRepository;
import com.upskillutoday.crmRoot.response.ResponseVO;
import com.upskillutoday.crmRoot.service.RemarkService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "*")
public class RemarkController {
	
	@Autowired
	private RemarkService remarkService;
	
	@Autowired
	private RemarkJpaRepository remarkJpaRepository;
	
    //add remark
	@PostMapping("/saveRemark")
	@ResponseBody 
	public ResponseVO insertRemark(
	        @RequestBody RemarkDto remarkDto
    ) {
      ResponseVO response = new ResponseVO();
        boolean flag=remarkService.insertRemarkService(remarkDto);
        if(flag) {
            response.setMessage("Insert Remarks Sucessfully");
            response.setStatusCode(String.valueOf(HttpStatus.OK));
        } else {
            response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
            response.setMessage("Insert Failed!!");
        }
        return response;
    }
	
	//get all active remark list
     @GetMapping("/getAllRemarkList")
     @ResponseBody
     public ResponseVO<List> getRemarkAllList() {
        ResponseVO<List> response=new ResponseVO<List>();
        System.out.println("List Successfully!!");
        List list=remarkService.getAllRecordRemarkService();
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
 
    //getRecordByidForEdit.........By neha
     @GetMapping("/getAllremarkbyid/{id}")
     @ResponseBody public ResponseVO<RemarkDto> getRecordByRemarkIdController(
             @PathVariable(value = "id") Long remarkId
     ) {
         ResponseVO<RemarkDto> response = new ResponseVO<RemarkDto>();
         RemarkDto remarkDto = new RemarkDto();
         remarkDto.setRemarkId(remarkId);
         boolean flag=remarkService.getRecordByRemarkIdService(remarkDto);
         if(flag) {
             response.setMessage("Search By Data Sucessfully");
             response.setStatusCode(String.valueOf(HttpStatus.OK));
             response.setResult(remarkDto);
         } else {
             response.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
             response.setMessage("Search Failed!!");
             response.setResult(remarkDto);
         }
         return response;
    }
 
     //update remark by id
     @PutMapping("/updateRemarkByid/{id}")
     public ResponseEntity<RemarkMaster> updateRemarbyid(
             @PathVariable(value = "id") Long remarkId,
             @RequestBody RemarkMaster remarkDetails
     ) throws ResourceNotFoundException {
         RemarkMaster remark = remarkJpaRepository.findById(remarkId)
         .orElseThrow(() -> new ResourceNotFoundException("Remark not found for this id :: " + remarkId));
         remark.setRemarkName(remarkDetails.getRemarkName());
         remark.setUpdatedOn(new Date());
         remark.setDeletedFlag(true);
         final RemarkMaster updatedRemark = remarkJpaRepository.save(remark);
         return ResponseEntity.ok(updatedRemark);
     }
 
 
    //delete remark by id
     @DeleteMapping("/deleteRemark/{id}")
     public Map<String, Boolean> deleteRemark(
             @PathVariable(value = "id") Long remarkId
     ) throws ResourceNotFoundException {
         RemarkMaster remark = remarkJpaRepository.findById(remarkId)
        .orElseThrow(() -> new ResourceNotFoundException("Remark not found for this id :: " + remarkId));
         remark.setDeletedFlag(false);
         remarkJpaRepository.save(remark);
         Map<String, Boolean> response = new HashMap<>();
         response.put("deletedFlag", Boolean.TRUE);
         return response;
     }
  
    //get All Remark list
    @GetMapping("/getRemarks")
    public List getAllRemark() {
        return remarkJpaRepository.findAll();
    }

    @GetMapping("/getAllRemarksWithCount")
    public List getAllRemarksWithCount() {
	    return remarkService.getAllRemarkWithCount();
    }

    @GetMapping("/getAllRemarksWithCountByEmp")
    public List getAllRemarkWithCountByEmp(@RequestParam("empId") Long empId) {
	    return remarkService.getRemarkWithCountForEmployee(empId);
    }

    @GetMapping("/getAllCountForEmpDatewise")
    public List getAllCountForEmpDatewise(@RequestParam("empId") Long empId, @RequestParam("date") String date) {
	    return remarkService.getAllCountOfEmpDatewise(empId, date);
    }
}
