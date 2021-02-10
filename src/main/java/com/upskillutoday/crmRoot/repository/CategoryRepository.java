package com.upskillutoday.crmRoot.repository;

import java.util.List;
import com.upskillutoday.crmRoot.model.CategoryMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryRepository {
	boolean insertCategoryDao(CategoryMaster category);
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
	public List getCategoryListDao() {
			return entityManager.createQuery(
					"Select cat from CategoryMaster as cat where cat.deletedFlag = true"
					,CategoryMaster.class
			).getResultList();
	}

	@Override
	public boolean getRecordByCategoryIdDao(CategoryMaster category) {
			return entityManager.createQuery(
					"SELECT cat.deletedFlag FROM CategoryMaster as cat " +
							"where cat.categoryId = " + category.getCategoryId(),
						Boolean.class
			).getSingleResult();
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

