package com.upskillutoday.crmRoot.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.upskillutoday.crmRoot.dto.LeadMasterDto;
import com.upskillutoday.crmRoot.model.*;
import com.upskillutoday.crmRoot.repository.*;
import com.upskillutoday.crmRoot.repository.JPARepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.transaction.Transactional;

public interface LeadMasterService {
	boolean insertLeadService(LeadMasterDto leadMasterDto);
	List getAllLeadRecordService();
	Flux getAllLeadRecordServiceFlux();
	LeadMasterDto getRecordByStudentIdService(LeadMasterDto leadMasterDto);
	boolean updateLeadService(Long userId, LeadMasterDto leadMasterDto);
	List<LeadMasterDto> getCategoryWiseandverifyLeadService(EmployeeMaster employeeMaster);
	List getAllAssignLeadListService(EmployeeMaster employeeMaster);
	List<LeadMaster> getAllLeadByAssignFlag();
	String assignUnverifiedLeadToVerifiers();
	LeadMaster getLeadByStudentId(Long stduentId);
	List getAllUnAssignedLeadWithAssignedLead();
}

@Service
@Transactional
class LeadMasterServiceImpl implements LeadMasterService{

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
    EmpLeadJpaRepository empleadJparepository;

    @Autowired
    EmpCategyRepository empCategyRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmpLeadRepository empLeadRepository;

    @Autowired
    RemarkService remarkService;

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

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
        // leadMaster.setSubCategoryMaster(subCategoryMaster);
        leadMaster.setRemarkMaster(remarkMaster);

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
    public List getAllLeadRecordService() {
        return leadRepostiory.getAllLeadForMe();
    }

    @Override
    public Flux getAllLeadRecordServiceFlux() {
        return leadRepostiory.getAllLeadForMeFlux().delayElements(Duration.ofSeconds(1));
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
            leadMasterDto2.setUpdatedOn(new Date());
            if(leadMasterDto.getSubCategoryMaster() != null) {
                System.out.println("not happend");
                leadMasterDto2.setSubCategoryId(leadMaster2.getSubCategoryMaster().getSubCategoryId());
                leadMasterDto2.setSubCategoryMaster(leadMaster2.getSubCategoryMaster());
            } else {
                System.out.println("else");
                leadMasterDto2.setSubCategoryMaster(leadMaster2.getSubCategoryMaster());
            }
            leadMasterDto2.setRemarkId(leadMaster2.getRemarkMaster().getRemarkId());
            leadMasterDto2.setCategoryMaster(leadMaster2.getCategoryMaster());
            leadMasterDto2.setRemarkMaster(leadMaster2.getRemarkMaster());
            return leadMasterDto2;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean updateLeadService(Long userId, LeadMasterDto leadMasterDto) {
        //cat obj by id
        CategoryMaster category = categoryJpaRepository.findById(leadMasterDto.getCategoryId()).orElse(null);
        RemarkMaster remarkMaster = remarkJpaRepository.findById(leadMasterDto.getRemarkId()).orElse(null);
        SubCategoryMaster subCategoryMaster = subCategoryRepository.findBySubCategoryId(leadMasterDto.getSubCategoryId());
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
        leadMaster.setUpdatedBy(employeeService.getEmpIdFromUserId(userId));
        leadMaster.setDeletedFlag(true);
        leadMaster.setCategoryMaster(category);
        leadMaster.setSubCategoryMaster(subCategoryMaster);
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
    public List<LeadMasterDto> getCategoryWiseandverifyLeadService(EmployeeMaster employeeMaster) {
        // TODO Auto-generated method stub

        RemarkMaster remarkMaster = remarkJpaRepository.findByRemarkId((long) 7);

        List<LeadMaster> leadMasterList = new ArrayList<>();
        ArrayList<EmpCategy> list = (ArrayList<EmpCategy>) empCategyRepository.findAll();
        for(EmpCategy empCategy : list) {
            if(empCategy.getEmployeeMaster().getEmployeeId().equals(employeeMaster.getEmployeeId()))
                leadMasterList.addAll(leadJpaMasterRepository.findByCategoryMasterAndRemarkMasterAndDeletedFlag(empCategy.getCategoryMaster(),remarkMaster, true));
        }

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
                leadMasterDto.setSubCategoryName(leadMaster.getSubCategoryMaster().getSubCategoryName());
                leadMasterDto.setRemarkName(leadMaster.getRemarkMaster().getRemarkName());
                leadMasterDtoList.add(leadMasterDto);
            }
        }
        return leadMasterDtoList;
    }

    @Override
    public List getAllAssignLeadListService(EmployeeMaster employeeMaster) {
        //third table
        List leadMasterDtoList = empLeadRepository.getAllLeadsFromEmployeeId(employeeMaster.getEmployeeId());
        return leadMasterDtoList;
    }

    @Override
    public List<LeadMaster> getAllLeadByAssignFlag() {
        List<LeadMaster> leadMasterDtos=leadRepostiory.getAllLeadByassignFlag();
        return leadMasterDtos;
    }

    @Override
    public String assignUnverifiedLeadToVerifiers() {
        List leadMasters = leadRepostiory.getAllUnassignedNewLeads();
        List verificationCounsellor = employeeService.getAllVerificationCounsellor();
        StringBuilder stringBuilder = new StringBuilder();
        if (leadMasters != null && verificationCounsellor != null) {
            for (LeadMaster leadMaster : (List<LeadMaster> )leadMasters) {
                EmpLead empLead = new EmpLead();
                empLead.setLeadMaster(leadMaster);
                List employees = employeeRepository.getVerificationConsellorByCategory(leadMaster.getCategoryMaster().getCategoryId());
                empLead.setEmployeeMaster((EmployeeMaster) employees.get(0));
                empLead.setUpdatedOn(new Date());
                empLead.setDeletedFlag(true);
                historyRepository.insertHistory(new History("Assigned" ,new Date(), empLead.getEmployeeMaster(), empLead.getLeadMaster(), remarkService.getRemarkById(3L)));
                empLeadRepository.addEmpLead(empLead);
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public LeadMaster getLeadByStudentId(Long stduentId) {
        return leadRepostiory.getLeadByStudentId(stduentId);
    }

    @Override
    public List getAllUnAssignedLeadWithAssignedLead() {
        List list = leadRepostiory.getAllUnAssignedLeads();
        list.addAll(leadRepostiory.otherThanAllUnAssignedLeads());
        return list;
    }
}
