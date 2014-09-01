package com.orderbuzz.rest;
import java.util.HashMap;
import java.util.List;
import com.orderbuzz.domain.OrderItem;

/*
 * Making this class as Singleton and it will store restaurant menu in Hash Map
 */

public class OrderCache {
	private HashMap<String,List<OrderItem>> OrderMap;
	private static OrderCache instance;

	private OrderCache(){
		OrderMap = new HashMap<String,List<OrderItem>>();
	}

	public static OrderCache getInstance(){
		if (instance == null){
			instance = new OrderCache();
		}
		return instance;
	}

	public List<OrderItem> getOrderItems(String orderId) {
		return OrderMap.get(orderId);
	}

	public void AddOrderItemList(String orderId , List<OrderItem> orderItemList)
	{
		OrderMap.put(orderId, orderItemList);
	}
}
