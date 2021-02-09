package com.upskillutoday.crmRoot.repository.JPARepository;

import com.upskillutoday.crmRoot.model.EmpCategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Added By Laukik
@Repository
public interface EmpCategoryJpaRepository extends JpaRepository<EmpCategy, Long> {
}
