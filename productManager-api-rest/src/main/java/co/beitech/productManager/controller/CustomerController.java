package co.beitech.productManager.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order;
import co.beitech.productManager.service.CustomerService;
import co.beitech.productManager.service.OrderService;

/**
 * This class is a customer controller for API REST
 * 
 * @author falemus
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class CustomerController {

	static Logger log = Logger.getLogger(CustomerController.class.getName());

	@Autowired
	private CustomerService customerService;

	@Autowired
	private OrderService orderService;

	/**
	 * This method gets all customers
	 * 
	 * @return
	 */
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getCustomers() {
		log.info("Get all customers ");
		return new ResponseEntity<List<Customer>>(customerService.getCustomers(), HttpStatus.OK);
	}

	/**
	 * THis method get orders by Customer and date
	 * 
	 * @param customerId
	 *            Customer id
	 * @param startDate
	 *            Date in format yyyy-MM-dd
	 * @param endDate
	 *            Date in format yyyy-MM-dd
	 * @return  If exists order return HTTP 200 and list.
	 *          If nothing order return 404, "Not Found"
	 *           
	 * @throws ParseException
	 */
	@GetMapping(value = "/customer/{customerId}/order", produces = "application/json")
	public ResponseEntity<List<Order>> getOrder(@PathVariable(value = "customerId") int customerId,
			@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
			throws ParseException {

		ResponseEntity<List<Order>> responseEntity = null;

		List<Order> listOrder = orderService.getOrderCustomers(customerId, startDate, endDate);
		if (!listOrder.isEmpty()) {
			responseEntity = new ResponseEntity<List<Order>>(listOrder, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<List<Order>>(listOrder, HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

}
