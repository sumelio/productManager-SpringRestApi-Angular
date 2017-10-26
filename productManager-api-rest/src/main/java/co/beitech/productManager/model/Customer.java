package co.beitech.productManager.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer") 
@NamedQueries({
	@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c"),
	@NamedQuery(name="Customer.findById", query="select c from Customer  c where c.id = :id")
}) 


public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="customer_id")
	private int customerId;

	private String email;

	private String name;

 
	@OneToMany
	@JoinTable(
		name="customer_available_product"
		, joinColumns={
			@JoinColumn(name="customer_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="product_id")
			}
		)
	private List<Product> products;

 

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

 
 

}


