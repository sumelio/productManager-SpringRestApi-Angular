package co.beitech.productManager.service;

import java.util.Date;
import java.util.List;

import co.beitech.productManager.domain.Response;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.OrderCustomer;
import co.beitech.productManager.model.Product;

public interface OrderService {

	public Response saveOrderCustomers(OrderCustomer orderCustomer, List<Product> products);

	public List<OrderCustomer> getOrderCustomers();

	public List<OrderCustomer> getOrderCustomers(int customerId, Date start, Date end);

}
