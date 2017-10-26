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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.beitech.productManager.domain.OrderCustomers;
import co.beitech.productManager.domain.Response;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.OrderCustomer;
import co.beitech.productManager.model.OrderDetail;
import co.beitech.productManager.model.Product;
import co.beitech.productManager.service.CustomerService;
import co.beitech.productManager.service.OrderService;

/**
 * 
 * This class is a order controller for API REST.
 * 
 * @author freddy.lemus
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class OrderController {

	static Logger log = Logger.getLogger(OrderController.class.getName());

	/**
	 * Service has business logical
	 */
	@Autowired
	private OrderService orderService;

	@Autowired
	private CustomerService customerService;

	/**
	 * This method creates an order
	 * 
	 * @param orderCustomers
	 *            This object contains an object Order and a list of Products
	 * 
	 * @return ResponseEntity
	 */
	@PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> createOrder(@RequestBody OrderCustomers orderCustomers) {

		Response response = orderService.saveOrderCustomers(orderCustomers.getOrderCustomer(),
				orderCustomers.getProducts());

		return new ResponseEntity<Response>(response, HttpStatus.valueOf(response.getCode()));

	}

	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/order", produces = "application/json")
	public ResponseEntity<List<OrderCustomer>> getOrder() {
		ResponseEntity<List<OrderCustomer>> response = null;

		List<OrderCustomer> listOrder = orderService.getOrderCustomers();
		if (listOrder.isEmpty()) {
			response = new ResponseEntity<>(orderService.getOrderCustomers(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(orderService.getOrderCustomers(), HttpStatus.NOT_FOUND);
		}

		return response;
	}

	/**
	 * 
	 * @param customerId
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	@GetMapping(value = "/order/customer", produces = "application/json")
	public ResponseEntity<List<OrderCustomer>> getOrder(@RequestParam(value = "customerId") int customerId,
			@RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
			@RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
			throws ParseException {

		ResponseEntity<List<OrderCustomer>> responseEntity = null;

		List<OrderCustomer> listOrder = orderService.getOrderCustomers(customerId, startDate, endDate);
		if ( ! listOrder.isEmpty() ) {
			responseEntity = new ResponseEntity<List<OrderCustomer>>(listOrder, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<List<OrderCustomer>>(listOrder, HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

}
