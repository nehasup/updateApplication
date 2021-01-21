package com.upskillutoday.crmRoot.request;

import java.util.ArrayList;

public class VerifyLeadRes {

    private ArrayList<Long> stduentId;

    public VerifyLeadRes() {
    }

    public VerifyLeadRes(ArrayList<Long> stduentId) {
        this.stduentId = stduentId;
    }

    public ArrayList<Long> getStduentId() {
        return stduentId;
    }

    public void setStduentId(ArrayList<Long> stduentId) {
        this.stduentId = stduentId;
    }
}
