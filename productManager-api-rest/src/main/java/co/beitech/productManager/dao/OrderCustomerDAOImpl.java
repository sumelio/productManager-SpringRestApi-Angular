package co.beitech.productManager.dao;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import co.beitech.productManager.controller.CustomerController;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order;

@Repository
@Transactional
public class OrderCustomerDAOImpl implements OrderCustomerDAO {

	static Logger log = Logger.getLogger(CustomerController.class.getName());

	private EntityManager em = null;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	/**
	 * Save all Order and OrderDetails
	 */
	public void saveOrderCustomer(Order orderCustomer) {

		em.persist(orderCustomer);
		em.flush();
		orderCustomer.getOrderDetails().forEach(orderDetail -> {
			em.persist(orderDetail);
		});

	}

	/**
	 * Get all Order
	 */
	public List<Order> getOrderCustomers() {
		return em.createNamedQuery("OrderCustomer.findAll", Order.class).getResultList();
	}

	/**
	 * Get all Order by Date
	 */
	public List<Order> getOrderCustomers(int customerId, Date frmDate, Date enDate) {
		
	 	
		
		return em.createNamedQuery("OrderCustomer.findByCustomAndDate", Order.class)
				.setParameter("customerId", new Integer(customerId)).setParameter("stDate", frmDate)
				.setParameter("edDate",  enDate ).getResultList();
	}

}
