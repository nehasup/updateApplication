package com.upskillutoday.crmRoot.repository;

import com.upskillutoday.crmRoot.model.EmpCategy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Added By Laukik
@Repository
public interface EmpCategyRepository extends CrudRepository<EmpCategy, Long> {
}
