package co.beitech.productManager.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order; 

@Repository
@Transactional
public class CustomerDAOImpl extends AbstractSession implements CustomerDAO {

 
	/**
	 *  Get all customers
	 */
	public List<Customer> getCustomers() {
		return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
	}
	
	/**
	 *  Exists Customer
	 */
	public boolean isExistsCustomerById(int customerId) {
		return ! em.createNamedQuery("Customer.findById", Customer.class).setParameter("id", customerId)
				.getResultList().isEmpty();

	}
	 
	
	public Customer getCustomerById(int customerId) {
		return em.createNamedQuery("Customer.findById", Customer.class).setParameter("id", customerId)
				.getSingleResult();

	}
	
 
	
	

}
