package co.beitech.productManager.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * The persistent class for the order_customer database table.
 * 
 */
@Entity
@Table(name = "order_")
@NamedQueries({ @NamedQuery(name = "OrderCustomer.findAll", query = "SELECT o FROM Order o"),
		@NamedQuery(name = "OrderCustomer.findByCustomAndDate", query = "select o from Order o where o.customer.id = :customerId and o.orderTime  BETWEEN :stDate AND :edDate  ") })
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "delivery_address")
	private String deliveryAddress;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	@Column(name = "order_time")
	private Date orderTime;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
	
	@Transient
	private BigDecimal totalPriceOrder;

	public Order() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@JsonIgnore
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getOrderDetails() {
		if (this.orderDetails == null) {
			this.orderDetails = new ArrayList<OrderDetail>();
		}
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrder(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrder(null);

		return orderDetail;
	}

	/**
	 * Sum of the all prices orderDetail
	 * 
	 * @return BigDecimal total
	 */
	
	public BigDecimal getTotalPriceOrder() {
		BigDecimal totalPrice = this.getOrderDetails().
				stream()
				   .map(OrderDetail::getPrice)
				   .reduce(
				       BigDecimal.ZERO,
				       (a, b) -> a.add( b));
		
		return totalPrice;
	}

	public void setTotalPriceOrder(BigDecimal totalPrice) {
		this.totalPriceOrder = totalPrice;
	}
	
	

}