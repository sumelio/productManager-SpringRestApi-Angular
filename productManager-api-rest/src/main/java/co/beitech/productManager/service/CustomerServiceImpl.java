package co.beitech.productManager.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.beitech.productManager.dao.CustomerDAO;
import co.beitech.productManager.model.Customer;

/**
 * Simple Customer service
 * 
 * @author Freddy.Lemus
 *
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO _customerDao;

	/**
	 * Instance order business logical
	 * 
	 * 
	 */
	@Autowired
	private OrderService orderService;
	
	

	/**
	 * This method  gets customer information, without order
	 * 
	 */
	public List<Customer> getCustomers() {
		List<Customer> listCustomer = _customerDao.getCustomers();
		listCustomer.forEach(custom -> custom.setOrders(null));
		return _customerDao.getCustomers();
	}

	
	/**
	 * This method gets a  customer orders list by date range
	 * 
	 * @param customerId customer identifier
	 * 
	 * @param startDate from date 
	 * 
	 * @param endDate until date
	 * 
	 * @return Customer Customer object  
	 *  
	 */
	public Customer getCustomerOrderByAndDate(int customerId, LocalDate start, LocalDate end) {

		Customer customer = new Customer();

		if (_customerDao.isExistsCustomerById(customerId)) {
			customer = _customerDao.getCustomerById(customerId);
			customer.getOrders().addAll(orderService.getOrderByCustomerIdAndDate(customerId, start, end));

		}

		return customer;
	}

}
