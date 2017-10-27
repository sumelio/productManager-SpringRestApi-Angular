package co.beitech.productManager.dao;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import co.beitech.productManager.controller.CustomerController;
import co.beitech.productManager.model.Order;

@Repository
@Transactional
public class OrderDAOImpl extends AbstractSession implements OrderDAO {

	static Logger log = Logger.getLogger(CustomerController.class.getName());



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
	 * Get all Order by Date
	 */
	public List<Order> getOrderByCustomerIdAndDate(int customerId, Date frmDate, Date enDate) {
		
	 	
		
		return em.createNamedQuery("OrderCustomer.findByCustomAndDate", Order.class)
				.setParameter("customerId", new Integer(customerId)).setParameter("stDate", frmDate)
				.setParameter("edDate",  enDate ).getResultList();
	}

}
