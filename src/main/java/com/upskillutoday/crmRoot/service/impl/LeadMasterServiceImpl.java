package com.upskillutoday.crmRoot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upskillutoday.crmRoot.dto.EmployeeDto;
import com.upskillutoday.crmRoot.dto.EmployeeLeadDto;
import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.EmpLead;
import com.upskillutoday.crmRoot.model.EmployeeMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import com.upskillutoday.crmRoot.model.RoleMaster;
import com.upskillutoday.crmRoot.model.SubCategoryMaster;
import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.model.UserRole;
import com.upskillutoday.crmRoot.repository.CategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.EmpLeadJpaRepository;
import com.upskillutoday.crmRoot.repository.EmployeeJpaRepository;
import com.upskillutoday.crmRoot.repository.LeadJpaMasterRepository;
import com.upskillutoday.crmRoot.repository.LeadMasterRepository;
import com.upskillutoday.crmRoot.repository.RemarkJpaRepository;
import com.upskillutoday.crmRoot.repository.SubCategoryJpaRepository;
import com.upskillutoday.crmRoot.service.LeadMasterService;


@Service
@Transactional
public class LeadMasterServiceImpl implements LeadMasterService{
	
	@Autowired
	private LeadMasterRepository leadRepostiory;
	
	@Autowired
	private SubCategoryJpaRepository subCategoryRepository;
	
	@Autowired
	CategoryJpaRepository categoryJpaRepository;
	
	@Autowired
	RemarkJpaRepository remarkJpaRepository;
	
	@Autowired
	private LeadJpaMasterRepository leadJpaMasterRepository;
	
	@Autowired
	private EmployeeJpaRepository employeeJpaRepository;
	
	@Autowired
	EmpLeadJpaRepository empleadJparepository;

	@Override
	public boolean insertLeadService(LeadMasterDto leadMasterDto) {
		
		//save category
		CategoryMaster categoryMaster = categoryJpaRepository.findByCategoryId(leadMasterDto.getCategoryId());
		
		//save subcategory
		SubCategoryMaster subCategoryMaster = subCategoryRepository.findBySubCategoryId(leadMasterDto.getSubCategoryId());
		
		
		long id = 3;
			RemarkMaster remarkMaster = remarkJpaRepository.findById(id).orElse(null);
		
		
		LeadMaster leadMaster = new LeadMaster();
		
		leadMaster.setStudentName(leadMasterDto.getStudentName());
		leadMaster.setCourseName(leadMasterDto.getCourseName());
		leadMaster.setContactNo(leadMasterDto.getContactNo());
		leadMaster.setArea(leadMasterDto.getArea());
		leadMaster.setCity(leadMasterDto.getCity());
		leadMaster.setEmailId(leadMasterDto.getEmailId());
		leadMaster.setModeOfCourse(leadMasterDto.getModeOfCourse());
		leadMaster.setAddress(leadMasterDto.getAddress());
		leadMaster.setBudget(leadMasterDto.getBudget());
		leadMaster.setModificationStage(leadMasterDto.getModificationStage());
		leadMaster.setRemark(leadMasterDto.getRemark());
		leadMaster.setComments(leadMasterDto.getComments());
		leadMaster.setInstituteName(leadMasterDto.getInstituteName());
		leadMaster.setUpdatedOn(new Date());
		leadMaster.setDeletedFlag(true);
		leadMaster.setAssignLeadFlag(false);
		leadMaster.setCategoryMaster(categoryMaster);
		leadMaster.setSubCategoryMaster(subCategoryMaster);
		leadMaster.setRemarkMaster(remarkMaster);
	
		
		
		//leadMaster.setUpdatedBy();
		
		  try {
			  leadRepostiory.insertLeadRepository(leadMaster);
	            return true;
	        }

	        catch (Exception e){
	            e.printStackTrace();
	            return false;
	        }
		
		
	}

	@Override
	public List<LeadMasterDto> getAllLeadRecordService() {
      //  List  list=leadRepostiory.getAllLeadListDao();
		
		
     
        
		//List<LeadMaster> leadMasterlist = leadMasterService.getAllLeadByAssignFlag();
		List<LeadMaster> leadMasterlist=leadRepostiory.getAllLeadByassignFlag();
		
		
		List<EmpLead> empList =  new ArrayList<EmpLead>();
		
		empList=empleadJparepository.findByDeletedFlag(true);
		
		List<LeadMasterDto> leadMasterarraylist = new ArrayList<LeadMasterDto>();
		if(leadMasterlist!=null) {
			for(LeadMaster lead:leadMasterlist) {
				
				Long studentId=lead.getStudentId();
				
				LeadMaster leadMaster= leadJpaMasterRepository.findByStudentId(studentId);
				if(leadMaster==null) {
				System.out.println("not list");	
				}else {
					LeadMasterDto leadMasterDto1 = new LeadMasterDto();
					EmpLead empLead1 = empleadJparepository.findByLeadMaster(leadMaster);
						if(empLead1==null) {
								leadMasterDto1.setEmployeeName("Not Assign");
								leadMasterDto1.setEmployeeId(null);
				
							}else {
								EmployeeMaster employeeMaster = employeeJpaRepository.findByEmployeeIdAndDeletedFlag(empLead1.getEmployeeMaster().getEmployeeId(), true);
				
								leadMasterDto1.setEmployeeName(employeeMaster.getEmployeeName());
								leadMasterDto1.setEmployeeId(employeeMaster.getEmployeeId());
								}
					
				
		        	 
					leadMasterDto1.setStudentId(leadMaster.getStudentId());
					leadMasterDto1.setStudentName(leadMaster.getStudentName());
					leadMasterDto1.setCourseName(leadMaster.getCourseName());
					leadMasterDto1.setContactNo(leadMaster.getContactNo());
					leadMasterDto1.setArea(leadMaster.getArea());
					leadMasterDto1.setCity(leadMaster.getCity());
					leadMasterDto1.setEmailId(leadMaster.getEmailId());
					leadMasterDto1.setModeOfCourse(leadMaster.getModeOfCourse());
					leadMasterDto1.setAddress(leadMaster.getAddress());
					leadMasterDto1.setBudget(leadMaster.getBudget());		
					leadMasterDto1.setComments(leadMaster.getComments());
					leadMasterDto1.setInstituteName(leadMaster.getInstituteName());
					
					CategoryMaster categoryMaster = categoryJpaRepository.findByCategoryId(leadMaster.getCategoryMaster().getCategoryId());
					//leadMasterDto1.setCategoryMaster(categoryMaster);
					leadMasterDto1.setCategoryName(categoryMaster.getCategoryName());
					leadMasterDto1.setCategoryId(categoryMaster.getCategoryId());
					
					RemarkMaster remarkMaster = remarkJpaRepository.findByRemarkId(leadMaster.getRemarkMaster().getRemarkId());
				//	leadMasterDto1.setRemarkMaster(remarkMaster);
					leadMasterDto1.setRemarkId(remarkMaster.getRemarkId());
					leadMasterDto1.setRemarkName(remarkMaster.getRemarkName());
				
				
		     		//leadMaster.setUpdatedOn(new Date());
		     		//leadMaster.setDeletedFlag(true);
		     		//leadMaster.setAssignLeadFlag(false);
					
					leadMasterarraylist.add(leadMasterDto1);      
					
				}
				
			}
				}
				
				
			
		return leadMasterarraylist;
    }
	
	@Override
    public LeadMasterDto getRecordByStudentIdService(LeadMasterDto leadMasterDto) {
		
		LeadMaster leadMaster = new LeadMaster();
		leadMaster.setStudentId(leadMasterDto.getStudentId());


		LeadMaster leadMaster2 = leadRepostiory.getRecordByStudentIdDao(leadMaster);
     
     
			if(leadMaster2!=null) {
        	
        	
        	LeadMasterDto leadMasterDto2 = new LeadMasterDto();
        	leadMasterDto2.setStudentId(leadMaster2.getStudentId());
        	leadMasterDto2.setStudentName(leadMaster2.getStudentName());
        	leadMasterDto2.setCourseName(leadMaster2.getCourseName());
        	leadMasterDto2.setContactNo(leadMaster2.getContactNo());
        	leadMasterDto2.setArea(leadMaster2.getArea());
        	leadMasterDto2.setCity(leadMaster2.getCity());
        	leadMasterDto2.setEmailId(leadMaster2.getEmailId());
        	leadMasterDto2.setModeOfCourse(leadMaster2.getModeOfCourse());
        	leadMasterDto2.setAddress(leadMaster2.getAddress());
        	leadMasterDto2.setBudget(leadMaster2.getBudget());
        	leadMasterDto2.setModificationStage(leadMaster2.getModificationStage());
        	leadMasterDto2.setRemark(leadMaster2.getRemark());
        	leadMasterDto2.setComments(leadMaster2.getComments());
        	leadMasterDto2.setInstituteName(leadMaster2.getInstituteName());        	
        	leadMasterDto2.setCategoryId(leadMaster2.getCategoryMaster().getCategoryId());
        	
        	
       	if(leadMasterDto.getSubCategoryMaster() != null) {
       		System.out.println("not happend");
       		leadMasterDto2.setSubCategoryId(leadMaster2.getSubCategoryMaster().getSubCategoryId());
    		leadMasterDto2.setSubCategoryMaster(leadMaster2.getSubCategoryMaster());
        	}else {
        		System.out.println("else");
        		//leadMasterDto2.setSubCategoryId(leadMaster2.getSubCategoryMaster().getSubCategoryId());
        		leadMasterDto2.setSubCategoryMaster(leadMaster2.getSubCategoryMaster());
        		
        	}
        	
        	
        	leadMasterDto2.setRemarkId(leadMaster2.getRemarkMaster().getRemarkId());
        	
        	leadMasterDto2.setCategoryMaster(leadMaster2.getCategoryMaster());
        	//leadMasterDto2.setSubCategoryMaster(leadMaster2.getSubCategoryMaster());
        	leadMasterDto2.setRemarkMaster(leadMaster2.getRemarkMaster());
        
        	
    
       	return leadMasterDto2;
        	
        }
        else {
        	return null;
        }
   
        
    }
	
	
	@Override
	public boolean updateLeadService(LeadMasterDto leadMasterDto) {
		//cat obj by id
		CategoryMaster category = categoryJpaRepository.findById(leadMasterDto.getCategoryId()).orElse(null);
		//SubCategoryMaster subCategoryMaster = subCategoryRepository.findById(leadMasterDto.getSubCategoryId()).orElse(null);
		
		RemarkMaster remarkMaster = remarkJpaRepository.findById(leadMasterDto.getRemarkId()).orElse(null);
	
	 
		LeadMaster leadMaster = new LeadMaster();
		leadMaster.setStudentId(leadMasterDto.getStudentId());
		leadMaster.setStudentName(leadMasterDto.getStudentName());
		leadMaster.setCourseName(leadMasterDto.getCourseName());
		leadMaster.setContactNo(leadMasterDto.getContactNo());
		leadMaster.setArea(leadMasterDto.getArea());
		leadMaster.setCity(leadMasterDto.getCity());
		leadMaster.setEmailId(leadMasterDto.getEmailId());
		leadMaster.setModeOfCourse(leadMasterDto.getModeOfCourse());
		leadMaster.setAddress(leadMasterDto.getAddress());
		leadMaster.setBudget(leadMasterDto.getBudget());
		leadMaster.setModificationStage(leadMasterDto.getModificationStage());
		leadMaster.setRemark(leadMasterDto.getRemark());
		leadMaster.setComments(leadMasterDto.getComments());
		leadMaster.setInstituteName(leadMasterDto.getInstituteName());
		leadMaster.setUpdatedOn(new Date());
		leadMaster.setDeletedFlag(true);
		leadMaster.setCategoryMaster(category);
		leadMaster.setSubCategoryMaster(leadMasterDto.getSubCategoryMaster());
	
		leadMaster.setRemarkMaster(remarkMaster);

		
	
	 try {
		 leadRepostiory.updateLeadRepository(leadMaster);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List<LeadMasterDto> getAllLeadListCategoryWiseService(EmployeeMaster employeeMaster) {
		// TODO Auto-generated method stub
		List<LeadMaster> leadMasterList =  new ArrayList<LeadMaster>();
		System.out.println("asdfa"+employeeMaster.getEmployeeName());
		//if(pageName.equals("Lead")) {
			System.out.println("leadd");
			leadMasterList = leadJpaMasterRepository.findByCategoryMasterAndDeletedFlag(employeeMaster.getCategory(), true);
		//}
		
//		if(pageName.equals("Assign")) {
//			System.out.println("asssing");
//			 //leadMasterList = leadJpaMasterRepository.findByCategoryMasterAndDeletedFlagAndAssignLeadFlag(employeeMaster.getCategory(), true,false);
//			leadMasterList=leadJpaMasterRepository.findAllAndDeletedFlagAndAssignLeadFlag(true,false);
//		}
//		
		
		List<LeadMasterDto> leadMasterDtoList = new ArrayList<LeadMasterDto>();
	
		if(leadMasterList!=null) {
			for (LeadMaster leadMaster : leadMasterList) {
				LeadMasterDto leadMasterDto = new LeadMasterDto();
			
				leadMasterDto.setStudentId(leadMaster.getStudentId());
				leadMasterDto.setStudentName(leadMaster.getStudentName());
				leadMasterDto.setCourseName(leadMaster.getCourseName());
				leadMasterDto.setContactNo(leadMaster.getContactNo());
				leadMasterDto.setArea(leadMaster.getArea());
				leadMasterDto.setCity(leadMaster.getCity());
				leadMasterDto.setEmailId(leadMaster.getEmailId());
				leadMasterDto.setModeOfCourse(leadMaster.getModeOfCourse());
				leadMasterDto.setAddress(leadMaster.getAddress());
				leadMasterDto.setBudget(leadMaster.getBudget());
				leadMasterDto.setModificationStage(leadMaster.getModificationStage());
				leadMasterDto.setRemark(leadMaster.getRemark());
				leadMasterDto.setComments(leadMaster.getComments());
				leadMasterDto.setInstituteName(leadMaster.getInstituteName());
				leadMasterDto.setUpdatedOn(new Date());
				leadMasterDto.setDeletedFlag(true);
				leadMasterDto.setCategoryName(leadMaster.getCategoryMaster().getCategoryName());
			//	leadMasterDto.setAssignLeadFlag(leadMaster.isAssignLeadFlag());
				leadMasterDto.setEmployeeName(employeeMaster.getEmployeeName());
				//leadMasterDto.setSubCategoryName(leadMaster.getSubCategoryMaster().getSubCategoryName());
				leadMasterDto.setRemarkName(leadMaster.getRemarkMaster().getRemarkName());
				
				leadMasterDtoList.add(leadMasterDto);
			}
		}		
		return leadMasterDtoList;
	}
	
	
	@Override
	
	public List<LeadMasterDto> getCategoryWiseandverifyLeadService(EmployeeMaster employeeMaster) {
		// TODO Auto-generated method stub
		
		RemarkMaster remarkMaster = remarkJpaRepository.findByRemarkId((long) 7);
		
		List<LeadMaster> leadMasterList = leadJpaMasterRepository.findByCategoryMasterAndRemarkMasterAndDeletedFlag(employeeMaster.getCategory(),remarkMaster, true);

		List<LeadMasterDto> leadMasterDtoList = new ArrayList<LeadMasterDto>();
		
		if(leadMasterList!=null) {
			for (LeadMaster leadMaster : leadMasterList) {
				LeadMasterDto leadMasterDto = new LeadMasterDto();
			
				leadMasterDto.setStudentId(leadMaster.getStudentId());
				leadMasterDto.setStudentName(leadMaster.getStudentName());
				leadMasterDto.setCourseName(leadMaster.getCourseName());
				leadMasterDto.setContactNo(leadMaster.getContactNo());
				leadMasterDto.setArea(leadMaster.getArea());
				leadMasterDto.setCity(leadMaster.getCity());
				leadMasterDto.setEmailId(leadMaster.getEmailId());
				leadMasterDto.setModeOfCourse(leadMaster.getModeOfCourse());
				leadMasterDto.setAddress(leadMaster.getAddress());
				leadMasterDto.setBudget(leadMaster.getBudget());
				leadMasterDto.setModificationStage(leadMaster.getModificationStage());
				leadMasterDto.setRemark(leadMaster.getRemark());
				leadMasterDto.setComments(leadMaster.getComments());
				leadMasterDto.setInstituteName(leadMaster.getInstituteName());
				leadMasterDto.setUpdatedOn(new Date());
				leadMasterDto.setDeletedFlag(true);
				leadMasterDto.setCategoryName(leadMaster.getCategoryMaster().getCategoryName());
				//leadMasterDto.setSubCategoryName(leadMaster.getSubCategoryMaster().getSubCategoryName());
				leadMasterDto.setRemarkName(leadMaster.getRemarkMaster().getRemarkName());
				
				leadMasterDtoList.add(leadMasterDto);
			}
		}		
		return leadMasterDtoList;
	}

	
	@Override
	public List<LeadMasterDto> getAllAssignLeadListService(EmployeeMaster employeeMaster) {
		// TODO Auto-generated method stub
		
		///List<LeadMaster> leadMasterList = leadJpaMasterRepository.findByCategoryMasterAndDeletedFlagAndAssignLeadFlag(employeeMaster.getCategory(), true,false);
	
		//third table
		List<EmpLead> emList=empleadJparepository.findByEmployeeMasterAndDeletedFlag(employeeMaster, true);
		
		
		List<LeadMasterDto> leadMasterDtoList = new ArrayList<LeadMasterDto>();
			if(emList!=null) {
				for(EmpLead empLead:emList) 
				{	
		
		LeadMaster leadMaster= leadJpaMasterRepository.findByStudentIdAndDeletedFlag(empLead.getLeadMaster().getStudentId(), true);
		LeadMasterDto leadMasterDto = new LeadMasterDto();
		
		leadMasterDto.setStudentId(leadMaster.getStudentId());
		leadMasterDto.setStudentName(leadMaster.getStudentName());
		leadMasterDto.setCourseName(leadMaster.getCourseName());
		leadMasterDto.setContactNo(leadMaster.getContactNo());
		leadMasterDto.setArea(leadMaster.getArea());
		leadMasterDto.setCity(leadMaster.getCity());
		leadMasterDto.setEmailId(leadMaster.getEmailId());
		leadMasterDto.setModeOfCourse(leadMaster.getModeOfCourse());
		leadMasterDto.setAddress(leadMaster.getAddress());
		leadMasterDto.setBudget(leadMaster.getBudget());
		leadMasterDto.setModificationStage(leadMaster.getModificationStage());
		leadMasterDto.setRemark(leadMaster.getRemark());
		leadMasterDto.setComments(leadMaster.getComments());
		leadMasterDto.setInstituteName(leadMaster.getInstituteName());
		leadMasterDto.setUpdatedOn(new Date());
		leadMasterDto.setDeletedFlag(true);
		leadMasterDto.setEmployeeName(employeeMaster.getEmployeeName());
		
		CategoryMaster categoryMaster = categoryJpaRepository.findByCategoryId(leadMaster.getCategoryId());
		leadMasterDto.setCategoryMaster(categoryMaster);	
		
		leadMasterDto.setCategoryName(leadMaster.getCategoryMaster().getCategoryName());
		leadMasterDto.setCategoryId(leadMaster.getCategoryMaster().getCategoryId());
		RemarkMaster remarkMaster=remarkJpaRepository.findByRemarkId(leadMaster.getRemarkId());

		
		leadMasterDto.setRemarkMaster(remarkMaster);
		
		//leadMasterDto.setSubCategoryName(leadMaster.getSubCategoryMaster().getSubCategoryName());
		leadMasterDto.setRemarkName(leadMaster.getRemarkMaster().getRemarkName());
		leadMasterDto.setRemarkId(leadMaster.getRemarkMaster().getRemarkId());
		
		leadMasterDtoList.add(leadMasterDto);
	
	}
		}
		
		
		
	
		return leadMasterDtoList;
	}

	@Override
	public List<LeadMaster> getAllLeadByAssignFlag() {
		List<LeadMaster> leadMasterDtos=leadRepostiory.getAllLeadByassignFlag();
		return leadMasterDtos;
	}

	
	
}
