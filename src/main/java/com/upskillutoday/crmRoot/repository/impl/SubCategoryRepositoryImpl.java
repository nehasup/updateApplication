package com.upskillutoday.crmRoot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.SubCategoryMaster;
import com.upskillutoday.crmRoot.repository.SubCategoryRepository;


@Repository
@Transactional
public class SubCategoryRepositoryImpl implements SubCategoryRepository {
	
	@Autowired
	@PersistenceContext   
	private EntityManager entityManager;

	@Override
	public boolean insertSubCategoryDao(SubCategoryMaster subCategory) {
		 try {
	            entityManager.persist(subCategory);
	            return true;
	        } catch (Exception e) {	   
	        	e.printStackTrace();
	        }
	            return false;
	}

	@Override
	public List getAllSubCategDao() {
	    List<String> list = null;
        try {
            Query query = entityManager.createQuery("Select NEW com.upskillutoday.crmRoot.response.SubCategoryResponse(sub.subCategoryId as subCategoryId,sub.subCategoryName as subCategoryName,c.categoryId as categoryId,c.categoryName as categoryName ) from SubCategoryMaster as sub inner join sub.category as c where sub.deletedFlag=1");
             list = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public SubCategoryMaster getBySubCatIdDao(SubCategoryMaster subCategory) {
		try{
	       // Query query = entityManager.createQuery("select sb.subCategoryId as subCategoryId,sb.subCategoryName as subCategoryName,c.categoryId as categoryId,c.categoryName as categoryName from SubCategory as sb INNER JOIN sb.Category as c WHERE subCategoryId=:id");
	        Query query = entityManager.createQuery("from SubCategoryMaster WHERE subCategoryId=:id");
	        query.setParameter("id",subCategory.getSubCategoryId());

	        SubCategoryMaster subCategory2 = (SubCategoryMaster) query.getSingleResult();      
	        	        
	        return subCategory2;
	    }
	        catch (Exception e)
	    {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	@Override
	public CategoryMaster getCategorybyidDao(Long categoryId) {
		
		try{
	        Query query = entityManager.createQuery("From CategoryMaster where categoryId=:id");

		        query.setParameter("id",categoryId);

		        CategoryMaster category1= (CategoryMaster) query.getSingleResult();
		     	
		        return category1;
		    }
	        catch (Exception e)
		    {
		        e.printStackTrace();
	        return null;
		    }
	}

	@Override
	public boolean updateSubCategoryRepository(SubCategoryMaster subCategory) {
		 try {
	            entityManager.merge(subCategory);
	        /*   CriteriaBuilder criteriaBuilder =entityManager.getCriteriaBuilder();
	            CriteriaQuery query=criteriaBuilder.createQuery(Institute.class);

	            List<> list=query.getOrderList();*/

	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	}
	
	@Override
	
	public List<SubCategoryMaster> getSubcategoryByCategoryIdDao(CategoryMaster categoryMaster) {
		List<SubCategoryMaster> list = null;
		System.out.println("id"+categoryMaster.getCategoryId());
		try{
	       // Query query = entityManager.createQuery("select sb.subCategoryId as subCategoryId,sb.subCategoryName as subCategoryName,c.categoryId as categoryId,c.categoryName as categoryName from SubCategory as sb INNER JOIN sb.Category as c WHERE subCategoryId=:id");
	        Query query = entityManager.createQuery("select sub from SubCategoryMaster as sub inner join sub.category as cat WHERE cat.categoryId=:id");
	        query.setParameter("id",categoryMaster.getCategoryId());
	        list = query.getResultList();

	       // SubCategoryMaster subCategory2 = (SubCategoryMaster) query.getSingleResult();      
	        	        
	        return list;
	    }
	        catch (Exception e)
	    {
	        e.printStackTrace();
	        return null;
	    }
	}

	
	
}
