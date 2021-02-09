package com.upskillutoday.crmRoot.repository.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.CityMaster;

@Repository
public interface CityJpaRepository extends JpaRepository<CityMaster, Long> {

}
