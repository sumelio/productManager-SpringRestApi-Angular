package co.beitech.productManager.service;

import java.math.BigDecimal;
import java.util.Calendar;
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
 * Main business logical
 * 
 * @author freddy.lemus
 *
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	private Environment env;

	private static final String MAX_PRODUCT = "max.products";
	
	
	@Autowired
	private OrderDAO _orderDAO;

	@Autowired
	private CustomerDAO _customerDao;

 

	/**
	 * This method creates an order. 
	 * Steps to create an order are as follows:
	 *     First: Validate the maximum products 
	 *     Second: Validate the available products by customer 
	 *     Third: Create the order and the orders detail.
	 * 
	 */
	public Response saveOrder(Order orderCustomer, List<Product> products) {

		try {
            
			int maxProduct = new Integer(env.getProperty(MAX_PRODUCT));
			
            // Validate max 5 products
			if (products.size() > maxProduct) {
				throw new Exception("The amount of products must be less or equal to " + maxProduct);
			}

			// validate exists customer
			int customerId = orderCustomer.getCustomer().getCustomerId();

			if (_customerDao.isExistsCustomerById(customerId)) {
				Customer customer = _customerDao.getCustomerById(customerId);
				orderCustomer.setCustomer(customer);
			} else {
				throw new Exception("The customer with ID " + customerId + " does not exist");
			}

			List<Product> listAvailableProduct = orderCustomer.getCustomer().getAvailableProducts();

			// Validate: Available products by Customer
			for (Product product : products) {

				// Count products found in listAvailableProduct
				long countAvailableProducts = listAvailableProduct.stream()
						.filter(prod -> prod.getProductId() == product.getProductId()).count();
				if (countAvailableProducts == 0) {
					throw new Exception("The product with id " + product.getProductId()
							+ "  is not available for  '" + orderCustomer.getCustomer().getName() + " '");
				}
			}

			// Create OrderDetails
			listAvailableProduct.forEach(availableProduct -> {

				// Count products by id
				long countProductById = products.stream()
						.filter(prod -> prod.getProductId() == availableProduct.getProductId()).count();
				if (countProductById > 0) {
					OrderDetail orderDetail = new OrderDetail();

					// calculate total price by orderDetail
					BigDecimal totalOrderDetail = availableProduct.getPrice()
							.multiply(new BigDecimal(countProductById));
					orderDetail.setPrice(totalOrderDetail);
					orderDetail.setProductescription(countProductById + " X " + availableProduct.getName());
					orderDetail.setOrder(orderCustomer);
					orderCustomer.getOrderDetails().add(orderDetail);
				}
			});
			// Set date
			orderCustomer.setOrderTime(new Date());

			// Persis objects
			_orderDAO.saveOrderCustomer(orderCustomer);

		} catch (Exception e) {
			return new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}

		return new Response("OK", HttpStatus.OK.value());

	}
	
	/**
	 * This method gets orders by Customer and dates
	 */
	public List<Order> getOrderByCustomerIdAndDate(int customerId, Date start, Date end) {
		Calendar c = Calendar.getInstance();
		c.setTime(end);
		c.add(Calendar.DATE, 1);
		return _orderDAO.getOrderByCustomerIdAndDate(customerId, start, c.getTime());
	}

}
