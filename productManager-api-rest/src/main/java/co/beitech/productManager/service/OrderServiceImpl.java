package co.beitech.productManager.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.beitech.productManager.dao.CustomerDAO;
import co.beitech.productManager.dao.OrderCustomerDAO;
import co.beitech.productManager.domain.Response;
import co.beitech.productManager.model.Customer;
import co.beitech.productManager.model.OrderCustomer;
import co.beitech.productManager.model.OrderDetail;
import co.beitech.productManager.model.Product;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	private static final int MAX_PRODUCT = 5;
	@Autowired
	private OrderCustomerDAO _orderCustomerDao;

	@Autowired
	private CustomerDAO _customerDao;

	
	/**
	 * Get ORders
	 */
	public List<OrderCustomer> getOrderCustomers() {
		return _orderCustomerDao.getOrderCustomers();
	}

	/**
	 * 
	 */
	public Response saveOrderCustomers(OrderCustomer orderCustomer, List<Product> products) {

		try {

			// Validate max 5 products
			if (products.size() > MAX_PRODUCT) {
				throw new Exception("La cantidad de productos debe ser menor a " + MAX_PRODUCT);
			}

			int customerId = orderCustomer.getCustomer().getCustomerId();
			orderCustomer.setCustomer(_customerDao.getCustomerById(customerId));
			List<Product> listAvailableProduct = orderCustomer.getCustomer().getProducts();

			// Validate: Available product by Customer
			for (Product product : products) {
				long count = listAvailableProduct.stream().filter(prod -> prod.getProductId() == product.getProductId())
						.count();
				if (count == 0) {
					throw new Exception("El product con id " + product.getProductId()
							+ " no esta disponible para el cliente " + orderCustomer.getCustomer().getName());
				}
			}

			// Create OrderDetails
			listAvailableProduct.forEach(availableProduct -> {
				long count = products.stream().filter(prod -> prod.getProductId() == availableProduct.getProductId())
						.count();
				if (count > 0) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setPrice(availableProduct.getPrice().multiply(new BigDecimal(count)));
					orderDetail.setProductoDescription(count + " X " + availableProduct.getName());
					orderDetail.setOrderCustomer(orderCustomer);
					orderCustomer.getOrderDetails().add(orderDetail);
				}
			});

			orderCustomer.setOrderTime(new Date());

			// Persis objects
			_orderCustomerDao.saveOrderCustomer(orderCustomer);

		} catch (Exception e) {
			return new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
		}

		return new Response("Success", HttpStatus.OK.value());

	}

}
