package com.upskillutoday.crmRoot.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.CategoryCity;
import com.upskillutoday.crmRoot.repository.CategoryRepository;

@Repository
@Transactional
public class CategoryRepositoryImpl implements CategoryRepository {

	@Autowired
	@PersistenceContext   
	private EntityManager entityManager;

	@Override
	public boolean insertCategoryDao(CategoryMaster category) {
		 try {
	            entityManager.persist(category);
	            return true;
	        } catch (Exception e) {	   
	        	e.printStackTrace();
	        } finally {
	            entityManager.close();
	        }
	            return false;

	        }
	

	@Override
	public boolean insertCategoryCityDao(CategoryCity categoryCity) {
		 try {
	            entityManager.persist(categoryCity);
	            return true;
	        } catch (Exception e) {	   
	        	e.printStackTrace();
	        } finally {
	            entityManager.close();
	        }
	            return false;

	        }



//	@Override
//	public Category getCategorybyNameDao(String categoryName) {
//		
//		try{
//		        Query query = entityManager.createQuery("From Category where categoryName=:categoryName");
//
//		        query.setParameter("categoryName",categoryName);
//
//		        Category category1= (Category) query.getSingleResult();
//		     
//		
//
//		        return category1;
//		    }
//		        catch (Exception e)
//		    {
//		        e.printStackTrace();
//		        return null;
//		    }
//	}
	
	@Override
    public List getCategoryListDao() {
        List<String> list = null;
        try {
            Query query = entityManager.createQuery("Select cat from CategoryMaster as cat where cat.deletedFlag=1",CategoryMaster.class);
             list = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }



	@Override
    public boolean getRecordByCategoryIdDao(CategoryMaster category) {
       
try{
        Query query = entityManager.createQuery("From CategoryMaster where categoryId=:id");

        query.setParameter("id",category.getCategoryId());

        
        CategoryMaster category2=(CategoryMaster) query.getSingleResult();
        category.setCategoryName(category2.getCategoryName());
        
        return true;
    }
        catch (Exception e)
    {
        e.printStackTrace();
        return false;
    }
}

	@Override
	public CategoryMaster getCatIdByName(String name) {
		for(CategoryMaster categoryMaster : ((List<CategoryMaster>)this.getCategoryListDao())) {
			if(categoryMaster.getCategoryName().equalsIgnoreCase(name))
				return categoryMaster;
		}
		return null;
	}

}
