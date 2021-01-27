package com.upskillutoday.crmRoot.request;

import java.util.ArrayList;
import java.util.List;

public class StudentWithInst {

    private Long studentId;
    private List<Long> instituteIds;

    public StudentWithInst() {
    }

    public StudentWithInst(Long studentId, ArrayList<Long> instituteIds) {
        this.studentId = studentId;
        this.instituteIds = instituteIds;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<Long> getInstituteIds() {
        return instituteIds;
    }

    public void setInstituteIds(List<Long> instituteIds) {
        this.instituteIds = instituteIds;
    }
}
