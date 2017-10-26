package co.beitech.productManager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import co.beitech.productManager.controller.CustomerController;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.OrderCustomer;

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
	public void saveOrderCustomer(OrderCustomer orderCustomer) {

		em.persist(orderCustomer);
		orderCustomer.getOrderDetails().forEach(orderDetail -> {
			em.flush();
			em.persist(orderDetail);
		});

	}

	/**
	 * Get all Order
	 */
	public List<OrderCustomer> getOrderCustomers() {
		return em.createNamedQuery("OrderCustomer.findAll", OrderCustomer.class).getResultList();
	}

}
