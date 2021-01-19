package com.upskillutoday.crmRoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CountRemarkDto {
    private long remarkId;
    private String remarkName;
    private Long count;

    @Id
    @JsonIgnore
    private String id;

    public CountRemarkDto(long remarkId, String remarkName, Long count) {
        this.remarkId = remarkId;
        this.remarkName = remarkName;
        this.count = count;
    }

    public CountRemarkDto() {
    }

    public long getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(long remarkId) {
        this.remarkId = remarkId;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
