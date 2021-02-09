package com.upskillutoday.crmRoot.repository.JPARepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.CategoryMaster;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryMaster, Long>{
	CategoryMaster findByCategoryId(Long categoryId);
	CategoryMaster findByCategoryNameAndDeletedFlag(String string, boolean b);
}
