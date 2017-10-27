package co.beitech.productManager.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.beitech.productManager.dao.CustomerDAO;
import co.beitech.productManager.model.Customer;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO _customerDao;

	/**
	 * Service has business logical
	 */
	@Autowired
	private OrderService orderService;

	/**
	 * This method only gets customer information, without order
	 */
	public List<Customer> getCustomers() {
		List<Customer> listCustomer = _customerDao.getCustomers();
		listCustomer.forEach(custom -> custom.setOrders(null));
		return _customerDao.getCustomers();
	}

	/**
	 * This method gets a  customer orders list by date
	 */
	public Customer getCustomerOrderByAndDate(int customerId, Date start, Date end) {

		Customer customer = new Customer();

		if (_customerDao.isExistsCustomerById(customerId)) {
			customer = _customerDao.getCustomerById(customerId);
			customer.getOrders().addAll(orderService.getOrderByCustomerIdAndDate(customerId, start, end));

		}

		return customer;
	}

}
