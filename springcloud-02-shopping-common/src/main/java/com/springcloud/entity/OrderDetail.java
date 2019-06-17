package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * order_details表对应的实体类，用于封装一行订单明细信息
 * @author 张凌强
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements java.io.Serializable{
   
	private static final long serialVersionUID = -965522763028914370L;
	/**
	 * 订单明细编号
	 */
	private Integer orderDetailId;
	/**
	 * 订单编号
	 */
    private Integer orderId;
    /**
	 * 商品编号
	 */
    private Integer goodsId;
    /**
	 * 商品名称
	 */
    private String goodsName;
    /**
	 * 成交价
	 */
    private Double goodsFinalPrice;
    /**
	 * 订单数量
	 */
    private Integer goodsFinalNum;

    
}