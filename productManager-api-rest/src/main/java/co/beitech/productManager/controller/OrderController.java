package co.beitech.productManager.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.beitech.productManager.domain.OrderCustomers;
import co.beitech.productManager.domain.Response;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.OrderCustomer;
import co.beitech.productManager.model.OrderDetail;
import co.beitech.productManager.model.Product;
import co.beitech.productManager.service.OrderService;

@RestController
@RequestMapping(value = "/v1")
public class OrderController {
	static Logger log = Logger.getLogger(OrderController.class.getName());

	@Autowired
	OrderService orderService;

	@PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> createOrder(@RequestBody OrderCustomers orderCustomers) {
		Response response = orderService.saveOrderCustomers(orderCustomers.getOrderCustomer(),
				orderCustomers.getProducts());
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping(path = "/order", produces = "application/json")
	public ResponseEntity<List<OrderCustomer>> getOrder() {
		return new ResponseEntity<>(orderService.getOrderCustomers(), HttpStatus.OK);
	}

}
