package com.upskillutoday.crmRoot.request;

import java.util.ArrayList;

public class VerifyLeadRes {

    private ArrayList<StudentWithInst> studentWithInsts;

    public VerifyLeadRes() {
    }

    public VerifyLeadRes(ArrayList<StudentWithInst> studentWithInsts) {
        this.studentWithInsts = studentWithInsts;
    }

    public ArrayList<StudentWithInst> getStudentWithInsts() {
        return studentWithInsts;
    }

    public void setStudentWithInsts(ArrayList<StudentWithInst> studentWithInsts) {
        this.studentWithInsts = studentWithInsts;
    }
}
