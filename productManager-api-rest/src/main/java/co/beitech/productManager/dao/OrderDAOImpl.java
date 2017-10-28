package co.beitech.productManager.dao;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import co.beitech.productManager.controller.CustomerController;
import co.beitech.productManager.model.Order;

/**
 * Basic implements DAO of Order 
 * 
 * 
 * @see AbstractSession
 * 
 * @author Freddy.Lemus
 *
 */
@Repository
@Transactional
public class OrderDAOImpl extends AbstractSession implements OrderDAO {

	static Logger log = Logger.getLogger(CustomerController.class.getName());

	
	/**
	 * 
	 * Save all Order and OrderDetails
	 *
	 * @param  Order to persist
	 * 
	 */
	public void saveOrderCustomer(Order orderCustomer) {

		em.persist(orderCustomer);
		em.flush();
		orderCustomer.getOrderDetails().forEach(orderDetail -> {
			em.persist(orderDetail);
		});

	}

	
	/**
	 * Get all Orders by date range
	 * 
	 * @param fromDate  
	 * 
	 * @param untilDate
	 * 
	 * @return List<Order>
	 * 
	 */
	public List<Order> getOrderByCustomerIdAndDate(int customerId, LocalDate fromDate, LocalDate untilDate) {

		return em.createNamedQuery("OrderCustomer.findByCustomAndDate", Order.class)
				.setParameter("customerId", new Integer(customerId))
				.setParameter("stDate", Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.setParameter("edDate", Date.from(untilDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
				.getResultList();
	}

}
