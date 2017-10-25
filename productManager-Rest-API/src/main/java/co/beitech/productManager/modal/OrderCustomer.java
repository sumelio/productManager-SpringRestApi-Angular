package co.beitech.productManager.modal;

import java.io.Serializable;
import javax.persistence.*;
 

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order_customer database table.
 * 
 */
@Entity
@Table(name="order_customer")
@NamedQuery(name="OrderCustomer.findAll", query="SELECT o FROM OrderCustomer o")
public class OrderCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private int orderId;

	@Column(name="delivery_address")
	private String deliveryAddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_time")
	private Date orderTime;

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	

	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="orderCustomer")
	private List<OrderDetail> orderDetails;

	public OrderCustomer() {
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

	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrderCustomer(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrderCustomer(null);

		return orderDetail;
	}

}