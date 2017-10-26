package co.beitech.productManager.domain;

import java.util.ArrayList;
import java.util.List;

import co.beitech.productManager.model.Order;
import co.beitech.productManager.model.Product;

public class OrderCustomers {
	private Order orderCustomer;
	private List<Product> products;
	public Order getOrderCustomer() {
		return orderCustomer;
	}
	public void setOrderCustomer(Order orderCustomer) {
		this.orderCustomer = orderCustomer;
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
