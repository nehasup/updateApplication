package com.upskillutoday.crmRoot.repository;

import com.upskillutoday.crmRoot.model.InstituteLead;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteLeadRepository {
    boolean insertInstituteLead(InstituteLead instituteLead);
}
