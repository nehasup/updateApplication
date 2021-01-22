package com.upskillutoday.crmRoot.response;

import com.upskillutoday.crmRoot.dto.CountRemarkDto;

import java.util.List;

public class DailyReportModelDto {

    private Long employeeId;
    private String employeeName;
    private List<CountRemarkDto> countRemarkDtoList;

    public DailyReportModelDto(Long employeeId, String employeeName, List<CountRemarkDto> countRemarkDtoList) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.countRemarkDtoList = countRemarkDtoList;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<CountRemarkDto> getCountRemarkDtoList() {
        return countRemarkDtoList;
    }

    public void setCountRemarkDtoList(List<CountRemarkDto> countRemarkDtoList) {
        this.countRemarkDtoList = countRemarkDtoList;
    }
}
