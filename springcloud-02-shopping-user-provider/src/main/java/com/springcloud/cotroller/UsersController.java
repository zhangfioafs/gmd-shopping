package com.springcloud.cotroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;
import com.springcloud.vo.ResultValue;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping(value = "/login")
	public ResultValue login(@RequestParam("userId") Integer userId, @RequestParam("userPsw") String userPsw,
			@RequestParam("permissionId") Integer permissionId) {
		ResultValue rv = new ResultValue();

		try {
			Users login = this.usersService.login(userId, userPsw, permissionId);
			if (login != null) {
				rv.setCode(0);

				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				rv.setDataMap(map);

				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("登录信息不正确，请重新输入！！！");
		return rv;
	}

	/**
	 * 添加新的用户
	 * 
	 * @param users 新用户信息
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Users insert = this.usersService.insert(users);
			if (insert != null) {
				rv.setCode(0);
				rv.setMessage("用户录入成功！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户录入失败！！！");
		return rv;

	}

	/**
	 * 查询满足条件的用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "select")
	public ResultValue select(Users users, @RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			List<Users> list = page.getContent();
			// 判断是否查询到了数据
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();

				// 将分页的数据添加到map中
				map.put("userList", list);
				PageUtils pageUtils = new PageUtils((int) page.getTotalElements());

				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);

				// 将map添加到ResultValue
				rv.setDataMap(map);
				return rv;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}

	@RequestMapping(value = "/updateStatus")
	public ResultValue updateStatus(@RequestParam("userId") Integer userId,
			@RequestParam("userStatus") Integer userStatus) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateStatus = this.usersService.updateStatus(userId, userStatus);
			if (updateStatus > 0) {
				rv.setMessage("用户状态修改成功！！！");
				rv.setCode(0);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("用户状态修改失败！！！");
		return rv;

	}

	/**
	 * 查询指定用户编号的用户信息
	 * 
	 * @param userId 用户编号
	 * @return
	 */
	@RequestMapping(value = "/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		ResultValue rv = new ResultValue();
		try {
			// 调用service中的方法
			Users selectById = this.usersService.selectById(userId);
			// 如果成功
			if (selectById != null) {
				// 创建map集合保存查询结果
				Map<String, Object> map = new HashMap<>();
				// 设置结果的状态为0
				rv.setCode(0);
				// 将查询结果保存到map集合中
				map.put("userId", selectById);
				// 将map集合添加到resultvalue对象中
				rv.setDataMap(map);
				// 返回resultvalue对象
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("获取用户信息失败！！！");
		return rv;
	}

	@RequestMapping(value = "/update")
	public ResultValue update(Users users) {
		ResultValue rv = new ResultValue();
		try {
			// 调用service中的方法修改用户信息，并获得修改是否成功
			Integer update = this.usersService.update(users);
			// 如果修改成功
			if (update > 0) {
				// 设置结果的状态为0
				rv.setCode(0);
				// 返回resultvalue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("修改信息失败！！！");
		return rv;
	}
	
	@RequestMapping(value = "/updateMessage")
	public ResultValue updateMessage(Users users) {
		ResultValue rv = new ResultValue();
		try {
			Integer updateMessage = this.usersService.updateMessage(users);
			if (updateMessage > 0) {
				rv.setCode(0);
				// 返回resultvalue对象
				rv.setMessage("修改用户信息成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("修改用户信息失败！！！");
		return rv;
	}

}
