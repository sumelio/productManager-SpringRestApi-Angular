package co.beitech.productManager.service;

import java.util.Date;
import java.util.List;

import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order;

public interface CustomerService  {
	
	public List<Customer> getCustomers();
	
	public Customer getCustomerOrderByAndDate(int customerId, Date start, Date end);
	

}
