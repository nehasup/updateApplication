package com.upskillutoday.crmRoot.repository;

import java.util.List;

import com.upskillutoday.crmRoot.model.CategoryMaster;
import com.upskillutoday.crmRoot.model.CategoryCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryRepository {
	boolean insertCategoryDao(CategoryMaster category);
	boolean insertCategoryCityDao(CategoryCity categoryCity);
	List getCategoryListDao();
	boolean getRecordByCategoryIdDao(CategoryMaster category);
	CategoryMaster getCatIdByName(String name);
}

@Repository
@Transactional
class CategoryRepositoryImpl implements CategoryRepository {

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
		List<CategoryMaster> categoryMasters = entityManager.createQuery("SELECT cat FROM CategoryMaster as cat", CategoryMaster.class).getResultList();
		name = name.toLowerCase();
		for(CategoryMaster categoryMaster : categoryMasters) {
			if(name.equalsIgnoreCase(categoryMaster.getCategoryName().toLowerCase()))
				return categoryMaster;

			if(categoryMaster.getCategoryName().toLowerCase().contains(name))
				return categoryMaster;

			if(name.contains(categoryMaster.getCategoryName().toLowerCase()))
				return categoryMaster;

		}
		return null;
	}

}

