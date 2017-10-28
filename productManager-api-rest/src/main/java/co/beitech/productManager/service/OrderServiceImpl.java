package co.beitech.productManager.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.beitech.productManager.dao.CustomerDAO;
import co.beitech.productManager.dao.OrderDAO;
import co.beitech.productManager.domain.Response;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.Order;
import co.beitech.productManager.model.OrderDetail;
import co.beitech.productManager.model.Product;

/**
 * 
 * Service class contains business logical Order
 * 
 * 
 * @author freddy.lemus
 *
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	private static final String MAX_PRODUCT = "max.products";
	
	
	@Autowired
	private Environment env;
	
	@Autowired
	private OrderDAO _orderDAO;

	@Autowired
	private CustomerDAO _customerDao;

 

	/**
	 * This method creates an order.
	 * 
	 * Steps to create an order are as follows:
	 * <ul>
	 * <li>Validate the maximum amount of products</li>
	 * <li>Validate the products available by customer</li>
	 * <li>Create the order and the orders detail</li>
	 * </ul>
	 * 
	 * @param orderCustomer
	 *            Customer order
	 * 
	 * @param orderProducts
	 *            list of order products
	 * 
	 * @return Response Result operation
	 * 
	 * 
	 */
	public Response saveOrder(Order orderCustomer, List<Product> orderProducts) {

		try {
            
			int maxProduct = new Integer(env.getProperty(MAX_PRODUCT));
			
            /* validate the maximum amount of products */
			if (orderProducts.size() > maxProduct) {
				throw new Exception("The amount of products must be less or equal to " + maxProduct);
			}

			
			/*  Validate customer */
			int customerId = orderCustomer.getCustomer().getCustomerId();

			if (_customerDao.isExistsCustomerById(customerId)) {
				Customer customer = _customerDao.getCustomerById(customerId);
				orderCustomer.setCustomer(customer);
			} else {
				throw new Exception("The customer with ID " + customerId + " does not exist");
			}

			
			
			List<Product> listAvailableProduct = orderCustomer.getCustomer().getAvailableProducts();

			/*  Validate the products available by customer */
			for (Product orderProduct : orderProducts) {

				// Count products found in listAvailableProduct
				long countAvailableProducts = listAvailableProduct
						.stream()
						.filter(availableProduct -> 
						            availableProduct.getProductId() == orderProduct.getProductId())
						.count();
				
				if (countAvailableProducts == 0) {
					throw new Exception("The product with id " + orderProduct.getProductId()
							+ "  is not available for  '" + orderCustomer.getCustomer().getName() + " '");
				}
			}

			// Create OrderDetails
			listAvailableProduct.forEach(availableProduct -> {

				// Group by products and count products by id
				Long countProductById = orderProducts
						.stream()
						.filter(orderProduct -> 
						          orderProduct.getProductId() == availableProduct.getProductId())
						.count();
				
				if (countProductById > 0) {
					
					OrderDetail orderDetail = new OrderDetail();

					// Calculate total price by orderDetail
					BigDecimal totalOrderDetail = availableProduct.getPrice()
							.multiply(BigDecimal.valueOf( countProductById));
					
					orderDetail.setPrice(totalOrderDetail);
					orderDetail.setProductescription(countProductById + " X " + availableProduct.getName());
					orderDetail.setOrder(orderCustomer);
					orderCustomer.getOrderDetails().add(orderDetail);
					
				}
			});
			
			// Set date
			orderCustomer.setOrderTime(new Date());

			
			// Persist objects
			_orderDAO.saveOrderCustomer(orderCustomer);
			

		} catch (Exception e) {
			return new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}

		return new Response("OK", HttpStatus.OK.value());

	}

	/**
	 * This method gets orders by Customer and dates
	 * 
	 * @param fromDate
	 * 
	 * @param untilDate
	 * 
	 * @return List<Order>
	 * 
	 */
	public List<Order> getOrderByCustomerIdAndDate(int customerId, LocalDate fromDate, LocalDate untilDate) {
		
		return _orderDAO.getOrderByCustomerIdAndDate(customerId, fromDate, untilDate.plusDays(1));
	}

}
