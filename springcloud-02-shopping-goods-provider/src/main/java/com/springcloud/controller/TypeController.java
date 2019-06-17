package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.TypeOne;
import com.springcloud.entity.TypeTwo;
import com.springcloud.service.TypeService;
import com.springcloud.vo.ResultValue;

/**
 * 一级类别与二级类别的控制器
 * 
 * @author 张凌强
 *
 */
@RestController
@RequestMapping(value = "type")
public class TypeController {

	@Autowired
	private TypeService typeService;

	@RequestMapping(value = "/selectAll")
	public ResultValue selectAll() {
		ResultValue rv = new ResultValue();
		try {
			// 调用service相应的方法查询相应二级类别的信息，并保持结果
			List<TypeOne> typeOne = this.typeService.selectAllTypeOne();
			// 如果查询成功,2个条件不能交换
			if (typeOne != null && typeOne.size() > 0) {
				// 设置结果的状态为0
				rv.setCode(0);
				// 创建map集合
				Map<String, Object> map = new HashMap<>();
				// 将结果存入map中
				map.put("typeOneList", typeOne);
				// 返回ResultValue对象
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}

	@RequestMapping(value = "/selectById")
	public ResultValue selectById(@RequestParam("typeOneId") Integer typeOneId) {
		ResultValue rv = new ResultValue();
		try {
			List<TypeTwo> twoByTypeOneId = this.typeService.selectTypeTwoByTypeOneId(typeOneId);
			if (twoByTypeOneId != null && twoByTypeOneId.size() > 0) {
				rv.setCode(0);
				Map<String, Object> hashMap = new HashMap<>();
				hashMap.put("TypeTwo", twoByTypeOneId);
				rv.setDataMap(hashMap);
				return rv;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}

}
