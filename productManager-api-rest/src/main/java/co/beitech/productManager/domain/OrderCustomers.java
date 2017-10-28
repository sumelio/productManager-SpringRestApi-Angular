package co.beitech.productManager.domain;

import java.util.ArrayList;
import java.util.List;

import co.beitech.productManager.model.Order;
import co.beitech.productManager.model.Product;

/**
 * 
 * Simple POJO in order to contain order and orderProducts
 * 
 * @author Freddy.Lemus
 *
 */
public class OrderCustomers {
	private Order order;
	private List<Product> orderProducts;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Product> getOrderProducts() {
		if (this.orderProducts == null) {
			this.orderProducts = new ArrayList<Product>();
		}
		return this.orderProducts;
	}

	public void setOrderProducts(List<Product> products) {
		this.orderProducts = products;
	}

}
