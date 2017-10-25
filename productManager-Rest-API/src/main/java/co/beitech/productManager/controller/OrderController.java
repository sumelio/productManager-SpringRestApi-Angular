package co.beitech.productManager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.beitech.productManager.modal.Customer;

@RestController
@RequestMapping(value = "/v1")
public class OrderController {
	
	@PostMapping("/order")
	public ResponseEntity<List<Customer>> getCustomers() {
		 return null;

	}

}
