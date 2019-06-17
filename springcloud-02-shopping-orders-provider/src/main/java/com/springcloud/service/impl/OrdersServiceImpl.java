package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrdersMapper;

import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public PageInfo<Orders> selectOrder(Orders orders, Integer pageNumber) {
		//为用户名2端添加%%
		if (orders.getUser() != null) {
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}
		PageHelper.startPage(pageNumber + 1, PageUtils.PAGE_ROW_COUNT);
		// 查询满足条件的订单信息
		List<Orders> list = this.ordersMapper.selectOrders(orders);
		// 返回分页信息
		return new PageInfo<>(list);

	}
	
	@Transactional
	@Override
	public Integer updateOrderStatus(Orders orders) {
		
		return this.ordersMapper.updateOrderStatus(orders);
	}

	@Override
	public List<Orders> selectGroup(Orders orders) {
		
		return this.ordersMapper.selectGroup(orders);
	}

}
