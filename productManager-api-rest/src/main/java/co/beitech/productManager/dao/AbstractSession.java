package co.beitech.productManager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractSession {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	

}
