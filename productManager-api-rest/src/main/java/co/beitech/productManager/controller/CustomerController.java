package co.beitech.productManager.controller;

import java.text.ParseException;
import java.time.LocalDate;
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
import co.beitech.productManager.service.CustomerService;

/**
 * Simple Customer controller for API REST version 1
 * 
 * @author freddy.lemus
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class CustomerController {

	static Logger log = Logger.getLogger(CustomerController.class.getName());

	@Autowired
	private CustomerService customerService;

 

	/**
	 * This method gets all customers
	 * 
	 * Method GET HTTP
	 * 
	 * @return Contain JSON
	 *         Example:
	 *         [
	 *           {
 	 *            "customerId": 1,
	 *             "email": "manny@Bharma.com",
	 *             "name": "Manny Bharma"
	 *           },
	 *           {
	 *             "customerId": 2,
	 *             "email": "alan@Briggs.com",
	 *             "name": "Alan Briggs"
	 *           },
	 *           {
	 *             "customerId": 3,
	 *             "email": "mike@Simm.com",
	 *             "name": "Mike Simm"
	 *           }
	 *         ]
	 */
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getCustomers() {
		log.info("Get all customers ");
		return new ResponseEntity<List<Customer>>(customerService.getCustomers(), HttpStatus.OK);
	}

	/**
	 * This method gets orders by Customer and date
	 * 
	 * Method GET HTTP
	 * 
	 *  URL:
	 *  http://hostname:port/app/v1/customer/{customerId}/order?startDate={fromDate}&endDate={untilDate}
	 *  
	 * @param customerId
	 *            Customer id
	 *            
	 * @param fromDate
	 *            Start date in format yyyy-MM-dd
	 *            
	 * @param untilDate
	 *             End date in format yyyy-MM-dd
	 *            
	 * @return  Customer ResponseEntity
	 *           
	 *          If exists order return HTTP 200 and list
	 *          .
	 *          If Order not found return 404 "Not Found"
	 *          
	 *          Example: response JSON HTTP 200:
	 *          
	 *           {
	 *             "customerId": 1,
	 *             "email": "manny@Bharma.com",
	 *             "name": "Manny Bharma",
	 *             "orders": [
	 *               {
	 *                 "orderId": 1,
	 *                 "deliveryAddress": "Adress",
	 *                 "orderTime": "2017-10-26",
	 *                 "orderDetails": [
	 *                   {
	 *                     "orderDetailId": 1,
	 *                     "price": 300.15,
	 *                     "productDescription": "3 X Product A"
	 *                   }
	 *                 ],
	 *                 "totalPrice": 600.2
	 *               },
	 *               {
	 *                 "orderId": 2,
	 *                 "deliveryAddress": "15 Queens Park Road, W32 YYY, UK",
	 *                 "orderTime": "2017-10-26",
	 *                 "orderDetails": [
	 *                   {
	 *                     "orderDetailId": 3,
	 *                     "price": 200.1,
	 *                     "productDescription": "2 X Product A"
	 *                   }
	 *                 ],
	 *                 "totalPrice": 400.2
	 *               }
	 *             ]
	 *           }
	 *           
	 * @throws ParseException
	 * 
	 */
	@GetMapping(value = "/customer/{customerId}/order", produces = "application/json")
	public ResponseEntity<Customer> getOrder(@PathVariable(value = "customerId") int customerId,
			@RequestParam(value = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@RequestParam(value = "untilDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate untilDate)
			throws ParseException {

		ResponseEntity<Customer> responseEntity;

		Customer customer = customerService.getCustomerOrderByAndDate(customerId, fromDate, untilDate);
		
		if ( ! customer.getOrders().isEmpty()) {
			responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

}
