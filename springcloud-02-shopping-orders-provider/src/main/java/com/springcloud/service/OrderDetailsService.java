package com.springcloud.service;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDetail;

/**
 * 订单明细模块的模型层，用于定义订单明细模块的方法
 * 
 * @author 张凌强
 *
 */
public interface OrderDetailsService {

	/**
	 * 查询指定编号的订单明细信息
	 * 
	 * @param orderId    订单编号
	 * @param pageNumber 页数
	 * @return 成功返回com.github.pagehelper.PageInfo<OrderDetail>类型的实例，否则返回null
	 */
	public abstract PageInfo<OrderDetail> selectByOrderId(Integer orderId, Integer pageNumber);

}
