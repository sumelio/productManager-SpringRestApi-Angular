package co.beitech.productManager.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;


/**
 * The persistent class for the order_detail database table.
 * 
 */
@Entity
@Table(name="order_detail")
@NamedQuery(name="OrderDetail.findAll", query="SELECT o FROM OrderDetail o")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_detail_id")
	private int orderDetailId;

	private BigDecimal price;

	@Column(name="product_description")
	private String productDescription;


	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="order_id")
	private OrderCustomer orderCustomer;
	

	public OrderDetail() {
	}

	public int getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public OrderCustomer getOrderCustomer() {
		return this.orderCustomer;
	}

	public void setOrderCustomer(OrderCustomer orderCustomer) {
		this.orderCustomer = orderCustomer;
	}

}