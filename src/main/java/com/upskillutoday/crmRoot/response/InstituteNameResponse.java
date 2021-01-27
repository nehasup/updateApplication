package com.upskillutoday.crmRoot.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstituteNameResponse {

    @Id
    private Long id;

    private String instituteName;

    public InstituteNameResponse(Long id, String instituteName) {
        this.id = id;
        this.instituteName = instituteName;
    }

    public InstituteNameResponse() {

    }

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
