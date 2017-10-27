package co.beitech.productManager.dao;

import java.util.Date;
import java.util.List;

import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order;
 

public interface OrderDAO {
	public void saveOrderCustomer(Order orderCustomer);
	 
	public List<Order> getOrderByCustomerIdAndDate(int customerId, Date frmDate, Date enDate);

}
