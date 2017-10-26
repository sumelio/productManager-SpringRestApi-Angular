package co.beitech.productManager.domain;

import java.util.ArrayList;
import java.util.List;

import co.beitech.productManager.model.OrderCustomer;
import co.beitech.productManager.model.Product;

public class OrderCustomers {
	private OrderCustomer orderCustomer;
	private List<Product> products;
	public OrderCustomer getOrderCustomer() {
		return orderCustomer;
	}
	public void setOrderCustomer(OrderCustomer orderCustomer) {
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
