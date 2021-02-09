package com.upskillutoday.crmRoot.repository;

import java.util.List;
import java.util.Optional;

import com.upskillutoday.crmRoot.model.RoleMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository {

	boolean insertRoleDao(RoleMaster role);

	List getRoleListDao();

	RoleMaster getroleByid(Long roleId);
	
	Optional<RoleMaster> findByRoleName(String roleName);

	Long getRoleIdFromUserId(Long userId);
}

@Repository
@Transactional
class RoleRepositoryImpl implements RoleRepository {

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean insertRoleDao(RoleMaster role) {

		try {
			//System.out.println("dd22222");
			entityManager.persist(role);
			return true;
		}
		catch (Exception e){
			return false;
		}
		finally {
			entityManager.close();
		}


	}

	@Override
	public List getRoleListDao() {
		List<String> list = null;
		try {
			Query query = entityManager.createQuery("Select role from RoleMaster as role where role.deletedFlag=1",RoleMaster.class);
			list = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public RoleMaster getroleByid(Long roleId) {

		try{
			Query query = entityManager.createQuery("From RoleMaster where roleId=:id");

			query.setParameter("id",roleId);

			RoleMaster roleMaster= (RoleMaster) query.getSingleResult();

			return roleMaster;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<RoleMaster> findByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getRoleIdFromUserId(Long userId) {
		Query query = entityManager.createQuery("SELECT role.roleId FROM UserRole as u inner join u.users as user inner join u.roles as role WHERE user.userId = " + userId);
		return (Long) query.getSingleResult();
	}
}
