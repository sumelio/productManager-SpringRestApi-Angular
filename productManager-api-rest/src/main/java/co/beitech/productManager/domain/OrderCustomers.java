package co.beitech.productManager.domain;

import java.util.ArrayList;
import java.util.List;

import co.beitech.productManager.model.Order;
import co.beitech.productManager.model.Product;

public class OrderCustomers {
	private Order order;
	private List<Product> products;
 
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<Product> getProducts() {
		if(this.products == null) {
			this.products = new ArrayList<Product>();
		}
		return this.products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

 

}
