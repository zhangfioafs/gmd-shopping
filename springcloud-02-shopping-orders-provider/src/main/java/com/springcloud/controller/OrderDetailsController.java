package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.OrderDetail;

import com.springcloud.service.OrderDetailsService;

import com.springcloud.vo.ResultValue;

/**
 * 订单明细模块的控制层
 * @author 张凌强
 *
 */
@RestController
@RequestMapping(value = "orderDetails")
public class OrderDetailsController {
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	/**
	 * 查询满足条件1的订单信息
	 * @param orderId 商品编号
	 * @param pageNumber 页数
	 * @return
	 */
	@RequestMapping(value = "/selectByOrderId")
	public ResultValue selectByOrderId(@RequestParam("orderId") Integer orderId,
			@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			PageInfo<OrderDetail> pageInfo = this.orderDetailsService.selectByOrderId(orderId, pageNumber);
			List<OrderDetail> list = pageInfo.getList();

			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("orderDetailList", list);
				
				PageUtils pageUtils = new PageUtils(5,pageInfo.getPages() * 5);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("没有找到满足条件的信息！！！");
		return rv;
	}
}
