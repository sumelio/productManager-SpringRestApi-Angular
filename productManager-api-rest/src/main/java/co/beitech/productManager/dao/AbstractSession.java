package co.beitech.productManager.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSession {
	
	
	protected EntityManager em = null;

	@PersistenceContext
	private void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	

}
