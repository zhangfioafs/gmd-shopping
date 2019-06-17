package com.springcloud.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * order表对应的实体类，用于封装一行订单信息
 * 
 * @author 张凌强
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements java.io.Serializable {

	private static final long serialVersionUID = -3857392721920542241L;
	/**
	 * 订单编号
	 */
	private Integer orderId;
	/**
	 * 当前订单对应的用户信息
	 */
	private Users user;

	/**
	 * 收货人姓名
	 */
	private String receiverName;
	/**
	 * 收货人电话
	 */
	private String receiverTel;
	/**
	 * 收货人地址
	 */
	private String receiverAddr;
	/**
	 * 下单时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderTime;
	/**
	 * 订单编号
	 */
	private Double orderTotal;
	/**
	 * 订单状态：0待付款1待发货2待收货3已付款，4已退货
	 */
	private Integer orderStatus;

	/**
	 * 订单起始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderDateMin;

	/**
	 * 订单终止时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date orderDateMax;

	/**
	 * 查询条件：起始年月
	 */
	private String startMonth;
	
	/**
	 * 查询条件：终止年月
	 */
	private String endMonth;
	
	/**
	 * 统计结果的年月日
	 */
	private String orderMonth;
	
	/**
	 * 统计结果的销售额
	 */
	private Double orderPrice;

}