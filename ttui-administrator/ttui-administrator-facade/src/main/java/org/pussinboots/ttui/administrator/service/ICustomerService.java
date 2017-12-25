package org.pussinboots.ttui.administrator.service;

import com.baomidou.mybatisplus.service.IService;
import org.pussinboots.ttui.administrator.entity.Customer;
import org.pussinboots.ttui.administrator.pojo.dto.CustomerPageDTO;
import org.pussinboots.ttui.common.support.page.PageInfo;


/**
 * 
* 项目名称：ttui-administrator-facade
* 类名称：ICustomerService
* 类描述：User / 用户表 业务逻辑层接口
* 创建人：zhancl
*
 */
public interface ICustomerService extends IService<Customer> {
	

	/**
	 * 根据分页信息/搜索内容查找用户列表
	 * @param pageInfo 分页信息
	 * @param search 搜索内容
	 * @return
	 */
	CustomerPageDTO listByPage (PageInfo pageInfo, String search);

	Integer insertCustomer(Customer customer, String userName);

	Integer updateUserId(Customer customer, String userName);
}
