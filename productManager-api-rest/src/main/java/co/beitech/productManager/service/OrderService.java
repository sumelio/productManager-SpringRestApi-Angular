package co.beitech.productManager.service;

import java.util.Date;
import java.util.List;

import co.beitech.productManager.domain.Response;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order;
import co.beitech.productManager.model.Product;

public interface OrderService {

	public Response saveOrderCustomers(Order orderCustomer, List<Product> products);

	public List<Order> getOrderCustomers();

	public List<Order> getOrderCustomers(int customerId, Date start, Date end);

}
