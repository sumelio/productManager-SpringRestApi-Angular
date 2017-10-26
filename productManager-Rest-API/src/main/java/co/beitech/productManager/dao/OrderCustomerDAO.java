package co.beitech.productManager.dao;

import java.util.List;

import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.OrderCustomer;
 

public interface OrderCustomerDAO {
	public void saveOrderCustomer(OrderCustomer orderCustomer);
	
	public List<OrderCustomer> getOrderCustomers();

}
