package co.beitech.productManager.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.beitech.productManager.model.Customer; 

/**
 * Basic implements DAO of Customer 
 * 
 * 
 * @see AbstractSession
 * 
 * @author Freddy.Lemus
 *
 */
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
	 
	
	/**
	 *  Get CUtomer by id
	 *  
	 */
	public Customer getCustomerById(int customerId) {
		return em.createNamedQuery("Customer.findById", Customer.class).setParameter("id", customerId)
				.getSingleResult();

	}
	
 
	
	

}
