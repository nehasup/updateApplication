package com.upskillutoday.crmRoot.service.impl;


import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.LeadMaster;
import com.upskillutoday.crmRoot.model.RemarkMaster;
import com.upskillutoday.crmRoot.model.SubCategoryMaster;
import com.upskillutoday.crmRoot.model.UserMaster;
import com.upskillutoday.crmRoot.repository.CategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.FileUploadRepository;
import com.upskillutoday.crmRoot.repository.LeadMasterRepository;
import com.upskillutoday.crmRoot.repository.RemarkJpaRepository;
import com.upskillutoday.crmRoot.repository.SubCategoryJpaRepository;
import com.upskillutoday.crmRoot.repository.UserMasterRepository;
import com.upskillutoday.crmRoot.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	@Autowired
	private FileUploadRepository fileuploadrepository;
	
	@Autowired
	private LeadMasterRepository leadMasterRepository;
	
	@Autowired
	private CategoryJpaRepository categoryJpaRepository;
	
	@Autowired
	private SubCategoryJpaRepository subCategoryJpaRepository;
	
	
	@Autowired
	private RemarkJpaRepository remarkJpaRepository;	
	
	@Override
	public Long save(MultipartFile file) {
		boolean result = false;
		 
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if(extension.equalsIgnoreCase("json")) {
			 readDataFromJson(file);
		}else if(extension.equalsIgnoreCase("csv")) {
			 readDataFromCsv(file);
		}
		else if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			
			 result = readDataFromExcel(file);
		}
		
		if(result) {
			return 0L;
		}
		else {
			return 1L;
		}
		
	}

	private Boolean readDataFromExcel(MultipartFile file) {

		Boolean result = false;

		Workbook workbook = getWorkBook(file);
		
		Sheet sheet = workbook.getSheetAt(0);
		
		for(Row row:sheet) {
			
			if(!row.getCell(0).toString().trim().equals("Student Name")) {
				
//				Boolean existingFlag = true;
//				LeadMaster leadMaster =  leadMasterRepository.findByStudentNameAndContactNoAndCourseNameAndDeletedFlag(row.getCell(0).toString(), row.getCell(1).toString(), row.getCell(3).toString(), true);
//				
//				if(leadMaster==null) {}
//				
				String studentName = row.getCell(0).toString();
				String contactNo = null;
				if(row.getCell(1).getCellType()== Cell.CELL_TYPE_NUMERIC) {
					 contactNo = NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());
					
				}else if (row.getCell(1).getCellType()== Cell.CELL_TYPE_STRING ) {
					
				}
				
				String emailId = row.getCell(2).toString();
				
				
				LeadMaster presentLead = fileuploadrepository.findByStudentNameAndContactNoAndEmailIdAndDeletedFlag(studentName, contactNo, emailId, false); 
				
				if(presentLead==null) {
					result = true;
					
					LeadMaster leadMaster = new LeadMaster();
					leadMaster.setStudentName(studentName);
					leadMaster.setContactNo(contactNo);
					leadMaster.setEmailId(emailId);					
					leadMaster.setCourseName(row.getCell(3).toString());
					leadMaster.setCity(row.getCell(4).toString());
					leadMaster.setArea(row.getCell(5).toString());
					leadMaster.setModeOfCourse(row.getCell(6).toString());
					
					
					CategoryMaster categoryMaster = categoryJpaRepository.findByCategoryNameAndDeletedFlag(row.getCell(7).toString(), true);
					if(categoryMaster!= null) {
						leadMaster.setCategoryMaster(categoryMaster);
					}
					
					//leadMaster.setSubCategoryMaster(row.getCell(8).getStringCellValue());
					
//					SubCategoryMaster subCategoryMaster = subCategoryJpaRepository.findBySubCategoryNameAndDeletedFlag(row.getCell(8).toString(), true);
//					
//					if(subCategoryMaster!=null) {
//						leadMaster.setSubCategoryMaster(subCategoryMaster);
//					}
					
					long id = 3;
					RemarkMaster remarkMaster = remarkJpaRepository.findById(id).orElse(null);
					
					leadMaster.setRemarkMaster(remarkMaster);
					
					
					//insert using by session user id
					//leadMaster.setUpdatedBy(userId);
					leadMaster.setUpdatedOn(new Date());
					leadMaster.setDeletedFlag(true);
					leadMaster.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					fileuploadrepository.save(leadMaster);
				}
			}
			
		}
		
		return result;
		
		/*Iterator<Row> rows = sheet.iterator();
		rows.next();
		while(rows.hasNext()) {
			Row row = rows.next();
			
			LeadMaster leadMaster = new LeadMaster();
			
			if(row.getCell(0).getCellType()== Cell.CELL_TYPE_STRING) {
				leadMaster.setStudentName(row.getCell(0).getStringCellValue());
			}
			
			if(row.getCell(1).getCellType()== Cell.CELL_TYPE_NUMERIC) {
				String contactNo = NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());
				leadMaster.setContactNo(contactNo);
			}else if (row.getCell(1).getCellType()== Cell.CELL_TYPE_STRING ) {
				leadMaster.setContactNo(row.getCell(1).getStringCellValue());
				
			}
			
			
			//leadMasterRepository.findByEmail(email);
			if(row.getCell(2).getCellType()== Cell.CELL_TYPE_STRING) {
				String emailId =row.getCell(2).getStringCellValue();
				
				leadMaster=leadMasterRepository.findByEmail(emailId);
				
				leadMaster.setEmailId(row.getCell(2).getStringCellValue());
				System.out.println(row.getCell(2).getStringCellValue());
			}
			//insert using by session user id
			//leadMaster.setUpdatedBy(userId);
			leadMaster.setUpdatedOn(new Date());
			leadMaster.setDeletedFlag(false);
			leadMaster.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
			fileuploadrepository.save(leadMaster);
			}*/
		
		
	}

	private Workbook getWorkBook(MultipartFile file) {
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			if(extension.equalsIgnoreCase("xlsx")) {
				workbook = new XSSFWorkbook(file.getInputStream());
			}
			else if(extension.equalsIgnoreCase("xls")) {
				workbook = new HSSFWorkbook(file.getInputStream());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	private void readDataFromCsv(MultipartFile file) {
		
		/*
		 * try { InputStreamReader reader = new
		 * InputStreamReader(file.getInputStream()); CSVReader csvReader = new
		 * CSVReaderBuilder(reader).withSkipLines(1).build(); List<String[]> rows =
		 * csvReader.readAll(); for(String[]row : rows) { fileuploadrepository.save(new
		 * LeadMaster(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7],
		 * row[8], row[9], row[10], row[11],
		 * FilenameUtils.getExtension(file.getOriginalFilename()))); }
		 * 
		 * } catch (Exception e) { // 
		 * 
		 */
		
		
		
	}

	private void readDataFromJson(MultipartFile file) {
		try {
			
			InputStream inputStream = file.getInputStream();
			ObjectMapper mapper = new ObjectMapper();
			List<LeadMaster> leadMasters = Arrays.asList(mapper.readValue(inputStream, LeadMaster[].class));
			if(leadMasters!=null && leadMasters.size()>0) {
				for(LeadMaster leadMaster : leadMasters) {
					leadMaster.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
					fileuploadrepository.save(leadMaster);
				}
			}
		
		}
		
		
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public List<LeadMaster> findAll() {
		
		return fileuploadrepository.findAll();
	}

	
}