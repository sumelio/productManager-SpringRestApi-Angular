package co.beitech.productManager.dao;

import java.util.List;

import co.beitech.productManager.model.Customer;
 

public interface CustomerDAO {
	public List<Customer> getCustomers();
	public Customer getCustomerById(int customerId);
	public boolean isExistsCustomerById(int customerId);
	

}
