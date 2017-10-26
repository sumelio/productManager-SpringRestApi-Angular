package co.beitech.productManager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.beitech.productManager.model.Customer; 

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/**
	 *  Get all customers
	 */
	public List<Customer> getCustomers() {
		return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
	}
	
	public Customer getCustomerById(int customerId) {
		return em.createNamedQuery("Customer.findById", Customer.class).setParameter("id", customerId)
				.getSingleResult();

	}

}
