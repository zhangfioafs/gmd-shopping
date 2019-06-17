package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.springcloud.dao.OrderDetailMapper;

import com.springcloud.entity.OrderDetail;

import com.springcloud.service.OrderDetailsService;


/**
 * 订单明细模块模型层的实现类，用于实现订单明细模块的方法
 * @author zlq1995
 *
 */
@Service
public class OederDetailsServiceImpl implements OrderDetailsService {
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Override
	public PageInfo<OrderDetail> selectByOrderId(Integer orderId, Integer pageNumber) {
		PageHelper.startPage(pageNumber + 1, 5);
		List<OrderDetail> list = this.orderDetailMapper.selectByOrderId(orderId);
		return new PageInfo<>(list);
		
	}

	
}
