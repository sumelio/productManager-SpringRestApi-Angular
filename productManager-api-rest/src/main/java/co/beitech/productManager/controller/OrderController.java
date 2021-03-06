package co.beitech.productManager.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.beitech.productManager.domain.OrderCustomers;
import co.beitech.productManager.domain.Response;
import co.beitech.productManager.service.OrderService;

/**
 * 
 * This class is an order controller for API REST.
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
	
	

	/**
	 * This method creates an order
	 * 
	 * @param OrderCustomers Object contains Order and OrderProducts
	 * 
	 *            This object contains an object Order and a list of Products
	 *            
	 *            
	 *            Request example:
	 *            
	 *            {
	 *              "order": { 
	 *                "deliveryAddress": "15 Queens Park Road, W32 YYY, UK", 
	 *                "customer": {
	 *                  "customerId": 1
	 *                }
	 *              },
	 *              "orderProducts": [
	 *                {
	 *                  "productId": 1
	 *                }, 
	 *                    {
	 *                  "productId": 1
	 *                },
	 *                    {
	 *                  "productId": 2
	 *                }
	 *              ]
	 *            }
	 * 
	 * @return ResponseEntity Return code and description. 
	 *   
	 *    Response example :
	 *    
	 *    {
	 *        "description": "OK",
	 *        "code": 200
	 *    }
	 */
	@PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Response> createOrder(@RequestBody OrderCustomers orderCustomers) {

		Response response = orderService.saveOrder(orderCustomers.getOrder(),
				orderCustomers.getOrderProducts());

		return new ResponseEntity<Response>(response, HttpStatus.valueOf(response.getCode()));

	}

 
	

}
