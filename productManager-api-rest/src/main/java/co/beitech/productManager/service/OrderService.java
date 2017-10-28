package co.beitech.productManager.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import co.beitech.productManager.domain.Response;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order;
import co.beitech.productManager.model.Product;

public interface OrderService {

	public Response saveOrder(Order orderCustomer, List<Product> products);
 

	public List<Order> getOrderByCustomerIdAndDate(int customerId, LocalDate start, LocalDate end);

}
