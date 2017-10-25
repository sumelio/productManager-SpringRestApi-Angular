package co.beitech.productManager.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.beitech.productManager.modal.Customer;
import co.beitech.productManager.service.CustomerService;

 
/**
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
	

	/**
	 * Get all customers
	 * @return
	 */
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getCustomers() {
		log.info("Get all customers ");
		return new ResponseEntity<List<Customer>>(customerService.getCustomers(), HttpStatus.OK);
	}

}
