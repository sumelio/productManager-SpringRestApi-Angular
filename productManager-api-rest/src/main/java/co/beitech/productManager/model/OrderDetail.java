package co.beitech.productManager.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
	private Order order;
	

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

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}