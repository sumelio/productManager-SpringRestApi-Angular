package co.beitech.productManager.dao;

import java.util.Date;
import java.util.List;

import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order;
 

public interface OrderCustomerDAO {
	public void saveOrderCustomer(Order orderCustomer);
	
	public List<Order> getOrderCustomers();
	public List<Order> getOrderCustomers(int customerId, Date start, Date end);

}
