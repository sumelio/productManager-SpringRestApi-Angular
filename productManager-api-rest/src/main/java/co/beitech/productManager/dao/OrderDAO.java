package co.beitech.productManager.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import co.beitech.productManager.model.Order;

/**
 * Basic DAO interface
 * 
 * @see SessionFactory
 */

public interface OrderDAO {
	public void saveOrderCustomer(Order orderCustomer);

	public List<Order> getOrderByCustomerIdAndDate(int customerId, LocalDate frmDate, LocalDate enDate);

}
