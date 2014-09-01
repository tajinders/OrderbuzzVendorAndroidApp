package com.orderbuzz.domain;
import java.util.ArrayList;

public class Order {
	String orderId;
	String stripetokenno ;
	String restId ;
	String orderKey ; 
	String orderGcmKey ;
	ArrayList<OrderItem> orderItem;
	String totalPrice;
	String orderSequenceNo; 
	
	public String getOrderSequenceNo() {
		return orderSequenceNo;
	}
	public void setOrderSequenceNo(String orderSequenceNo) {
		this.orderSequenceNo = orderSequenceNo;
	}
	private String orderStatus;
	

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStripetokenno() {
		return stripetokenno;
	}
	public void setStripetokenno(String stripetokenno) {
		this.stripetokenno = stripetokenno;
	}
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public String getOrderKey() {
		return orderKey;
	}
	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}
	public String getOrderGcmKey() {
		return orderGcmKey;
	}
	public void setOrderGcmKey(String orderGcmKey) {
		this.orderGcmKey = orderGcmKey;
	}
	public ArrayList<OrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(ArrayList<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	


}
