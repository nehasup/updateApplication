package com.upskillutoday.crmRoot.repository.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upskillutoday.crmRoot.model.RemarkMaster;

public interface RemarkJpaRepository extends JpaRepository<RemarkMaster, Long> {

	RemarkMaster findByRemarkId(Long remarkId);

	

}
