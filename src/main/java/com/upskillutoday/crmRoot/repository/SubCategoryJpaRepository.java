package com.upskillutoday.crmRoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.upskillutoday.crmRoot.model.SubCategoryMaster;

public interface SubCategoryJpaRepository extends JpaRepository<SubCategoryMaster, Long> {

	SubCategoryMaster findBySubCategoryId(Long subCategoryId);

	SubCategoryMaster findBySubCategoryNameAndDeletedFlag(String string, boolean b);

}
