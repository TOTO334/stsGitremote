package org.order.dao;

import java.util.List;

import org.order.entity.Order;

public interface IOrderDao {
	public List<Order> queryAllOrder();
	public Order queryOrderById(int id);
	public Order queryOrderByName(String orderName);
	//添加
	public int addOrder(Order order);
	//删除
	public int deleteOrder(Order order) ;
	//修改
	public int updateOrder(Order order);

}
