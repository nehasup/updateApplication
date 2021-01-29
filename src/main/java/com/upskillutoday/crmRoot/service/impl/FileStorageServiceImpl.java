package com.upskillutoday.crmRoot.service.impl;


import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.upskillutoday.crmRoot.exception.GlobalExceptionHandler;
import com.upskillutoday.crmRoot.exception.ResourceNotFoundException;
import com.upskillutoday.crmRoot.model.*;
import com.upskillutoday.crmRoot.repository.*;
import com.upskillutoday.crmRoot.service.LeadMasterService;
import com.upskillutoday.crmRoot.service.RemarkService;
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
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private LeadMasterService leadMasterService;

	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private RemarkService remarkService;

	@Autowired
	private InstituteRepository instituteRepository;
	
	@Override
	public Long save(MultipartFile file) throws ResourceNotFoundException {
		Long result = 0L;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if(extension.equalsIgnoreCase("json")) {
			 readDataFromJson(file, 0);
		}else if(extension.equalsIgnoreCase("csv")) {
			 readDataFromCsv(file, 0);
		}
		else if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			 result = readDataFromExcel(file, 0);
		}
		leadMasterService.assignUnverifiedLeadToVerifiers();
		return result;
	}

	@Override
	public Long saveInstituteFile(MultipartFile file) throws ResourceNotFoundException {
		Long result = 0L;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		if(extension.equalsIgnoreCase("json")) {
			readDataFromJson(file, 1);
		}else if(extension.equalsIgnoreCase("csv")) {
			readDataFromCsv(file, 1);
		}
		else if(extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
			result = readDataFromExcel(file, 1);
		}
		return result;
	}

	private Long readDataFromExcel(MultipartFile file, int i) throws ResourceNotFoundException {
		Long count = 0L;
		Workbook workbook = getWorkBook(file);
		Sheet sheet = workbook.getSheetAt(0);

		// insert leads by file
		if(i == 0) {
			// updated by laukik
			for(Row row:sheet) {
				if(!row.getCell(0).toString().trim().equals("Student Name")) {
					String studentName;
					if(row.getCell(0) != null) {
						studentName = row.getCell(0).toString();
					} else {
						throw new ResourceNotFoundException("Student name Not Found" + ": Uploaded Student Count - " + count);
					}

					String contactNo;
					if(row.getCell(1).getCellType()== Cell.CELL_TYPE_NUMERIC) {
						contactNo = NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());
					} else {
						throw new ResourceNotFoundException("'Contact' Not Found of Student Name: - " + studentName + ": Uploaded Student Count - " + count);
					}

					String emailId;
					if(row.getCell(2) != null) {
						emailId = row.getCell(2).toString();
					} else {
						throw new ResourceNotFoundException("'Email' Not Found of Student Name: - " + studentName + ": Uploaded Student Count - " + count);
					}

//					LeadMaster presentLead = fileuploadrepository.findByStudentNameAndContactNoAndEmailIdAndDeletedFlag(studentName, contactNo, emailId, false);
					List presentLead = leadMasterRepository.getLeadMasterByNameEmailContactDeleteFlag(studentName, contactNo, emailId, false);

					if(presentLead.size() == 0) {
						LeadMaster leadMaster = new LeadMaster();
						leadMaster.setStudentName(studentName);
						leadMaster.setContactNo(contactNo);
						leadMaster.setEmailId(emailId);

						if(row.getCell(3) != null) {
							leadMaster.setCourseName(row.getCell(3).toString());
						} else {
							throw new ResourceNotFoundException("'Course Name' Not Found of Student Name: - " + studentName + ": Uploaded Student Count - " + count);
						}

						if(row.getCell(4) != null) {
							leadMaster.setCity(row.getCell(4).toString());
						} else {
							throw new ResourceNotFoundException("'City' Not Found of Student Name: - " + studentName + ": Uploaded Student Count - " + count);
						}

						if(row.getCell(5) != null) {
							leadMaster.setArea(row.getCell(5).toString());
						} else {
							throw new ResourceNotFoundException("'Area' Not Found of Student Name: - " + studentName + ": Uploaded Student Count - " + count);
						}

						if(row.getCell(6) != null) {
							leadMaster.setModeOfCourse(row.getCell(6).toString());
						} else {
							throw new ResourceNotFoundException("'Mode of Course' Not Found of Student Name: - " + studentName + ": Uploaded Student Count - " + count);
						}

						if(row.getCell(7) != null) {
							String catName = row.getCell(7).toString();
							CategoryMaster categoryMaster = categoryRepository.getCatIdByName(row.getCell(7).toString());
							if(categoryMaster!= null) {
								leadMaster.setCategoryMaster(categoryMaster);
							} else {
								throw new ResourceNotFoundException("'Category' Not Found of Student Name: - " + studentName + ": Uploaded Student Count - " + count);
							}
						}
						long id = 3;
						RemarkMaster remarkMaster = remarkJpaRepository.findById(id).orElse(null);
						leadMaster.setRemarkMaster(remarkMaster);
						leadMaster.setUpdatedOn(new Date());
						leadMaster.setDeletedFlag(true);
						leadMaster.setAssignLeadFlag(false);
						leadMaster.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
						fileuploadrepository.save(leadMaster);
					}
					count ++;
				}
			}
		}

		if(i == 1) {
			for(Row row:sheet) {
				InstituteMaster instituteMaster = new InstituteMaster();
				if(!row.getCell(0).toString().trim().equals("Client Name")) {
					String clientName;
					if(row.getCell(0) != null) {
					clientName = row.getCell(0).toString();
					} else {
					throw new ResourceNotFoundException("Client Name Not Found" + ": Uploaded Client Count - " + count);
					}

					String commitedLeads;
					if(row.getCell(1) != null) {
						commitedLeads = row.getCell(1).toString();
					} else {
					throw new ResourceNotFoundException("Client commited leads Not Found" + ": Uploaded Client Count - " + count);
					}

					String conversionPrecentage;
					if(row.getCell(2) != null) {
					conversionPrecentage = row.getCell(2).toString();
					} else {
					throw new ResourceNotFoundException("Client conversion percentage Not Found" + ": Uploaded Client Count - " + count);
					}

					String confirmAddmission;
					if(row.getCell(3) != null) {
					confirmAddmission = row.getCell(3).toString();
					} else {
					throw new ResourceNotFoundException("Client confirm addmission Not Found" + ": Uploaded Client Count - " + count);
					}

					String mobileNumber;
					if(row.getCell(4) != null) {
					mobileNumber = row.getCell(4).toString();
					} else {
					throw new ResourceNotFoundException("Client mobile number Not Found" + ": Uploaded Client Count - " + count);
					}

					String emailId;
					if(row.getCell(5) != null) {
					emailId = row.getCell(5).toString();
					} else {
					throw new ResourceNotFoundException("Client email id Not Found" + ": Uploaded Client Count - " + count);
					}

					String decisionMakerName;
					if(row.getCell(6) != null) {
					decisionMakerName = row.getCell(6).toString();
					} else {
					throw new ResourceNotFoundException("Client decision maker name Not Found" + ": Uploaded Client Count - " + count);
					}

					String address;
					if(row.getCell(7) != null) {
					address = row.getCell(7).toString();
					} else {
					throw new ResourceNotFoundException("Client address Not Found" + ": Uploaded Client Count - " + count);
					}

					String additionalCommitment;
					if(row.getCell(8) != null) {
					additionalCommitment = row.getCell(8).toString();
					} else {
					throw new ResourceNotFoundException("Client additional commitment Not Found" + ": Uploaded Client Count - " + count);
					}

					String category;
					if(row.getCell(9) != null) {
					category = row.getCell(9).toString();
					} else {
					throw new ResourceNotFoundException("Client category Not Found" + ": Uploaded Client Count - " + count);
					}

					instituteMaster.setInstituteName(clientName);
					instituteMaster.setEmailId(emailId);
					instituteMaster.setDesignMaker(decisionMakerName);
					instituteMaster.setContactNo(mobileNumber);
					instituteMaster.setAddress(address);
					instituteMaster.setCommitedLead(commitedLeads);
					instituteMaster.setAddmissionComitted(confirmAddmission);
					instituteMaster.setAdditionalCommited(additionalCommitment);
					instituteMaster.setCategoryMaster(categoryRepository.getCatIdByName(category));
					instituteMaster.setConversion(conversionPrecentage);
					instituteMaster.setDeletedFlag(true);
					instituteMaster.setUpdatedOn(new Date());

					instituteRepository.insertInsituteDao(instituteMaster);
					count ++;
				}
			}
		}
		return count;
	}

	private Workbook getWorkBook(MultipartFile file) {
		Workbook workbook = null;
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		try {
			assert extension != null;
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

	private void readDataFromCsv(MultipartFile file, int i) {
		
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

	private void readDataFromJson(MultipartFile file, int i) {
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